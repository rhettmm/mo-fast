package wang.momo.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/2 0:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NotMsgHandle {
    boolean value() default true;
}
