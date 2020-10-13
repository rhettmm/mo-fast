package wang.momo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.momo.annotion.NotPermission;
import wang.momo.common.MoException;
import wang.momo.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验api token
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/9 23:37
 */
@Aspect
public class ApiAspect {

    @Pointcut("execution(* wang.momo.api.*.*(..))")
    public void apiPointCut(){}

    @Around("apiPointCut()")
    public Object validateToken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed=null;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        boolean methodAnnotion = signature.getMethod().isAnnotationPresent(NotPermission.class);
        boolean typeAnnotion = signature.getMethod().getDeclaringClass().isAnnotationPresent(NotPermission.class);
        if(methodAnnotion||typeAnnotion){
            proceed = proceedingJoinPoint.proceed();
        }else {
            //执行权限校验
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            if (doTokenValidate(request)){
                proceed=proceedingJoinPoint.proceed();
            }else {
                throw new MoException("invalid token!");
            }
        }
        return proceed;
    }

    /**
     * 校验token是否过期
     * @param request
     * @return
     */
    private boolean doTokenValidate(HttpServletRequest request) {
        //TODO 校验token是否过期
        String appId = request.getParameter("appId");
        String appKey = request.getParameter("appKey");
        if(StringUtil.isEmpty(appId)||StringUtil.isEmpty(appKey))
            throw new MoException("appId or appKey can not be null");
        else



        return true;
    }
}
