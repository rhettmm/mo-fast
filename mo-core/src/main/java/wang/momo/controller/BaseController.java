package wang.momo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import wang.momo.common.MoConst;
import wang.momo.util.RequestUtil;
import wang.momo.util.cache.CacheUtil;


/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 1:06
 */
public class BaseController {
    @Autowired
    private CacheUtil cacheUtil;
    private RequestUtil requestUtil;

    /**
     * 根据请求查询当前登录用户
     * @param request
     * @return
     *//*
    public  SysUser getLoginUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        SysUser user=null;
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(MoConst.MO_ADMIN_AUTH)){
                    String cookieValue = cookie.getValue();
                    user= (SysUser) cacheUtil.getCache(MoConst.CACHE_PREFIX + MoConst.LOGIN_TOKEN + cookieValue);
                }
            }
        }
        return user;
    }*/
}
