package model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idUser")
public class User {
    private Integer idUser;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String username;
    private Boolean isActive;
//    private Collection<Article> articles;
//    private Collection<Commentary> commentaries;
//    private Collection<Mail> sendMails;
//    private Collection<Recipient> recievedMails;
    private Role role;

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != null ? !idUser.equals(user.idUser) : user.idUser != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (isActive != null ? !isActive.equals(user.isActive) : user.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    public Collection<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(Collection<Article> articles) {
//        this.articles = articles;
//    }
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    public Collection<Commentary> getCommentaries() {
//        return commentaries;
//    }
//
//    public void setCommentaries(Collection<Commentary> commentaries) {
//        this.commentaries = commentaries;
//    }
//
//    @OneToMany(mappedBy = "userSender", fetch = FetchType.LAZY)
//    public Collection<Mail> getSendMails() {
//        return sendMails;
//    }
//
//    public void setSendMails(Collection<Mail> sendMails) {
//        this.sendMails = sendMails;
//    }
//
//    @OneToMany(mappedBy = "userReciever", fetch = FetchType.LAZY)
//    public Collection<Recipient> getRecievedMails() {
//        return recievedMails;
//    }
//
//    public void setRecievedMails(Collection<Recipient> recievedMails) {
//        this.recievedMails = recievedMails;
//    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id_role", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
