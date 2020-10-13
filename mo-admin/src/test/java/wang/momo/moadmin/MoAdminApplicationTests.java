package wang.momo.moadmin;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import wang.momo.moadmin.entity.TestUser;
import wang.momo.moadmin.entity.dto.TestUserDto;
import wang.momo.util.BeanUtil;
import wang.momo.util.EncryUtil;
import wang.momo.util.StringUtil;

import javax.management.Query;
import java.lang.reflect.Field;

@SpringBootTest
class MoAdminApplicationTests {

    @Test
    void contextLoads() throws Exception {
        String momo520 = EncryUtil.encryptByMD5("momo520");
        System.out.println(momo520);
    }


    @Test
    public void test1() throws IllegalAccessException {
        TestUser testUser = new TestUser();
        testUser.setAccount("1001").setCreateAccount("mo1001");

        Field[] declaredFields = testUser.getClass().getDeclaredFields();
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            if(field.get(testUser)==null){
                System.out.println(field.getName()+"属性为空！");
            }else {
                wrapper.eq(StringUtil.HumpToline(field.getName()),field.get(testUser));
                System.out.println(StringUtil.HumpToline(field.getName())+"\t"+field.get(testUser));
            }
        }

        System.out.println(wrapper.toString());
    }



    @Test
    public void test2() throws IllegalAccessException {
        TestUser testUser = new TestUser();
        TestUserDto testUserDto = new TestUserDto();
        testUser.setId(1)
                .setAccount("mo520")
                .setName("我爱你")
                .setUpdateAccount("mo1001")
                .setCreateAccount("mo1001");

        BeanUtil.convertSameAttr(testUser,testUserDto);

        System.out.println(testUserDto.toString());


    }


}
