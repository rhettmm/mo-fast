package wang.momo.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 23:17
 */
@Data
@Accessors(chain = true)
public class MoException extends RuntimeException implements Serializable {
    public MoException() {
    }

    public MoException(String message) {
        super(message);
    }

    public MoException(String message, Throwable cause) {
        super(message, cause);
    }
}
