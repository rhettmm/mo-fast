package wang.momo.common;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 前后端分离同意消息返回
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MoResult<T> {
    private int code;
    private String msg;
    private T data;
}
