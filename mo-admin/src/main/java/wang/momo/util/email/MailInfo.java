package wang.momo.util.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/19 0:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MailInfo {
    private String from;
    private String to;
    private String subject;
    private boolean flag;           //是否为Html内容
    private String text;
    private String inlineName;
    private File inline;            //内嵌图片
    private String attachmentName;
    private File attachment;        //附件支持多种类型  file\inputstream
}
