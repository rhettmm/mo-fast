package wang.momo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 23:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private T data;
    /**
     * 结果代码集
     */
    public interface CodeList{
        String OK="200";
        String FAILED="301";
        String GONE="404";
        String ERROR="500";
    }
}
