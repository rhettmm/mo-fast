package wang.momo.util.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/19 0:41
 */
public class CacheFactory {
    private static Map<String,AbsolutyCacheUtil> map;
    static {
        map=new HashMap<>();
        map.put(CacheType.local,new LocalCacheUtil());
        map.put(CacheType.redis,new RedisCacheUtil());
    }
    public static AbsolutyCacheUtil getCacheUtil(String CacheType){
        return map.get(CacheType);
    }
    public interface CacheType{
        String local="local";
        String redis="redis";
    }


}
