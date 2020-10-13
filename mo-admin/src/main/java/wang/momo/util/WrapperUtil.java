package wang.momo.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/13 0:37
 */
public class WrapperUtil {

    public static Wrapper voluation(QueryWrapper wrapper, Object object) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if(field.get(object)!=null){
                wrapper.eq(StringUtil.HumpToline(field.getName()),field.get(object));
            }
        }
        return wrapper;
    }
}
