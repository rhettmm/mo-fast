package wang.momo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 1:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;
    private Date createTime;
    private String createAccount;
    private Date updateTime;
    private String updateAccount;
}
