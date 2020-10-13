package wang.momo.moadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.momo.moadmin.dao.TestUserMapper;
import wang.momo.moadmin.entity.TestUser;
import wang.momo.moadmin.service.TestUserService;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/12 22:58
 */
@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements TestUserService {
}
