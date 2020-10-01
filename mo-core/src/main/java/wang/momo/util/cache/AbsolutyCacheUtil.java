package wang.momo.util.cache;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 23:29
 */
public abstract class AbsolutyCacheUtil {
    /**
     * 保存缓存
     * @param key
     * @param value
     */
     abstract  void setCache(String key, Object value);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    abstract Object getCache(String key);

    /**
     * 缓存  有效期
     * @param key
     * @param value
     * @param expireTime
     */
    abstract void setCache(String key,Object value, long expireTime);

    /**
     * 删除缓存
     * @param key
     */
    abstract void delCache(String key);

}
