package dto;

import lombok.Data;
import model.entity.Article;

import java.util.Date;


@Data
public class ArticleDto {

    private Integer idArticle;
    private String author;
    private Date date;
    private String title;
    private String text;

    public ArticleDto(Article article) {
        this.idArticle = article.getIdArticle();
        this.author = article.getAuthor();
        this.date = article.getDate();
        this.title = article.getTitle();
        this.text = article.getText();
    }
}
