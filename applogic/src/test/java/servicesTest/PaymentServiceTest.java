package servicesTest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import dto.payment.AddPaymentDto;
import dto.payment.PaymentDto;
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
import services.PaymentService;
import services.TestServicesConfiguration;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServicesConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:paymentServiceTest.xml")
@DatabaseTearDown(value = "classpath:paymentServiceTest.xml", type = DatabaseOperation.DELETE_ALL)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void newPayment() {
        AddPaymentDto addPaymentDto = new AddPaymentDto();
        addPaymentDto.setCharge(100);
        addPaymentDto.setTitle("Test payment");
        addPaymentDto.setUserId(3);

        paymentService.createPayment(addPaymentDto);
        List<PaymentDto> paymentDtoList = paymentService.getAllPayments();

        Assert.assertEquals("Size of all payments", 7, paymentDtoList.size());
        Assert.assertEquals("Id of added payment", new Integer(7), paymentDtoList.get(6).getIdPayment());
        Assert.assertEquals("Title of added payment", "Test payment", paymentDtoList.get(6).getTitle());
        Assert.assertEquals("Charge of added payment", new Integer(100), paymentDtoList.get(6).getCharge());
        Assert.assertEquals("User id of added payment", new Integer(3), paymentDtoList.get(6).getUserDto().getIdUser());
        Assert.assertEquals("Allotment id of added payment", new Integer(3), paymentDtoList.get(6).getAllotmentDto().getIdAllotment());
    }

    @Test
    public void editPayment() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setIdPayment(1);
        paymentDto.setCharge(100);
        paymentDto.setTitle("Test payment");
        paymentDto.setPaid(true);

        paymentService.updatePayment(paymentDto);
        List<PaymentDto> paymentDtoList = paymentService.getAllPayments();

        Assert.assertEquals("Size of all payments", 6, paymentDtoList.size());
        Assert.assertEquals("Id of first payment", new Integer(1), paymentDtoList.get(0).getIdPayment());
        Assert.assertEquals("Title of added payment", "Test payment", paymentDtoList.get(0).getTitle());
        Assert.assertEquals("Charge of added payment", new Integer(100), paymentDtoList.get(0).getCharge());
        Assert.assertTrue("Payment is paid", paymentDtoList.get(0).getPaid());
        Assert.assertEquals("User id of added payment", new Integer(1), paymentDtoList.get(0).getUserDto().getIdUser());
        Assert.assertEquals("Allotment id of added payment", new Integer(1), paymentDtoList.get(0).getAllotmentDto().getIdAllotment());
    }

    @Test
    public void deletePayment() {
        paymentService.deletePayment(1);
        List<PaymentDto> paymentDtoList = paymentService.getAllPayments();

        Assert.assertEquals("Size of all payments", 5, paymentDtoList.size());
        Assert.assertEquals("Id of first payment", new Integer(2), paymentDtoList.get(0).getIdPayment());
        Assert.assertEquals("Title of added payment", "Title second", paymentDtoList.get(0).getTitle());
        Assert.assertEquals("Charge of added payment", new Integer(230), paymentDtoList.get(0).getCharge());
        Assert.assertTrue("Payment is paid", paymentDtoList.get(0).getPaid());
    }
}
