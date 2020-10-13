package wang.momo.moadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wang.momo.annotion.NotLogin;
import wang.momo.annotion.NotPermission;
import wang.momo.moadmin.entity.TestUser;
import wang.momo.moadmin.service.TestUserService;
import wang.momo.util.RequestUtil;
import wang.momo.util.WrapperUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

import java.util.Date;
import java.util.List;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:39
 */
@RestController
@RequestMapping("test")
@NotLogin
@NotPermission
public class TestController {

    @Autowired
    private TestUserService testUserService;
    @Autowired
    private RequestUtil requestUtil;

    /**
     * 挑战页面
     * @return
     */
    @GetMapping("/view")
    public ModelAndView view(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public boolean save(TestUser testUser, HttpServletRequest request){
        Date date = new Date();
        String account = requestUtil.getLoginAccount(request);
        testUser.setCreateTime(date)
                .setCreateAccount(account)
                .setUpdateTime(date)
                .setUpdateAccount(account);
        return testUserService.save(testUser);
    }

    /**
     * 删除
     * @param testUser
     * @param request
     * @return
     */
    @PostMapping("/remove")
    public boolean remove(TestUser testUser,HttpServletRequest request) throws IllegalAccessException {
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        WrapperUtil.voluation(wrapper,testUser);
        return testUserService.remove(wrapper);
    }

    /**
     * 更新
     * @param testUser
     * @param request
     * @return
     */
    @PostMapping("/updateById")
    public boolean updateById(TestUser testUser,HttpServletRequest request){
        return testUserService.updateById(testUser);
    }

    /**
     * 分页查询
     * @param page
     * @param testUser
     */
    @PostMapping("/page")
    public void page(Page<TestUser> page,TestUser testUser){


    }

    @PostMapping("/queryAll")
    public List<TestUser> queryAll(TestUser testUser) throws IllegalAccessException {
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        Field[] declaredFields = testUser.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object o = declaredField.get(testUser);


        }


        List<TestUser> list = testUserService.list();

        return null;
    }

    @GetMapping("/queryByConds")
    public TestUser queryByConds(){
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",456);
        TestUser one = testUserService.getOne(wrapper);
        System.out.println("查询结果："+one);
        return one;
    }




}
