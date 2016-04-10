package by.bsuir.spp.documentgenerator.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.documentgenerator.DocumentGenerator;
import by.bsuir.spp.exception.dao.DaoException;
import com.itextpdf.text.DocumentException;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;

public class XlsDocumentGenerator implements DocumentGenerator {


    private static final DocumentGenerator instance = new XlsDocumentGenerator();
    private XlsDocumentGenerator() {}

    public static DocumentGenerator getInstance() {
        return instance;
    }

    @Override
    public void generatePackage(by.bsuir.spp.bean.document.Package pack, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Package" + pack.getIdPackage());

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Получатель");
        row.createCell(1).setCellValue(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName());

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Отправитель");
        row.createCell(1).setCellValue(pack.getSenderName());

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Тип посылки");
        row.createCell(1).setCellValue(pack.getType());

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("Адрес");
        row.createCell(1).setCellValue(pack.getAddress());


        CellStyle cellStyleForDate = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyleForDate.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy"));
        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Дата отправления");
        row.createCell(1).setCellValue(pack.getDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("Статус посылки");
        row.createCell(1).setCellValue(pack.getStatus());

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("Трек-код");
        row.createCell(1).setCellValue(pack.getTrackNumber());

        

        for (int i = 0; i < 7; i++) {
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row1 = sheet.getRow(i);
            row1.getCell(0).setCellStyle(cellStyle);
        }

        for (int i=0; i < 2 ; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    @Override
    public void generateAdvertisement(Advertisement advertisement, OutputStream outputStream) throws DocumentException, IOException {
        by.bsuir.spp.bean.document.Package pack = advertisement.getPostPackage();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Извещение" + advertisement.getPostPackage().getIdPackage());

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Получатель");
        row.createCell(1).setCellValue(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName());

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Отправитель");
        row.createCell(1).setCellValue(pack.getSenderName());

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Тип посылки");
        row.createCell(1).setCellValue(pack.getType());

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("Адрес");
        row.createCell(1).setCellValue(pack.getAddress());

        CellStyle cellStyleForDate = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyleForDate.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy"));
        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Дата отправления");
        row.createCell(1).setCellValue(pack.getDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("Статус посылки");
        row.createCell(1).setCellValue(pack.getStatus());

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("Трек-код");
        row.createCell(1).setCellValue(pack.getTrackNumber());

        row = sheet.createRow(7);
        row.createCell(0).setCellValue("Вес посылки (граммы)");
        row.createCell(1).setCellValue(advertisement.getWeight());

        row = sheet.createRow(8);
        row.createCell(0).setCellValue("Стоимость доставки (бел.руб)");
        row.createCell(1).setCellValue(advertisement.getCost());


        for (int i = 0; i < 9; i++) {
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row1 = sheet.getRow(i);
            row1.getCell(0).setCellStyle(cellStyle);
        }

        for (int i=0; i < 2 ; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    @Override
    public void generatePrepaymentBook(PrepaymentBookStatement prepaymentBookStatement, OutputStream outputStream) throws DocumentException, IOException {

        Passport passport = null;
        try {
            passport = MySqlPassportDao.getInstance().read(prepaymentBookStatement.getPassportId());
        } catch (DaoException e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Заявление. Авансовая книга" + prepaymentBookStatement.getStatementNumber());

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Имя клиента");
        row.createCell(1).setCellValue(prepaymentBookStatement.getClientName());

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Номер паспорта");
        row.createCell(1).setCellValue(passport.getPassportNumber());

        CellStyle cellStyleForDate = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyleForDate.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy"));
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Выдан (дата)");
        row.createCell(1).setCellValue(passport.getIssueDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("Средства на счету (бел.руб)");
        row.createCell(1).setCellValue(prepaymentBookStatement.getUnpaidCost());

        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Глава организации");
        row.createCell(1).setCellValue(prepaymentBookStatement.getOrganizationHeadName());

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("Бухгалтер");
        row.createCell(1).setCellValue(prepaymentBookStatement.getBookkeeperName());

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("Дата");
        row.createCell(1).setCellValue(prepaymentBookStatement.getDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        for (int i = 0; i < 7; i++) {
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row1 = sheet.getRow(i);
            row1.getCell(0).setCellStyle(cellStyle);
        }

        for (int i=0; i < 2 ; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    @Override
    public void generateReceipt(Receipt receipt, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Квитанция №" + receipt.getReceiptId());

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Имя плательщика");
        row.createCell(1).setCellValue(receipt.getClientName());

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Услуга");
        row.createCell(1).setCellValue(receipt.getServiceName());

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Данные оплаты");
        row.createCell(1).setCellValue(receipt.getPaymentData());

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("Стоимость (бел.руб)");
        row.createCell(1).setCellValue(receipt.getCost());

        CellStyle cellStyleForDate = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyleForDate.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy"));
        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Дата оплаты");
        row.createCell(1).setCellValue(receipt.getDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        for (int i = 0; i < 5; i++) {
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row1 = sheet.getRow(i);
            row1.getCell(0).setCellStyle(cellStyle);
        }

        for (int i=0; i < 2 ; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    @Override
    public void generateSearchStatement(SearchPackageStatement searchPackageStatement, OutputStream outputStream) throws DocumentException, IOException {
        Package pack = searchPackageStatement.getPostPackage();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Заявление. Поиск посылки " + searchPackageStatement.getId());

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Содержание заявления");
        row.createCell(1).setCellValue(searchPackageStatement.getPetitionContent());

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Информация о посылке");
        row.createCell(1);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Получатель");
        row.createCell(1).setCellValue(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName());

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("Отправитель");
        row.createCell(1).setCellValue(pack.getSenderName());

        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Тип посылки");
        row.createCell(1).setCellValue(pack.getType());

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("Адрес");
        row.createCell(1).setCellValue(pack.getAddress());

        CellStyle cellStyleForDate = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyleForDate.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy"));
        row = sheet.createRow(6);
        row.createCell(0).setCellValue("Дата отправления");
        row.createCell(1).setCellValue(pack.getDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        row = sheet.createRow(7);
        row.createCell(0).setCellValue("Статус посылки");
        row.createCell(1).setCellValue(pack.getStatus());

        row = sheet.createRow(8);
        row.createCell(0).setCellValue("Трек-код");
        row.createCell(1).setCellValue(pack.getTrackNumber());

        row = sheet.createRow(9);
        row.createCell(0); row.createCell(1);

        row = sheet.createRow(10);
        row.createCell(0).setCellValue("Контактный телефон");
        row.createCell(1).setCellValue(searchPackageStatement.getPhoneNumber());

        row = sheet.createRow(11);
        row.createCell(0).setCellValue("Менеджер");
        row.createCell(1).setCellValue(searchPackageStatement.getPostManagerName());

        row = sheet.createRow(12);
        row.createCell(0).setCellValue("Дата");
        row.createCell(1).setCellValue(searchPackageStatement.getCurrentDate());
        row.getCell(1).setCellStyle(cellStyleForDate);

        CellStyle cellStyle = null;
        for (int i = 0; i < 13; i++) {
            cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row1 = sheet.getRow(i);
            row1.getCell(0).setCellStyle(cellStyle);
        }

        Row row1 = sheet.getRow(1);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        row1.getCell(0).setCellStyle(cellStyle);

        for (int i=0; i < 2 ; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        outputStream.close();
    }
}
