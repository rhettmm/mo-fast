package wang.momo.mogenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import wang.momo.util.FreeMarkerUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MoGeneratorApplicationTests {

    @Value("${mo-generator.target.tables}")
    private String table;
    @Value("${mo-generator.target.remove-prefix}")
    private String prefix;
    @Value("${mo-generator.target.packages}")
    private String packages;
    @Value("${mo-generator.target.outpath}")
    private String outpath;
    @Value("${mo-generator.target.htmlpath}")
    private String htmlpath;
    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;


    /**
     * 生成代码
     */
    @Test
    void codeGenerator() {
        //TODO 先删除输出路径下的所有文件
        File file = new File(outpath);
        deleteAllFiles(file);
        //根据逗号，切分表格名称
        String[] tables = table.split(",");
        String db = url.split("\\?")[1].split("&")[0];
        for (String t : tables) {
            InitCode(db, t);
        }
    }

    /**
     * 删除文件夹下的所有文件
     * @param file
     */
    private void deleteAllFiles(File file) {
        if(file.exists()&&file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                if(f.isFile()){
                    if(!f.getName().equalsIgnoreCase("sysUser.java")){
                        f.delete();
                    }
                }else {
                    deleteAllFiles(f);
                }
            }
        }
    }

    /**
     * 初始化生成bean dao service serviceImpl controller 层
     * TODO 生成页面
     * @param db
     * @param t
     */
    private void InitCode(String db, String t) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, String>> tableInfo = freeMarkerUtil.getTableInfo(db,t);
        map.put("hasBigDecimal",false);
        map.put("hasDate",false);
        for (Map<String, String> fieldMap : tableInfo) {
            if(fieldMap.get("dataType").equals("BigDecimal")){
                map.put("hasBigDecimal",true);
            }
            if(fieldMap.get("dataType").equals("Date")){
                map.put("hasDate",true);
            }
        }
        //类名是否去除前缀
        String className=null;
        String classNameFirstLower=null;
        String classNameLower=null;
        String replace=null;
        if(prefix!=null&&!"".equals(prefix)){
            replace = t.replace(prefix, "");
            className=freeMarkerUtil.underline2Hump(replace,true);
            classNameFirstLower=freeMarkerUtil.underline2Hump(replace,false);
            classNameLower=freeMarkerUtil.removeUnderLine(replace);
        }
        map.put("htmlName",replace);
        map.put("className",className);
        map.put("classNameFirstLower",classNameFirstLower);
        map.put("classNameLower",classNameLower);
        map.put("packages",packages);
        map.put("tableName",t);
        map.put("tableNameColumn","");
        map.put("columns",tableInfo);
        map.put("htmlpath",htmlpath);
        freeMarkerUtil.printTemplate(map,"entity.ftl",outpath+"/entity",className+".java");
        freeMarkerUtil.printTemplate(map,"dao.ftl",outpath+"/dao",className+"Mapper.java");
        freeMarkerUtil.printTemplate(map,"service.ftl",outpath+"/service",className+"Service.java");
        freeMarkerUtil.printTemplate(map,"serviceImpl.ftl",outpath+"/service/impl",className+"ServiceImpl.java");
        freeMarkerUtil.printTemplate(map,"controller.ftl",outpath+"/controller",className+"Controller.java");
        freeMarkerUtil.printTemplate(map,"html.ftl",outpath+"/view/"+htmlpath,replace+".html");
        freeMarkerUtil.printTemplate(map,"messages_en_US.ftl",outpath+"/message",replace+"messages_en_US.properties");
        freeMarkerUtil.printTemplate(map,"messages.ftl",outpath+"/message",replace+"messages.properties");
    }
}
