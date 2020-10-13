package wang.momo.moadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 23:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("test_user")
public class TestUser {

    private Integer id;
    private String account;
    private String name;
    private Integer age;

    private String createAccount;
    private Date createTime;
    private String updateAccount;
    private Date updateTime;
}
