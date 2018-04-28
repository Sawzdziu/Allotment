package dto.article;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Commentary;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentaryDto {

    private Integer idCommentary;
    private String author;
    private String text;
    private Integer idArticle;
    private Date date;

    public CommentaryDto(Commentary commentary) {
        this.idCommentary = commentary.getIdCommentary();
        this.author = commentary.getAuthor();
        this.text = commentary.getText();
        this.idArticle = commentary.getArticle().getIdArticle();
        this.date = commentary.getDate();
    }
}
