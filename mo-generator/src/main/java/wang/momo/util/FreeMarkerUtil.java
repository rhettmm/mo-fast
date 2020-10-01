package wang.momo.util;


import freemarker.template.Template;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FreeMarkerUtil {

    @Autowired
    private PropertiesConfiguration configuration;

    @Autowired
    private freemarker.template.Configuration freeMarker;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 生成模板
     */
    public void printTemplate(Map<String, Object> root,String templateName,String saveUrl,String fileName){
        try {
            Template template = freeMarker.getTemplate(templateName);
            File saveDir = new File(saveUrl);
            if(!saveDir.exists()){
                saveDir.mkdirs();
            }
            File file = new File(saveUrl, fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            template.process(root,bufferedWriter);
        } catch (Exception e) {
            new RuntimeException("生成模板失败",e);
        }

    }

    /**
     * 获取数据库表格属性并转化为java类型
     * @param dataBase
     * @param tableName
     * @return
     */
    public List<Map<String,String>> getTableInfo(String dataBase,String tableName){
        //获取表格信息
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT b.TABLE_NAME,b.TABLE_COMMENT,a.COLUMN_NAME,a.DATA_TYPE,a.COLUMN_COMMENT,a.IS_NULLABLE,a.COLUMN_KEY,a.EXTRA FROM information_schema.`COLUMNS` as a LEFT JOIN information_schema.`TABLES` as b on a.TABLE_SCHEMA=b.TABLE_SCHEMA and a.TABLE_NAME=b.TABLE_NAME WHERE a.TABLE_SCHEMA=(")
            .append("select DATABASE()")
            .append(") and a.TABLE_NAME='")
            .append(tableName)
            .append("'");
        List<Map<String, Object>> initMaps = jdbcTemplate.queryForList(sql.toString());
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        for (Map<String, Object> initMap : initMaps) {
            HashMap<String, String> map = new HashMap<>();
            map.put("tableName",initMap.get("TABLE_NAME").toString());
            map.put("tableComment",initMap.get("TABLE_COMMENT").toString());
            map.put("fieldName",underline2Hump(initMap.get("COLUMN_NAME").toString(),false));
            map.put("fieldNameFirstUpper",underline2Hump(initMap.get("COLUMN_NAME").toString(),true));
            map.put("dbFieldName",initMap.get("COLUMN_NAME").toString());
            map.put("dataType",convert2JavaType(initMap.get("DATA_TYPE").toString()));
            map.put("columnComment",initMap.get("COLUMN_COMMENT").toString());
            map.put("nullable",initMap.get("IS_NULLABLE").toString());
            map.put("columnKey",initMap.get("COLUMN_KEY").toString());
            map.put("extra",initMap.get("EXTRA").toString());
            maps.add(map);
        }
        return maps;
    }

    /**
     * 将数据库数据类型转换为java数据类型
     * @param sqlType
     * @return
     */
    public String convert2JavaType(String sqlType){
       return configuration.getString(sqlType,"undefined");
    }

    /**
     * 下划线转驼峰规则，
     * @param target       需要转换的目标文字
     * @param firstUpper   首字母是否大写
     * @return
     */
    public String underline2Hump(String target,boolean firstUpper){
        String[] arr = target.split("_");
        StringBuilder res= new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if(i==0){
                if(firstUpper){
                    arr[i].toLowerCase();
                    arr[i]= arr[i].substring(0,1).toUpperCase()+ arr[i].substring(1);
                }else {
                    arr[i].toLowerCase();
                    arr[i]= arr[i].substring(0,1).toLowerCase()+ arr[i].substring(1);
                }
            }else {
                arr[i].toLowerCase();
                arr[i]= arr[i].substring(0,1).toUpperCase()+ arr[i].substring(1);
            }
            res.append(arr[i]);
        }
        return res.toString();
    }

    /**
     * 下划线转驼峰规则，
     * @param target       需要转换的目标文字
     * @return
     */
    public String removeUnderLine(String target){
        String[] arr = target.split("_");
        StringBuilder res= new StringBuilder();
        for (String s : arr) {
            String ss= s.toLowerCase();
            res.append(ss);
        }
        return res.toString();
    }

}
