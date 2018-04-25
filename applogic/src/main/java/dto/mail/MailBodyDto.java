package dto.mail;

import lombok.Data;
import model.entity.Mail;

/**
 * Created by piotrsa on 25/04/18.
 */
@Data
public class MailBodyDto extends MailDto {

    private String text;

    public MailBodyDto(Mail mail, Boolean seen, String text) {
        super(mail, seen);
        this.text = text;
    }
}
