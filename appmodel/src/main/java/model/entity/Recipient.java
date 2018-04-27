package model.entity;

//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idRecipient")
public class Recipient {
    private Long idRecipient;
    private Boolean seen;
    private Mail mail;
    private User userReciever;

    @Id
    @Column(name = "id_recipient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(Long idRecipient) {
        this.idRecipient = idRecipient;
    }


    @Basic
    @Column(name = "seen")
    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipient recipient = (Recipient) o;

        if (idRecipient != null ? !idRecipient.equals(recipient.idRecipient) : recipient.idRecipient != null)
            return false;
        if (seen != null ? !seen.equals(recipient.seen) : recipient.seen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecipient != null ? idRecipient.hashCode() : 0;
        result = 31 * result + (seen != null ? seen.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mail_id", referencedColumnName = "id_mail", nullable = false)
    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    @ManyToOne
    @JoinColumn(name = "user_reciver_id", referencedColumnName = "id_user", nullable = false)
    public User getUserReciever() {
        return userReciever;
    }

    public void setUserReciever(User userReciever) {
        this.userReciever = userReciever;
    }
}
