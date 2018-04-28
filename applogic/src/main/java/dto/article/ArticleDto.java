package dto.article;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Article;

import java.util.Date;


@Data
@NoArgsConstructor
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