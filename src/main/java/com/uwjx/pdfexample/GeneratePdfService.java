package com.uwjx.pdfexample;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

@Slf4j
@Service
public class GeneratePdfService {

    @PostConstruct
    public void generate() {
        try {
            String pdfFile = "/Users/wanghuan/Documents";
            try {
                File filePath = new File(pdfFile);
                if(!filePath.exists()){
                    filePath.mkdir();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            String fileName = "test.pdf";
            String filePath = pdfFile + "/" + fileName;
            log.warn("准备生成PDF 文件:{}" , fileName);
            File file = new File(filePath);
            try {
                if(!file.exists()){
                    file.createNewFile();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Document document = new Document();
            FileOutputStream outputStream = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
//            log.warn("密码:{}" , bean.getIdentityNumber());
//            writer.setEncryption(bean.getIdentityNumber().getBytes()
//                    , bean.getIdentityNumber().getBytes(), PdfWriter.ALLOW_COPY
//                    , PdfWriter.EMBEDDED_FILES_ONLY);
//            document.addAuthor("XXX");
//            document.addCreationDate();
//            document.addCreator("XXX");
//            document.addSubject("XXXXXX");
//            document.addTitle("XXXXXX");
//            document.addHeader("Expires", "0");
            document.open();

            Image image = Image.getInstance("https://s3-us-west-1.amazonaws.com/jiafinance/logo_what.png");
            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            image.scaleAbsolute(70, 70);
            image.setAlignment(Element.ALIGN_TOP | Element.ALIGN_MIDDLE);
            document.add(image);

            Paragraph date = new Paragraph("2019-09-06");
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            Paragraph docNo = new Paragraph("测试");
            document.add(docNo);

            Paragraph start = new Paragraph("Pre-approval for:");
            document.add(start);

            Font nameFont = FontFactory.getFont(FontFactory.COURIER,
                    12, Font.BOLD, CMYKColor.BLACK);



            Paragraph text1 = new Paragraph("The above named individual has been pre-approved for a loan with the terms described below.");
            text1.setSpacingBefore(20);
            document.add(text1);

            Font tableFont = FontFactory.getFont(FontFactory.COURIER,
                    12, Font.BOLD, CMYKColor.BLACK);


            Paragraph downPay = new Paragraph("Down payment: $"  , tableFont);
            document.add(downPay);


            Paragraph content1 = new Paragraph("This approval is a preliminary determination that the borrower(s) named above qualifies for a mortgage loan under our lending standards and guidelines. It is subject to the verification of all additional information provided by borrower(s). It is also subject to a satisfactory appraisal of the subject property, a satisfactory title search and a final underwriting decision, among other criteria. If final approval is granted for this loan, the terms, loan amount, and conditions may be different than what is described in this pre-approval letter. ");
            content1.setSpacingBefore(20);
            document.add(content1);

            Paragraph content2 = new Paragraph("Jia Finance Inc. is a direct lender specializing in foreign investment in US residential property. Combining a focused service, innovative technology and competitive pricing, our goal is to provide you with a fast, transparent and simple process to obtaining a mortgage in the United States.");
            content2.setSpacingBefore(20);
            document.add(content2);

            Paragraph content3 = new Paragraph("Please get in touch with our excellent team if you need any assistance at:  ");
            content3.setSpacingBefore(20);
            document.add(content3);

            Font emailFont = FontFactory.getFont(FontFactory.COURIER,
                    12, Font.BOLD, CMYKColor.BLACK);
            Paragraph email = new Paragraph("mortgages@jiafinance.com" , emailFont);
            email.setSpacingBefore(20);
            document.add(email);

            Font footerFont = FontFactory.getFont(FontFactory.COURIER,
                    9, Font.NORMAL, CMYKColor.BLACK);
            Paragraph footer = new Paragraph("© 2018 Jia Finance Inc. All Rights Reserved. Jia Finance Inc. makes residential, business purpose loans. Loans are for investment purposes only and not for personal, family, or household use. Loan product availability may be limited in certain states. The specific facts and circumstances of each proposed loan transaction impact whether Jia Finance will be authorized to make loans in a particular state. No mortgage solicitation activity or loan applications for properties located in the State of New York or the State of California can be facilitated through this site or otherwise by Jia Finance Inc. Other restrictions apply. Jia Finance Inc. is domiciled in and conducts its business solely in the United States, it is not registered or authorized to take loan applications, undertake lending activities or provide financial services in any foreign jurisdictions, including the People’s Republic of China."
                    ,footerFont);
            footer.setSpacingBefore(100);
            document.add(footer);



            document.close();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Font getChineseFont() {
        BaseFont base = null;
        Font fontChinese = null;
        try {
            base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.EMBEDDED);
            fontChinese = new Font(base, 12, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;
    }
}
