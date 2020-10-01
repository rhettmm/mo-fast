package wang.momo.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 23:51
 */
@Component
public class RedisCacheUtil extends AbsolutyCacheUtil{

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    void setCache(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    Object getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    void setCache(String key, Object value, long expireTime) {
        redisTemplate.opsForValue().set(key,value,expireTime);
    }

    @Override
    void delCache(String key) {
        redisTemplate.delete(key);
    }

}
