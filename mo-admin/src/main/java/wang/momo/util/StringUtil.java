package wang.momo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.expression.Strings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 23:27
 */
public class StringUtil {

    private static ObjectMapper objectMapper;
    private static Pattern linePattern=Pattern.compile("_(\\w)");
    private static Pattern humpPattern=Pattern.compile("[A-Z]");



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

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String HumpToline(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static  String lineToHump(String str){
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
