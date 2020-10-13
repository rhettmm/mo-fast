package wang.momo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.momo.common.MoConst;
import wang.momo.util.cache.CacheUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
     * 退出登陆后，删除缓存
     * @param request
     */
    public void delLoginCache(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(MoConst.MO_ADMIN_AUTH)){
                    String cookieValue = cookie.getValue();
                    cacheUtil.delCache(MoConst.CACHE_PREFIX + MoConst.LOGIN_ACCOUNT + cookieValue);
                }
            }
        }
    }

    /**
     * 获取登录用户账号
     * @param request
     * @return
     */
    public String getLoginAccount(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String account=null;
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(MoConst.MO_ADMIN_AUTH)){
                    String cookieValue = cookie.getValue();
                    account= (String) cacheUtil.getCache(MoConst.CACHE_PREFIX + MoConst.LOGIN_ACCOUNT + cookieValue);
                }
            }
        }
        return  account;
    }

    /**
     * 保存登录用户ID
     */
    public void saveLoginAccount(String key,String account){
        cacheUtil.setCache(MoConst.CACHE_PREFIX+MoConst.LOGIN_ACCOUNT+key,account);
    }

    /**
     * 保存登录用户ID
     */
    public void saveLoginAccount(String key,String account,long timeOut){
        cacheUtil.setCache(MoConst.CACHE_PREFIX+MoConst.LOGIN_ACCOUNT+key,account,timeOut);
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
