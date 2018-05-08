package dto.article;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Article;
import model.entity.Commentary;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Data
@NoArgsConstructor
public class ArticleDto {

    private Integer idArticle;
    private String author;
    private Date date;
    private String title;
    private String text;
    private List<CommentaryDto> commentaryDtoList;

    public ArticleDto(Article article) {
        this.idArticle = article.getIdArticle();
        this.author = article.getAuthor();
        this.date = article.getDate();
        this.title = article.getTitle();
        this.text = article.getText();
        this.commentaryDtoList = new LinkedList<>();
    }
}
