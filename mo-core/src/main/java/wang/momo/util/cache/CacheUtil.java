package wang.momo.util.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 缓存工具类
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/19 0:37
 */
@Component
public class CacheUtil {

    @Value("${mo.admin.cacheType}")
    private  String cacheType;
    /**
     * 保存缓存
     * @param key
     * @param value
     */
    public void setCache(String key, Object value){
        CacheFactory.getCacheUtil(cacheType).setCache(key,value);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object getCache(String key){
        return CacheFactory.getCacheUtil(cacheType).getCache(key);
    }

    /**
     * 缓存  有效期
     * @param key
     * @param value
     * @param expireTime
     */
    public void setCache(String key,Object value, long expireTime){
        CacheFactory.getCacheUtil(cacheType).setCache(key,value,expireTime);
    }

    /**
     * 删除缓存
     * @param key
     */
    public void delCache(String key){
        CacheFactory.getCacheUtil(cacheType).delCache(key);
    }

}
