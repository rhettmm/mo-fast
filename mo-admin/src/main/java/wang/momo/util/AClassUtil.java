package wang.momo.util;

import java.lang.reflect.Field;

/**
 * 类工具
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:07
 */
public class AClassUtil {

    /**
     * 判断类是否保存某个属性
     * @return
     */
    public static boolean containField(String field,Class aClass){
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(declaredField.getName().equals(field)){
                return true;
            }
        }
        return false;
    }

    /**
     * 设置对象属性值
     * @param o
     * @param fieldName
     * @param fieldValue
     */
    public static void setField(Object o,String fieldName,Object fieldValue)  {
        try {
            Class<?> aClass = o.getClass();
            if(containField(fieldName,aClass)){
                Field declaredField = aClass.getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                declaredField.set(o,fieldValue);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }




}
