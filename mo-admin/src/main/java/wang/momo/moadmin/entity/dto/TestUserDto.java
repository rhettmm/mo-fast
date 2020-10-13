package wang.momo.moadmin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import wang.momo.moadmin.entity.TestUser;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/14 0:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class TestUserDto extends TestUser {
    private String otherAttr;
}
