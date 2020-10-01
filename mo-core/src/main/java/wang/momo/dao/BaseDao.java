package wang.momo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wang.momo.entity.BaseEntity;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 1:09
 */
public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {

}
