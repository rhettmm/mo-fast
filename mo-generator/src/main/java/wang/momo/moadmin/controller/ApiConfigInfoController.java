package wang.momo.moadmin.controller;

import wang.momo.moadmin.entity.ApiConfigInfo;
import wang.momo.moadmin.service.ApiConfigInfoService;
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



import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("apiconfiginfo")
public class ApiConfigInfoController {
    @Autowired
    private ApiConfigInfoService apiConfigInfoService;
    @Autowired
    private RequestUtil requestUtil;


    @GetMapping("/view")
    public ModelAndView view(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/core/api_config_info");
        return modelAndView;
    }

    @PostMapping("/insert")
    public ResultData<String> insert(ApiConfigInfo apiConfigInfo,HttpServletRequest request){
        String account = requestUtil.getLoginAccount(request);
        apiConfigInfo.setCreateAccount(account);
        apiConfigInfo.setUpdateAccount(account);
        apiConfigInfoService.insert( apiConfigInfo);
        return new ResultData<String>().setCode(ResultData.CodeList.OK).setMsg("添加成功！");
    }


    @PostMapping("/deleteById")
    public ResultData<String> deleteById(int id){
        apiConfigInfoService.deleteById(id);
        return new ResultData<String>().setCode(ResultData.CodeList.OK).setMsg("删除成功！");
    }


    @PostMapping("/updateById")
    public ResultData<String> updateById(ApiConfigInfo apiConfigInfo,HttpServletRequest request){
        String account = requestUtil.getLoginAccount(request);
        apiConfigInfo.setUpdateAccount(account);
        apiConfigInfoService.updateById( apiConfigInfo);
        return new ResultData<String>().setCode(ResultData.CodeList.OK).setMsg("更新成功！");
    }


    @GetMapping("/queryAll")
    public ResultData<List<ApiConfigInfo>> queryAll(ApiConfigInfo apiConfigInfo){
        List<ApiConfigInfo>  apiConfigInfoList = apiConfigInfoService.queryAll( apiConfigInfo);
        return new ResultData<List<ApiConfigInfo>>().setCode(ResultData.CodeList.OK).setMsg("添加成功！").setData(apiConfigInfoList);
    }


    @GetMapping("/queryPage")
    public Map<String,Object> queryPage(Page<ApiConfigInfo> page,ApiConfigInfo apiConfigInfo){
        apiConfigInfoService.queryPage(page,apiConfigInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",page.getTotal());
        map.put("rows",page.getRecords());
        return map;
    }

}
