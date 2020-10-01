package wang.momo.moadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 23:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TestUser {

    private String account;
    private String name;
    private int age;
}
