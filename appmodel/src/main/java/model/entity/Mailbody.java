package model.entity;

//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="mailId")
public class Mailbody {
    private Long mailId;
    private String text;
    private Mail mail;

    @Id
    @Column(name = "mail_id")
    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mailbody mailbody = (Mailbody) o;

        if (text != null ? !text.equals(mailbody.text) : mailbody.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mail_id", referencedColumnName = "id_mail", nullable = false)
    @MapsId
    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
