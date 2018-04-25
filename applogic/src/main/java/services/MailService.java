package services;

import dto.mail.MailBodyDto;
import dto.mail.MailDto;
import dto.mail.NewMailDto;
import model.dao.MailRepositoryDAO;
import model.dao.RecipientRepositoryDAO;
import model.dao.UserRepositoryDAO;
import model.entity.Mail;
import model.entity.Mailbody;
import model.entity.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by piotrsa on 25/04/18.
 */
@Service
public class MailService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private RecipientRepositoryDAO recipientRepositoryDAO;

    @Autowired
    private MailRepositoryDAO mailRepositoryDAO;

    @Autowired
    private AuthenticationService authenticationService;

    public List<MailDto> getLastFiveMails() {
        return mapToMailDto(recipientRepositoryDAO.findLastFiveMailsForUser(userRepositoryDAO.findByUsername(authenticationService.getUsername())));
    }

    public List<MailBodyDto> getAllMails() {
        return mapToMailBodyDto(recipientRepositoryDAO.findAllMailsForUser(userRepositoryDAO.findByUsername(authenticationService.getUsername())));
    }

    public void createNewMail(NewMailDto newMailDto) {
        Mail mail = new Mail();
        mail.setSubject(newMailDto.getSubject());


        Mailbody mailBody = new Mailbody();
        mailBody.setText(newMailDto.getText());
        mail.setMailBody(mailBody);
        mailBody.setMail(mail);

    }

    private void persistMail(Mail mail){
        mailRepositoryDAO.save(mail);
    }

    private List<MailDto> mapToMailDto(Collection<Recipient> receivedMails) {
        return receivedMails.stream().map(recipient -> new MailDto(recipient.getMail(), recipient.getSeen())).collect(Collectors.toList());
    }

    private List<MailBodyDto> mapToMailBodyDto(Collection<Recipient> receivedMails) {
        return receivedMails.stream().map(recipient -> new MailBodyDto(recipient.getMail(), recipient.getSeen(), recipient.getMail().getMailBody().getText())).collect(Collectors.toList());
    }
}
