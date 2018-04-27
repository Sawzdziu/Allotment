package model.entity;

//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idArticle")
public class Article {
    private Integer idArticle;
    private String title;
    private String text;
    private Date date;
    private String author;
    private User user;
    private Collection<Commentary> commentaries;

    @Id
    @Column(name = "id_article")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate(){return date;}

    public void setDate(Date date){
        this.date = date;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (idArticle != null ? !idArticle.equals(article.idArticle) : article.idArticle != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (text != null ? !text.equals(article.text) : article.text != null) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArticle != null ? idArticle.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "article")
    public Collection<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(Collection<Commentary> commentaries) {
        this.commentaries = commentaries;
    }
}
