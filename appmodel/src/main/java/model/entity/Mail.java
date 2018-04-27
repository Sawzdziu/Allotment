package model.entity;

//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idMail")
public class Mail {
    private Long idMail;
    private String subject;
    private User userSender;
    @PrimaryKeyJoinColumn
    private Mailbody mailBody;
    private Collection<Recipient> recipients;

    @Id
    @Column(name = "id_mail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail = (Mail) o;

        if (idMail != null ? !idMail.equals(mail.idMail) : mail.idMail != null) return false;
        if (subject != null ? !subject.equals(mail.subject) : mail.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMail != null ? idMail.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_sender_id", referencedColumnName = "id_user", nullable = false)
    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mail")
    public Mailbody getMailBody() {
        return mailBody;
    }

    public void setMailBody(Mailbody mailBody) {
        this.mailBody = mailBody;
    }

    @OneToMany(mappedBy = "mail")
    public Collection<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Collection<Recipient> recipients) {
        this.recipients = recipients;
    }
}
