package wang.momo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.momo.dao.BaseDao;
import wang.momo.entity.BaseEntity;
import wang.momo.service.IBaseService;



/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 1:09
 */
@Service
public class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {
    @Autowired
    private BaseDao<T> baseDao;


    @Override
    public int insert(T t) {
        return baseDao.insert(t);
    }

    @Override
    public int deleteById(int id) {
        return baseDao.deleteById(id);
    }

    @Override
    public int updateById(T t) {
        return baseDao.updateById(t);
    }


}
