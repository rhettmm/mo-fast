package wang.momo.util.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 23:36
 */
public class LocalCacheUtil extends AbsolutyCacheUtil {

    private static  Map<String,Object> cacheMap;
    private static  Map<String,Long> expireMap;

    static {
        cacheMap=new HashMap<>();
        expireMap=new HashMap<>();
    }

    @Override
    void setCache(String key, Object value) {
        cacheMap.put(key,value);
    }

    @Override
    Object getCache(String key) {
        Object value=null;
        if( expireMap.get(key)!=null  ){
            if( expireMap.get(key)>=System.currentTimeMillis()){
                value=cacheMap.get(key);
            }else {
                cacheMap.remove(key);
            }
        }else {
            value=cacheMap.get(key);
        }
        return value;
    }

    @Override
    void setCache(String key, Object value, long expireTime) {
        cacheMap.put(key,value);
        expireMap.put(key,expireTime);
    }

    @Override
    void delCache(String key) {
        cacheMap.remove(key);
        expireMap.remove(key);
    }
}
