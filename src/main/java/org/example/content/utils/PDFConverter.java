package org.example.content.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFConverter {
    public static void panelToPDF(JPanel panel) {
        String filePath = new String("src/main/outputfiles/Resume.pdf");
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfGraphics2D pdfGraphics = new PdfGraphics2D(writer.getDirectContent(), panel.getWidth(), panel.getHeight());
            panel.print(pdfGraphics);
            pdfGraphics.dispose();
            document.close();
            System.out.println("PDF 文件已生成：" + new File(filePath).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}