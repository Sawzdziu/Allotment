package services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import model.dao.PaymentRepositoryDAO;
import model.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;

@Service
@Slf4j
public class PDFService {

    @Autowired
    private PaymentRepositoryDAO paymentRepositoryDAO;

    @Autowired
    private Seller seller;

    @Autowired
    private CreateTable createTable;

    public ByteArrayOutputStream createDocument(Integer idPayment) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Payment payment = paymentRepositoryDAO.getByPaymentId(idPayment);
        Document document = new Document(PageSize.A4, 36, 20, 36, 20);
        document.addTitle("ROD Obrońca rachunek");
        document.addHeader("Expires", "0");

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            Font font = new Font(Font.FontFamily.COURIER, 12);

            document.add(seller.createSeller());
            document.add(createTable.createHeader(payment.getAllotmentUserByAllotmentUserId().getUser()));
            document.add(createTable.createData(payment));
            document.add(createTable.createSum(payment));
            Paragraph paragraphDots = new Paragraph(".................................", font);
            paragraphDots.setAlignment(Element.ALIGN_RIGHT);
            Paragraph paragraphSingature = new Paragraph("Podpis", font);
            paragraphSingature.setAlignment(Element.ALIGN_RIGHT);
            document.add(new Paragraph());
            document.add(paragraphDots);
            document.add(paragraphSingature);
        } catch (Exception e) {
            log.error(e.getMessage());
        }


        document.close();
        return byteArrayOutputStream;
    }
}
