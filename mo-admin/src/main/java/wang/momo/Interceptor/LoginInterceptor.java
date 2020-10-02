package wang.momo.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;
import wang.momo.annotion.NotLogin;
import wang.momo.util.RequestUtil;
import wang.momo.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/3 1:25
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestUtil requestUtil;
    /**
     * 处理controller请求前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //有NotLogin注解直接跳过登录校验
        if(handler instanceof HandlerMethod){
            boolean methodAnnotation = ((HandlerMethod) handler).getMethod().isAnnotationPresent(NotLogin.class);
            boolean typeAnnotation= ((HandlerMethod) handler).getMethod().getDeclaringClass().isAnnotationPresent(NotLogin.class);
            if(typeAnnotation || methodAnnotation){
                return true;
            }
            String loginAccount = requestUtil.getLoginAccount(request);
            if(StringUtil.isEmpty(loginAccount)){
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 处理请求后，渲染页面前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
