package model.entity;

import javax.persistence.*;

@Entity
public class Mailbody {
    private Integer mailId;
    private String text;
    private Mail mail;

    @Id
    @Column(name = "mail_id")
    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
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

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "mail_id", referencedColumnName = "id_mail", nullable = false)
    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
