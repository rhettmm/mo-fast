package wang.momo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 23:27
 */
public class StringUtil {

    private static ObjectMapper objectMapper;
    static {
        objectMapper=new ObjectMapper();
    }

    /**
     * 判断字符串是否为空
     * @param target
     * @return
     */
    public static boolean isEmpty(String target){
        if(target == null || "".equalsIgnoreCase(target.trim()))
            return true;
        return false;
    }

    /**
     * 日期转字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String parseDate2String(Date date,String pattern){
        pattern = (pattern == null) ? "yyyyMMdd" : pattern;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * java Bean转字符串
     * @param bean
     * @return
     */
    public static String parseBean2Json(Object bean){
        String json=null;
        try {
            json = objectMapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json字符串转javaBean
     * @param target
     * @param clazz
     * @return
     */
    public static Object parseString2Map(String target,Class clazz){
        Object object=null;
        try {
            object = objectMapper.readValue(target, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return clazz.cast(object);
    }
}
