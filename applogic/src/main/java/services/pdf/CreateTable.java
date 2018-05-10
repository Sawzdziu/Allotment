package services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import model.entity.Payment;
import model.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
class CreateTable {

    public PdfPTable createHeader(User user) {
        Font f = getFont();

        float[] widths = {5, 30, 5, 5};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100f);
        PdfPCell header = new PdfPCell(new Phrase("Rachunek:"));
        header.setPadding(5);
        header.setColspan(4);
        table.addCell(header);
        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.getDefaultCell().setPaddingBottom(3);
        table.getDefaultCell().setPaddingTop(2);
        table.getDefaultCell().setPaddingLeft(3);
        table.getDefaultCell().setPaddingRight(3);
        table.getDefaultCell().setBorderWidth(1);

        Phrase id = new Phrase("Id.", f);
        Phrase title = new Phrase("Tytuł opłaty", f);
        Phrase userToPay = new Phrase("Numer działki", f);
        Phrase charge = new Phrase("Wartość brutto [zł]", f);


        table.addCell(id);
        table.addCell(title);
        table.addCell(userToPay);
        table.addCell(charge);

        return table;
    }

    public PdfPTable createData(Payment payment) {
        Font f = getFont();
        f.setSize(12);
        float[] widths = {5, 30, 5, 5};
        PdfPTable table = new PdfPTable(widths);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setPaddingRight(4);
        table.setWidthPercentage(100f);

        Phrase id = new Phrase(payment.getIdPayment().toString(), f);
        Phrase title = new Phrase(payment.getTitle(), f);
        Phrase userToPay = new Phrase(payment.getAllotmentUserByAllotmentUserId().getAllotment().getNumber().toString(), f);
        Phrase charge = new Phrase(String.format("%.2f", payment.getCharge()) + " zł", f);

        table.addCell(id);
        table.addCell(title);
        table.addCell(userToPay);
        table.addCell(charge);

        return table;
    }

    public PdfPTable createSum(Payment payment) {
        float[] widths = {15, 10, 10, 10, 10};
        PdfPTable table = new PdfPTable(widths);
        table.getDefaultCell().setBorderWidth(1);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(58);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(new Phrase("Razem:"));
        table.addCell(new Phrase(payment.getCharge().toString()));


        PdfPCell Suma = new PdfPCell(new Phrase(payment.getCharge().toString() + " zł"));
        Suma.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Suma.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(Suma);

        return table;
    }

    private Font getFont() {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Font(bf, 9, Font.NORMAL);
    }
}
