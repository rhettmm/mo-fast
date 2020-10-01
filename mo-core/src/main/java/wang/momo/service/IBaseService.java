package wang.momo.service;

import wang.momo.entity.BaseEntity;


/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 1:08
 */
public interface IBaseService<T extends BaseEntity> {

    int insert(T t);

    int deleteById(int id);

    int updateById(T t);


}
