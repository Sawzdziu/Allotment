package dto.mail;

import lombok.Data;

import java.util.List;

/**
 * Created by piotrsa on 25/04/18.
 */
@Data
public class NewMailDto {

    private String subject;
    private List<Integer> receivers;
    private String text;

}
