package servicesTest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import dto.article.CommentaryDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import services.CommentaryService;
import services.TestServicesConfiguration;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServicesConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:commentaryServiceTest.xml")
@DatabaseTearDown(value = "classpath:commentaryServiceTest.xml", type = DatabaseOperation.DELETE_ALL)
public class CommentaryServiceTest extends AuthenticationSetup {

    @Autowired
    private CommentaryService commentaryService;

    @Test
    public void addCommentary() {
        CommentaryDto commentaryDto = new CommentaryDto();
        commentaryDto.setIdArticle(1);
        commentaryDto.setText("Test commentary");

        commentaryService.addCommentary(commentaryDto);
        List<CommentaryDto> commentaryDtoList = commentaryService.getCommentariesFromArticle(1);

        Assert.assertEquals("Size of commentaries for article", 3, commentaryDtoList.size());
        Assert.assertEquals("Id article", new Integer(1), commentaryDtoList.get(2).getIdArticle());
        Assert.assertEquals("Author of commentary", "Adam Kowalski", commentaryDtoList.get(2).getAuthor());
        Assert.assertEquals("Text of commentary", "Test commentary", commentaryDtoList.get(2).getText());
        Assert.assertEquals("Id commentary", new Integer(5), commentaryDtoList.get(2).getIdCommentary());
    }

    @Test
    public void editCommentary() {
        CommentaryDto commentaryDto = new CommentaryDto();
        commentaryDto.setIdArticle(1);
        commentaryDto.setIdCommentary(1);
        commentaryDto.setText("Test commentary");

        commentaryService.editCommentary(commentaryDto);
        List<CommentaryDto> commentaryDtoList = commentaryService.getCommentariesFromArticle(1);

        Assert.assertEquals("Size of commentaries for article", 2, commentaryDtoList.size());
        Assert.assertEquals("Id article", new Integer(1), commentaryDtoList.get(1).getIdArticle());
        Assert.assertEquals("Author of commentary", "First author", commentaryDtoList.get(1).getAuthor());
        Assert.assertEquals("Text of commentary", "Test commentary", commentaryDtoList.get(1).getText());
        Assert.assertEquals("Id commentary", new Integer(1), commentaryDtoList.get(1).getIdCommentary());
    }

    @Test(expected = AccessDeniedException.class)
    public void editCommentaryException() {
        CommentaryDto commentaryDto = new CommentaryDto();
        commentaryDto.setIdArticle(1);
        commentaryDto.setIdCommentary(2);
        commentaryDto.setText("Test commentary");

        commentaryService.editCommentary(commentaryDto);

    }
}
