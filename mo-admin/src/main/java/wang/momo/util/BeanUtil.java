package wang.momo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * javaBean转换工具
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/14 0:47
 */
public class BeanUtil {

    /**
     * 相同属性转换
     * @param from
     * @param to
     */
    public static void convertSameAttr(Object from,Object to) throws IllegalAccessException {
        List<Field> fromfields = getAllFields(from);
        List<Field> toFields = getAllFields(to);
        for (Field fromfield : fromfields) {
            for (Field toField : toFields) {
                fromfield.setAccessible(true);
                toField.setAccessible(true);
                if(fromfield.getName().equals(toField.getName()) && fromfield.getType().equals(toField.getType())){
                    toField.set(to,fromfield.get(from));
                    break;
                }
            }
        }
    }

    /**
     * 获取一个类的所有属性，包含父类属性
     * @param obj
     * @return
     */
    public static List<Field> getAllFields(Object obj){
        ArrayList<Field> fieldList = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        while(clazz!=null){
            Field[] declaredFields = clazz.getDeclaredFields();
            ArrayList<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
            fieldList.addAll(fields);
            clazz=clazz.getSuperclass();
        }
        return fieldList;
    }
}
