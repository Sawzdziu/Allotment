package services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class Seller {

    public PdfPTable createSeller() {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font f1 = new Font(bf, 9, Font.NORMAL);
        Font f2 = new Font(bf, 10, Font.BOLD);

        Phrase desc = new Phrase("Wystawca:\n", f1);
        Phrase seller = new Phrase("Rodzinny Ogród Działkowy \"OBROŃCA\".\n53-114 Wrocław, ul. Weigla b/n\nNIP: 899-234-87-55", f2);
        desc.add(seller);


        f1.setSize(10);
        Phrase info = new Phrase("Data wystawienia: " + new Date() + ", Wrocław", f1);

        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setPadding(3);

        table.setWidthPercentage(100f);

        PdfPCell cell_seller = new PdfPCell(desc);
        cell_seller.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell_seller.setBorder(Rectangle.NO_BORDER);
        cell_seller.setPaddingBottom(10);

        PdfPCell cell_info = new PdfPCell(info);
        cell_info.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell_info.setBorder(Rectangle.NO_BORDER);
        cell_info.setPaddingBottom(10);


        table.addCell(cell_seller);
        table.addCell(cell_info);

        return table;

    }
}
