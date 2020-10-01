package wang.momo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.momo.common.MoConst;
import wang.momo.moadmin.entity.SysUser;
import wang.momo.util.cache.CacheUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 请求工具类
 * @author rhettmm
 * @version 1.0
 * @date 2020/7/7 21:47
 */
@Component
public class RequestUtil {
    @Autowired
    private CacheUtil cacheUtil;
    /**
     * 根据请求查询当前登录用户
     * @param request
     * @return
     */
    public SysUser getLoginUser(HttpServletRequest request){
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
    }

    /**
     * 退出登陆后，删除缓存
     * @param request
     */
    public void delLoginCache(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        SysUser user=null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(MoConst.MO_ADMIN_AUTH)){
                String cookieValue = cookie.getValue();
                cacheUtil.delCache(MoConst.CACHE_PREFIX + MoConst.LOGIN_TOKEN + cookieValue);
            }
        }

    }

    /**
     * 判断请求是否未ajax请求
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request){
        boolean result=false;
        String requestedWith = request.getHeader("X-Requested-With");
        if(requestedWith!=null&&requestedWith.equalsIgnoreCase("XMLHttpRequest")){
            result=true;
        }
        return result;
    }
}
