package dto.mail;

import lombok.Data;
import model.entity.Mail;
import model.entity.User;

/**
 * Created by piotrsa on 25/04/18.
 */
@Data
public class MailDto {

    private Long idMail;
    private String subject;
    private User userSender;
    private Boolean seen;

    public MailDto(Mail mail, Boolean seen) {
        this.idMail = mail.getIdMail();
        this.subject = mail.getSubject();
        this.userSender = mail.getUserSender();
        this.seen = seen;
    }
}
