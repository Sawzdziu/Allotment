package servicesTest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import dto.article.ArticleDto;
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
import services.ArticleService;
import services.CommentaryService;
import services.TestServicesConfiguration;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServicesConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:articleServiceTest.xml")
@DatabaseTearDown(value = "classpath:articleServiceTest.xml", type = DatabaseOperation.DELETE_ALL)
public class ArticleServiceTest extends AuthenticationSetup{

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentaryService commentaryService;

    @Test
    public void getAllArticles(){
        List<ArticleDto> articleDtoList = articleService.getAllArticles();

        Assert.assertEquals("Size of all articles", 4, articleDtoList.size());
        Assert.assertEquals("Title of first article", "Title first", articleDtoList.get(0).getTitle());
        Assert.assertEquals("Text of first article", "First text", articleDtoList.get(0).getText());
        Assert.assertEquals("Author of first article", "Author first", articleDtoList.get(0).getAuthor());
    }

    @Test
    public void getLastFiveArticles(){
        List<ArticleDto> articleDtoList = articleService.getLastFiveArticles();

        Assert.assertEquals("Size of all articles", 4, articleDtoList.size());
        Assert.assertEquals("Title of first article", "Title fourth", articleDtoList.get(0).getTitle());
        Assert.assertEquals("Text of first article", "Fourth text", articleDtoList.get(0).getText());
        Assert.assertEquals("Author of first article", "Author fourth", articleDtoList.get(0).getAuthor());
        Assert.assertEquals("Id of first article", new Integer(4), articleDtoList.get(0).getIdArticle());
    }

    @Test
    public void createNewArticle(){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setText("Test");
        articleDto.setTitle("Title test");

        articleService.createNewArticle(articleDto);
        List<ArticleDto> articleDtoList = articleService.getLastFiveArticles();

        Assert.assertEquals("Size of all articles", 5, articleDtoList.size());
        Assert.assertEquals("Title of first article", "Title test", articleDtoList.get(0).getTitle());
        Assert.assertEquals("Text of first article", "Test", articleDtoList.get(0).getText());
        Assert.assertEquals("Author of first article", "Adam Kowalski", articleDtoList.get(0).getAuthor());
        Assert.assertEquals("Id of first article", new Integer(5), articleDtoList.get(0).getIdArticle());
    }

    @Test
    public void editArticle(){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setIdArticle(1);
        articleDto.setText("Test");
        articleDto.setTitle("Title test");

        articleService.editArticle(articleDto);
        List<ArticleDto> articleDtoList = articleService.getAllArticles();

        Assert.assertEquals("Size of all articles", 4, articleDtoList.size());
        Assert.assertEquals("Title of first article", "Title test", articleDtoList.get(0).getTitle());
        Assert.assertEquals("Text of first article", "Test", articleDtoList.get(0).getText());
        Assert.assertEquals("Author of first article", "Author first", articleDtoList.get(0).getAuthor());
        Assert.assertEquals("Id of first article", new Integer(1), articleDtoList.get(0).getIdArticle());
    }

    @Test(expected = AccessDeniedException.class)
    public void editArticleException(){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setIdArticle(2);
        articleDto.setText("Test");
        articleDto.setTitle("Title test");

        articleService.editArticle(articleDto);
    }

    @Test
    public void deleteArticle(){
        articleService.deleteArticle(1);
        List<ArticleDto> articleDtoList = articleService.getAllArticles();

        Assert.assertEquals("Size of all articles", 3, articleDtoList.size());
        Assert.assertEquals("Title of first article", "Title second", articleDtoList.get(0).getTitle());
        Assert.assertEquals("Text of first article", "Second text", articleDtoList.get(0).getText());
        Assert.assertEquals("Author of first article", "Author second", articleDtoList.get(0).getAuthor());
        Assert.assertEquals("Id of first article", new Integer(2), articleDtoList.get(0).getIdArticle());
    }

    @Test(expected = AccessDeniedException.class)
    public void deleteArticleException(){
        articleService.deleteArticle(2);
    }

}
