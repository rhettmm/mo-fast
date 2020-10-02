package wang.momo.annotion;

/**
 * 不校验权限注解
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/3 2:05
 */
public @interface NotPermission {
    boolean value() default true;
}
