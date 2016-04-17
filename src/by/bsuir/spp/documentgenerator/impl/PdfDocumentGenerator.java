package by.bsuir.spp.documentgenerator.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.documentgenerator.DocumentGenerator;
import by.bsuir.spp.exception.dao.DaoException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

public class PdfDocumentGenerator implements DocumentGenerator {

    private static final DocumentGenerator instance = new PdfDocumentGenerator();

    private PdfDocumentGenerator() {
        try {
            BASE_FONT = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }



    public static DocumentGenerator getInstance() {
        return instance;
    }

    private static BaseFont BASE_FONT;

    private static final String USER_PASS = "";
    private static final String MAIN_PASS = "pass";
    private String IMAGE_BACK;

    public void setIMAGE_BACK(String IMAGE_BACK) {
        this.IMAGE_BACK = IMAGE_BACK;
    }


    @Override
    public void generatePackage(by.bsuir.spp.bean.document.Package pack, OutputStream outputStream) throws DocumentException {
        Document packDoc = new Document(PageSize.A6, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(packDoc, outputStream);
        writer.setEncryption(USER_PASS.getBytes(), MAIN_PASS.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        packDoc.open();
        Paragraph title = new Paragraph("ПОСЫЛКА", new Font(BASE_FONT, 13, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);

        packDoc.add(title);

        packDoc.add(Chunk.NEWLINE);
        packDoc.add(Chunk.NEWLINE);
        packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Получатель:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        packDoc.add(Chunk.NEWLINE);packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Отправитель:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(pack.getSenderName(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        packDoc.add(Chunk.NEWLINE);packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Тип посылки:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(pack.getType(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        packDoc.add(Chunk.NEWLINE);packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Адрес:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(pack.getAddress(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        packDoc.add(Chunk.NEWLINE);packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Дата отправления:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(pack.getDate()), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        packDoc.add(Chunk.NEWLINE);packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Статус посылки:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(pack.getStatus(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        packDoc.add(Chunk.NEWLINE);packDoc.add(Chunk.NEWLINE);

        packDoc.add(new Chunk("Трек-код:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        packDoc.add(new Phrase(pack.getTrackNumber(), new Font(BASE_FONT, 10, Font.UNDERLINE)));


        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = null;
        try {
            image = Image.getInstance(IMAGE_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.scaleAbsolute(PageSize.A6.getWidth(), PageSize.A6.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.2f);
            canvas.setGState(pdfGState);
            canvas.addImage(image);
            canvas.restoreState();
        }

        packDoc.close();

    }

    @Override
    public void generateAdvertisement(Advertisement advertisement, OutputStream outputStream) throws DocumentException {
        Document advertDoc = new Document(PageSize.A6, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(advertDoc, outputStream);
        writer.setEncryption(USER_PASS.getBytes(), MAIN_PASS.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        advertDoc.open();
        Paragraph title = new Paragraph("ИЗВЕЩЕНИЕ", new Font(BASE_FONT, 13, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);

        advertDoc.add(title);

        advertDoc.add(Chunk.NEWLINE);

        by.bsuir.spp.bean.document.Package pack = advertisement.getPostPackage();


        advertDoc.add(new Chunk("Получатель:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Отправитель:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(pack.getSenderName(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Тип посылки:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(pack.getType(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Адрес:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(pack.getAddress(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Дата извещения:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(pack.getDate()), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Статус посылки:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(pack.getStatus(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Вес посылки:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(advertisement.getWeight() + " грамм", new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);advertDoc.add(Chunk.NEWLINE);

        advertDoc.add(new Chunk("Трек-код:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        advertDoc.add(new Phrase(pack.getTrackNumber(), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        advertDoc.add(Chunk.NEWLINE);advertDoc.add(Chunk.NEWLINE);

        Paragraph costText = new Paragraph("Стоимость доставки:  ", new Font(BASE_FONT, 10, Font.BOLD));
        costText.setAlignment(Element.ALIGN_RIGHT);
        Paragraph costVal = new Paragraph(advertisement.getCost()+"бел.руб", new Font(BASE_FONT, 10, Font.UNDERLINE));
        costVal.setAlignment(Element.ALIGN_RIGHT);

        advertDoc.add(costText);
        advertDoc.add(costVal);

        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = null;
        try {
            image = Image.getInstance(IMAGE_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.scaleAbsolute(PageSize.A6.getWidth(), PageSize.A6.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.2f);
            canvas.setGState(pdfGState);
            canvas.addImage(image);
            canvas.restoreState();
        }

        advertDoc.close();
    }

    @Override
    public void generatePrepaymentBook(PrepaymentBookStatement prepaymentBookStatement, OutputStream outputStream) throws DocumentException {

        Passport passport = null;
        try {
            passport = MySqlPassportDao.getInstance().read(prepaymentBookStatement.getPassportId());
        } catch (DaoException e) {
            e.printStackTrace();
        }

        Document prepayDoc = new Document(PageSize.A5, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(prepayDoc, outputStream);
        writer.setEncryption(USER_PASS.getBytes(), MAIN_PASS.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        prepayDoc.open();
        Paragraph title = new Paragraph("Заявление на восстановление авансовой книжки №" + prepaymentBookStatement.getStatementNumber(), new Font(BASE_FONT, 15, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);

        prepayDoc.add(title);

        prepayDoc.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(2);

        PdfPCell clientNameCell = new PdfPCell(new Paragraph("Имя клиента:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        clientNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        clientNameCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell clientNameVal = new PdfPCell(new Paragraph(prepaymentBookStatement.getClientName(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        clientNameVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        clientNameVal.setBorder(Rectangle.NO_BORDER);
        clientNameCell.setLeading(45f, 0f);
        clientNameVal.setLeading(45f, 0f);
        table.addCell(clientNameCell);
        table.addCell(clientNameVal);

        PdfPCell passportNumberCell = new PdfPCell(new Paragraph("Номер паспорта:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        passportNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        passportNumberCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell passportNumberVal = new PdfPCell(new Paragraph(passport.getPassportNumber(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        passportNumberVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        passportNumberVal.setBorder(Rectangle.NO_BORDER);
        passportNumberCell.setLeading(45f, 0f);
        passportNumberVal.setLeading(45f, 0f);
        table.addCell(passportNumberCell);
        table.addCell(passportNumberVal);

        PdfPCell passportDateCell = new PdfPCell(new Paragraph("Выдан (дата):  ", new Font(BASE_FONT, 12, Font.BOLD)));
        passportDateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        passportDateCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell passportDateVal = new PdfPCell(new Paragraph(new SimpleDateFormat("yyyy-MM-dd").format(passport.getIssueDate()),
                new Font(BASE_FONT, 12, Font.UNDERLINE)));
        passportDateVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        passportDateVal.setBorder(Rectangle.NO_BORDER);
        passportDateCell.setLeading(45f, 0f);
        passportDateVal.setLeading(45f, 0f);
        table.addCell(passportDateCell);
        table.addCell(passportDateVal);

        PdfPCell costLeftCell = new PdfPCell(new Paragraph("Средства на счету:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        costLeftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        costLeftCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell costLeftVal = new PdfPCell(new Paragraph(prepaymentBookStatement.getUnpaidCost()+"бел.руб", new Font(BASE_FONT, 12, Font.UNDERLINE)));
        costLeftVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        costLeftVal.setBorder(Rectangle.NO_BORDER);
        costLeftCell.setLeading(45f, 0f);
        costLeftVal.setLeading(45f, 0f);
        table.addCell(costLeftCell);
        table.addCell(costLeftVal);

        PdfPCell headCell = new PdfPCell(new Paragraph("Глава организации:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        headCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        headCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell headVal = new PdfPCell(new Paragraph(prepaymentBookStatement.getOrganizationHeadName(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        headVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        headVal.setBorder(Rectangle.NO_BORDER);
        headCell.setLeading(45f, 0f);
        headVal.setLeading(45f,0f);
        table.addCell(headCell);
        table.addCell(headVal);

        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});
        table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});table.addCell(new PdfPCell(){{setBorder(Rectangle.NO_BORDER);}});

        PdfPCell bookCell = new PdfPCell(new Paragraph("Бухгалтер:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        bookCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        bookCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell bookVal = new PdfPCell(new Paragraph(prepaymentBookStatement.getBookkeeperName(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        bookVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        bookVal.setBorder(Rectangle.NO_BORDER);
        table.addCell(bookCell);
        table.addCell(bookVal);

        PdfPCell dateCell = new PdfPCell(new Paragraph(new SimpleDateFormat("yyyy-MM-dd").format(prepaymentBookStatement.getDate()), new Font(BASE_FONT, 12, Font.NORMAL)));
        dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell sign = new PdfPCell(new Paragraph("Подпись:               .", new Font(BASE_FONT, 12, Font.UNDERLINE)));
        sign.setHorizontalAlignment(Element.ALIGN_RIGHT);
        sign.setBorder(Rectangle.NO_BORDER);
        table.addCell(dateCell);
        table.addCell(sign);

        prepayDoc.add(table);

        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = null;
        try {
            image = Image.getInstance(IMAGE_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.scaleAbsolute(PageSize.A5.getWidth(), PageSize.A5.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.2f);
            canvas.setGState(pdfGState);
            canvas.addImage(image);
            canvas.restoreState();
        }

        prepayDoc.close();
    }

    @Override
    public void generateReceipt(Receipt receipt, OutputStream outputStream) throws DocumentException {
        Document receiptDoc = new Document(PageSize.A5.rotate(), 10, 10, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(receiptDoc, outputStream);
        writer.setEncryption(USER_PASS.getBytes(), MAIN_PASS.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        receiptDoc.open();

        PdfPTable pdfPTableHeader = new PdfPTable(1);
        pdfPTableHeader.setWidthPercentage(40);
        insertCellWithoutBorder(pdfPTableHeader, "ИП Иванов К.С.", Element.ALIGN_LEFT, 1, new Font(BASE_FONT, 8));
        insertCellWithoutBorder(pdfPTableHeader, "ПОШТА СЕРВИС. Веб-приложение.", Element.ALIGN_LEFT, 1, new Font(BASE_FONT, 8, Font.BOLD));
        insertCellWithoutBorder(pdfPTableHeader, "ИНН 503510313842    ОГРН 3115034502213", Element.ALIGN_LEFT, 1, new Font(BASE_FONT, 8));
        insertCellWithoutBorder(pdfPTableHeader, "РБ, г. Минск, ул. Гикало 9", Element.ALIGN_LEFT, 1, new Font(BASE_FONT, 8));
        insertCellWithoutBorder(pdfPTableHeader, "Тел.: +375 (29) 284-70-37", Element.ALIGN_LEFT, 1, new Font(BASE_FONT, 8));
        pdfPTableHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        receiptDoc.add(pdfPTableHeader);
        receiptDoc.add(Chunk.NEWLINE);

        Paragraph title = new Paragraph("Квитанция №" + receipt.getReceiptId(), new Font(BASE_FONT, 13, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);

        receiptDoc.add(title);

        receiptDoc.add(Chunk.NEWLINE);

        receiptDoc.add(new Chunk("Ф.И.О. плательщика:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        receiptDoc.add(new Phrase(receipt.getClientName() + "                                                                                  ",

                new Font(BASE_FONT, 10, Font.UNDERLINE)));
        receiptDoc.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80);

        table.setWidths(new float[]{1.2f, 0.8f});
        insertCell(table, "Наименование услуги", Element.ALIGN_CENTER, 1, new Font(BASE_FONT, 10));
        insertCell(table, "Сумма(бел.руб.)", Element.ALIGN_CENTER, 1, new Font(BASE_FONT, 10));
        table.setHeaderRows(1);

        insertCell(table, receipt.getServiceName(), Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));
        insertCell(table, Integer.toString(receipt.getCost()), Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));

        insertCell(table, "", Element.ALIGN_CENTER, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_CENTER, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_CENTER, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_CENTER, 1, new Font(BASE_FONT, 10));
        insertCell(table, "", Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));

        insertCell(table, "Всего по квитанции:", Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));
        insertCell(table, Integer.toString(receipt.getCost()), Element.ALIGN_RIGHT, 1, new Font(BASE_FONT, 10));

        receiptDoc.add(table);
        receiptDoc.add(Chunk.NEWLINE);

        receiptDoc.add(new Chunk("Данные оплаты:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        receiptDoc.add(new Phrase("  " +receipt.getPaymentData() + "                                                                                   " +
                "                                                                                                                       ",
                new Font(BASE_FONT, 10, Font.UNDERLINE)));
        receiptDoc.add(Chunk.NEWLINE);receiptDoc.add(Chunk.NEWLINE);


        receiptDoc.add(new Chunk("Дата оплаты:  ", new Font(BASE_FONT, 10, Font.BOLD)));
        receiptDoc.add(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(receipt.getDate()), new Font(BASE_FONT, 10, Font.UNDERLINE)));
        receiptDoc.add(Chunk.NEWLINE);receiptDoc.add(Chunk.NEWLINE);


        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = null;
        try {
            image = Image.getInstance(IMAGE_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.scaleAbsolute(PageSize.A5.rotate().getWidth(), PageSize.A5.rotate().getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.2f);
            canvas.setGState(pdfGState);
            canvas.addImage(image);
            canvas.restoreState();
        }

        receiptDoc.close();
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    private void insertCellWithoutBorder(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        cell.setBorder(Rectangle.NO_BORDER);
        //add the call to the table
        table.addCell(cell);
    }

    @Override
    public void generateSearchStatement(SearchPackageStatement searchPackageStatement, OutputStream outputStream) throws DocumentException {

        Package pack = searchPackageStatement.getPostPackage();

        Document searchStatementDoc = new Document(PageSize.A5, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(searchStatementDoc, outputStream);
        writer.setEncryption(USER_PASS.getBytes(), MAIN_PASS.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        searchStatementDoc.open();
        Paragraph title = new Paragraph("Заявление поиска посылки", new Font(BASE_FONT, 17, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);

        searchStatementDoc.add(title);

        searchStatementDoc.add(Chunk.NEWLINE);

        Paragraph statement = new Paragraph("Содержание заявления", new Font(BASE_FONT, 14, Font.BOLDITALIC));
        statement.setAlignment(Element.ALIGN_LEFT);
        searchStatementDoc.add(statement);
        searchStatementDoc.add(new Phrase(searchPackageStatement.getPetitionContent(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);


        Paragraph packInfo = new Paragraph("Информация о посылке", new Font(BASE_FONT, 14, Font.BOLDITALIC));
        packInfo.setAlignment(Element.ALIGN_LEFT);
        searchStatementDoc.add(packInfo);

        searchStatementDoc.add(new Chunk("Получатель:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName(),
                new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Отправитель:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(pack.getSenderName(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Тип посылки:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(pack.getType(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Адрес:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(pack.getAddress(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Дата отправления:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(pack.getDate()),
                new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Статус посылки:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(pack.getStatus(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Трек-код:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(pack.getTrackNumber(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);


        searchStatementDoc.add(new Chunk("Контактный телефон:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(searchPackageStatement.getPhoneNumber(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Менеджер:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(searchPackageStatement.getPostManagerName(), new Font(BASE_FONT, 12, Font.UNDERLINE)));
        searchStatementDoc.add(Chunk.NEWLINE);

        searchStatementDoc.add(new Chunk("Дата:  ", new Font(BASE_FONT, 12, Font.BOLD)));
        searchStatementDoc.add(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(searchPackageStatement.getCurrentDate()),
                new Font(BASE_FONT, 12, Font.UNDERLINE)));

        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = null;
        try {
            image = Image.getInstance(IMAGE_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.scaleAbsolute(PageSize.A5.getWidth(), PageSize.A5.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.2f);
            canvas.setGState(pdfGState);
            canvas.addImage(image);
            canvas.restoreState();
        }

        searchStatementDoc.close();
    }

}
