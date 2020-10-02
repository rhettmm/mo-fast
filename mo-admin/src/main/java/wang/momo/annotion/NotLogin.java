package wang.momo.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要登录注解，例如接口
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/3 1:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NotLogin {
    boolean value() default true;
}
