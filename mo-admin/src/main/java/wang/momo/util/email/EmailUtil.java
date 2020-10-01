package wang.momo.util.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/19 0:50
 */
@Component
public class EmailUtil {
    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     */
    public void sendEmail(MailInfo mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(mail.getFrom());
        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setText(mail.getText(),mail.isFlag());
        if(mail.getInline()!=null)
            mimeMessageHelper.addInline(mail.getInlineName(),mail.getInline());
        if(mail.getAttachment()!=null)
            mimeMessageHelper.addAttachment(mail.getAttachmentName(),mail.getAttachment());
        //发送邮件
        mailSender.send(mimeMessage);
    }

}
