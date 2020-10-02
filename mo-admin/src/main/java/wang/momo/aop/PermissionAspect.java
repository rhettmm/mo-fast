package wang.momo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.momo.annotion.NotPermission;
import wang.momo.common.MoException;
import wang.momo.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/3 2:06
 */
@Aspect
public class PermissionAspect {
    @Autowired
    private RequestUtil requestUtil;

    @Pointcut("execution(* wang.momo.moadmin.controller.*.*(..))")
    public void controllerPointCut(){}

    @Around("controllerPointCut()")
    public Object validatePerm(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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
            String loginAccount = requestUtil.getLoginAccount(request);
            if (doPermissionValidate(loginAccount)){
                proceed=proceedingJoinPoint.proceed();
            }else {
                throw new MoException("you do not have permission to access,please contact the admin!");
            }
        }
        return proceed;
    }

    /**
     * 执行校验权限的动作
     * @param loginAccount
     * @return
     */
    public boolean doPermissionValidate(String loginAccount){
        //todo 权限校验动作！

        return true;
    }
}
