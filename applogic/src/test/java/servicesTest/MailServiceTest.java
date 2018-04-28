package servicesTest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import dto.mail.MailBodyDto;
import dto.mail.MailDto;
import dto.mail.NewMailDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import services.MailService;
import services.TestServicesConfiguration;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServicesConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:mailServiceTest.xml")
@DatabaseTearDown(value = "classpath:mailServiceTest.xml", type = DatabaseOperation.DELETE_ALL)
public class MailServiceTest extends AuthenticationSetup {

    @Autowired
    private MailService mailService;

    @Test
    public void getLastFiveMails() {
        List<MailDto> mailDtoList = mailService.getLastFiveMails();

        Assert.assertEquals("Number of emails", 2, mailDtoList.size());
        Assert.assertEquals("Subject of first email", "Temat drugi", mailDtoList.get(0).getSubject());
        Assert.assertEquals("Id of first email", new Long(2), mailDtoList.get(0).getIdMail());
        Assert.assertEquals("Subject of second email", "Temat pierwszy", mailDtoList.get(1).getSubject());
        Assert.assertEquals("Id of second email", new Long(1), mailDtoList.get(1).getIdMail());
    }

    @Test
    public void getAllMails() {
        List<MailBodyDto> mailBodyDtoList = mailService.getAllMails();

        Assert.assertEquals("Number of emails", 2, mailBodyDtoList.size());
        Assert.assertEquals("Subject of first email", "Temat pierwszy", mailBodyDtoList.get(0).getSubject());
        Assert.assertEquals("Id of first email", new Long(1), mailBodyDtoList.get(0).getIdMail());
        Assert.assertEquals("Text of first email", "Tekst pierwszy", mailBodyDtoList.get(0).getText());
        Assert.assertEquals("Subject of second email", "Temat drugi", mailBodyDtoList.get(1).getSubject());
        Assert.assertEquals("Id of second email", new Long(2), mailBodyDtoList.get(1).getIdMail());
        Assert.assertEquals("Text of second email", "Tekst drugi", mailBodyDtoList.get(1).getText());
    }

    @Test
    public void createNewMail() {
        NewMailDto newMailDto = new NewMailDto();
        newMailDto.setText("New added mail");
        newMailDto.setSubject("New subject");
        newMailDto.setReceivers(Arrays.asList(1, 2, 3));

        mailService.createNewMail(newMailDto);
        List<MailBodyDto> mailBodyDtoList = mailService.getAllMails();

        Assert.assertEquals("Number of emails", 3, mailBodyDtoList.size());
        Assert.assertEquals("Subject of first email", "New subject", mailBodyDtoList.get(2).getSubject());
        Assert.assertEquals("Id of first email", new Long(3), mailBodyDtoList.get(2).getIdMail());
        Assert.assertEquals("Text of first email", "New added mail", mailBodyDtoList.get(2).getText());
    }
}
