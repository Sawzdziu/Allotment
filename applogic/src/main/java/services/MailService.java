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
import model.entity.User;
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


    /**
     * Function returns last five obtained mails for logged user.
     *
     * @return List with last five mails for specified user
     */
    public List<MailDto> getLastFiveMails() {
        return mapToMailDto(recipientRepositoryDAO.findLastFiveMailsForUser(userRepositoryDAO.findByUsername(authenticationService.getUsername())));
    }

    /**
     * Function return List<MailBodyDto> containing all mails for logged user.
     *
     * @return List with all mails for specified user.
     */
    public List<MailBodyDto> getAllMails() {
        return mapToMailBodyDto(recipientRepositoryDAO.findAllMailsForUser(getUser()));
    }

    /**
     * Function set recipient as seen by user.
     *
     * @param idMail of Mail which user on frontend see.
     */
    public void seen(Long idMail) {
        Recipient recipient = recipientRepositoryDAO.findRecipientByUserAndIdMail(getUser(), getMailById(idMail));
        recipient.setSeen(true);

        recipientRepositoryDAO.persistRecipient(recipient);
    }

    /**
     * Method creates new Mail and Mailbody, then create Recipient entity's for each User
     *
     * @param newMailDto
     */
    public void createNewMail(NewMailDto newMailDto) {
        Mail mail = new Mail();
        mail.setSubject(newMailDto.getSubject());
        mail.setUserSender(getUser());

        //Set text of mail
        Mailbody mailBody = new Mailbody();
        mailBody.setText(newMailDto.getText());

        //Set mailbody for mail
        mail.setMailBody(mailBody);
        mailBody.setMail(mail);

        //persist mail
        persistMail(mail);

        //persist all recipients
        persistRecipients(newMailDto.getReceivers(), mail);
    }

    private void persistMail(Mail mail) {
        mailRepositoryDAO.save(mail);
    }

    /**
     * Method iterates through users id and create recipient object with id of mail
     *
     * @param ids List of users ids which obtains mails
     * @param mail Mail which was send to specified users
     */
    private void persistRecipients(List<Integer> ids, Mail mail) {
        ids.forEach(id -> saveRecipient(id, mail));
    }

    private void saveRecipient(Integer id, Mail mail) {
        Recipient recipient = new Recipient();
        recipient.setMail(mail);
        recipient.setUserReciever(userRepositoryDAO.findById(id));
        recipient.setSeen(false);

        recipientRepositoryDAO.persistRecipient(recipient);
    }

    private Mail getMailById(Long idMail) {
        return mailRepositoryDAO.findByIdMail(idMail);
    }

    private User getUser() {
        return userRepositoryDAO.findByUsername(authenticationService.getUser().getUsername());
    }

    private List<MailDto> mapToMailDto(Collection<Recipient> receivedMails) {
        return receivedMails.stream().map(recipient -> new MailDto(recipient.getMail(), recipient.getSeen())).collect(Collectors.toList());
    }

    private List<MailBodyDto> mapToMailBodyDto(Collection<Recipient> receivedMails) {
        return receivedMails.stream().map(recipient -> new MailBodyDto(recipient.getMail(), recipient.getSeen(), recipient.getMail().getMailBody().getText())).collect(Collectors.toList());
    }
}
