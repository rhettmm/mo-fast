package ${packages}.controller;

import ${packages}.entity.${className};
import ${packages}.service.${className}Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import wang.momo.common.ResultData;
import wang.momo.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import wang.momo.moadmin.entity.SysUser;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("${classNameLower}")
public class ${className}Controller {
    @Autowired
    private ${className}Service ${classNameFirstLower}Service;
    @Autowired
    private RequestUtil requestUtil;


    @GetMapping("/view")
    public ModelAndView view(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/${htmlpath}/${htmlName}");
        return modelAndView;
    }

    @PostMapping("/insert")
    public ResultData<String> insert(${className} ${classNameFirstLower},HttpServletRequest request){
        SysUser loginUser = requestUtil.getLoginUser(request);
        ${classNameFirstLower}.setCreateAccount(loginUser.getMoAccount());
        ${classNameFirstLower}.setUpdateAccount(loginUser.getMoAccount());
        ${classNameFirstLower}Service.insert( ${classNameFirstLower});
        return new ResultData<String>().setCode(ResultData.CodeList.OK).setMsg("添加成功！");
    }


    @PostMapping("/deleteById")
    public ResultData<String> deleteById(int id){
        ${classNameFirstLower}Service.deleteById(id);
        return new ResultData<String>().setCode(ResultData.CodeList.OK).setMsg("删除成功！");
    }


    @PostMapping("/updateById")
    public ResultData<String> updateById(${className} ${classNameFirstLower},HttpServletRequest request){
        SysUser loginUser = requestUtil.getLoginUser(request);
        ${classNameFirstLower}.setUpdateAccount(loginUser.getMoAccount());
        ${classNameFirstLower}Service.updateById( ${classNameFirstLower});
        return new ResultData<String>().setCode(ResultData.CodeList.OK).setMsg("更新成功！");
    }


    @GetMapping("/queryAll")
    public ResultData<List<${className}>> queryAll(${className} ${classNameFirstLower}){
        List<${className}>  ${classNameFirstLower}List = ${classNameFirstLower}Service.queryAll( ${classNameFirstLower});
        return new ResultData<List<${className}>>().setCode(ResultData.CodeList.OK).setMsg("添加成功！").setData(${classNameFirstLower}List);
    }


    @GetMapping("/queryPage")
    public Map<String,Object> queryPage(Page<${className}> page,${className} ${classNameFirstLower}){
        ${classNameFirstLower}Service.queryPage(page,${classNameFirstLower});
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",page.getTotal());
        map.put("rows",page.getRecords());
        return map;
    }

}
