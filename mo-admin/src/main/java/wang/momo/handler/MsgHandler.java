package wang.momo.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import wang.momo.annotion.NotMsgHandle;
import wang.momo.common.MoResult;
import wang.momo.util.MoResultUtil;


/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:40
 */
@ControllerAdvice
public class MsgHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(NotMsgHandle.class)){
            return o;
        }
        if(methodParameter.getMethod().isAnnotationPresent(NotMsgHandle.class)){
            return o;
        }

        if(o==null){
          return MoResultUtil.success(o);
        } else if(o instanceof MoResult){
           return o;
       }else if(o instanceof Exception){
           return MoResultUtil.error(0,o.toString());
       } else {
           return MoResultUtil.success(o);
       }
    }
}
