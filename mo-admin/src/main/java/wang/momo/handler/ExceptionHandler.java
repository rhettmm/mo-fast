package wang.momo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.NoHandlerFoundException;
import wang.momo.common.MoException;
import wang.momo.util.MoResultUtil;
import wang.momo.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 23:16
 */
@RestControllerAdvice
public class ExceptionHandler {
    @Autowired
    private RequestUtil requestUtil;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Object handle(HttpServletRequest request, HttpServletResponse response, Object  handler, Exception e){
        //if(requestUtil.isAjax(request)){
            if (e instanceof MoException) {
                return MoResultUtil.error(301,"Internal Error"+e.toString());
            }
            if (e instanceof NoHandlerFoundException) {
                return MoResultUtil.error(400,"uri "+request.getRequestURI()+" not exits!");
            }
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod() .getName(), e.getMessage());
            } else {
                message = e.getMessage();
            }

            return MoResultUtil.error(400,message);
//        }else {
//            ModelAndView modelAndView = new ModelAndView();
//            if (e instanceof MoException) {
//                modelAndView.addObject("msg",e.getMessage());
//                modelAndView.setViewName("/error/500");
//                return modelAndView;
//            }
//            if (e instanceof NoHandlerFoundException) {
//                modelAndView.addObject("msg","找不到页面");
//                modelAndView.setViewName("/error/400");
//                return modelAndView;
//            }
//            String message;
//            if (handler instanceof HandlerMethod) {
//                HandlerMethod handlerMethod = (HandlerMethod) handler;
//                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
//                        handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod() .getName(), e.getMessage());
//            } else {
//                message = e.getMessage();
//            }
//            modelAndView.addObject("msg",message);
//            modelAndView.setViewName("/error/500");
//            return modelAndView;
//        }


    }
}
