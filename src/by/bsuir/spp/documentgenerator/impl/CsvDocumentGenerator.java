package by.bsuir.spp.documentgenerator.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.documentgenerator.DocumentGenerator;
import by.bsuir.spp.exception.dao.DaoException;
import com.itextpdf.text.DocumentException;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvDocumentGenerator implements DocumentGenerator {

    private static final DocumentGenerator instance = new CsvDocumentGenerator();

    private CsvDocumentGenerator() {}

    public static DocumentGenerator getInstance() {
        return instance;
    }

    private static CellProcessor[] getProcessorsForPackage() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new FmtDate("dd/MM/yyyy"),
                new NotNull(),
                new Optional()
        };

        return processors;
    }

    @Override
    public void generatePackage(by.bsuir.spp.bean.document.Package pack, OutputStream outputStream) throws DocumentException, IOException {

        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_PREFERENCE);
        final String[] header = {"Получатель","Отправитель","Тип посылки","Адрес","Дата отправления","Статус посылки","Трек-код"};
        final List<Object> packageData = new ArrayList<Object>() {{
            add(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName());
            add(pack.getSenderName());
            add(pack.getType());
            add(pack.getAddress());
            add(pack.getDate());
            add(pack.getStatus());
            add(pack.getTrackNumber());
        }};

        final CellProcessor[] processors = getProcessorsForPackage();
        listWriter.writeHeader(header);
        listWriter.write(packageData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }

    private static CellProcessor[] getProcessorsForAdvertisement() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new FmtDate("dd/MM/yyyy"),
                new Optional(),
                new Optional(),
                new Optional(new ParseInt()),
                new Optional(new ParseInt())
        };

        return processors;
    }

    @Override
    public void generateAdvertisement(Advertisement advertisement, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_PREFERENCE);
        final String[] header = {"Получатель","Отправитель","Тип посылки","Адрес","Дата отправления","Статус посылки",
                "Трек-код", "Вес посылки (граммы)", "Стоимость доставки (бел.руб)"};

        by.bsuir.spp.bean.document.Package pack = advertisement.getPostPackage();
        final List<Object> advertisementData = new ArrayList<Object>() {{
            add(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName());
            add(pack.getSenderName());
            add(pack.getType());
            add(pack.getAddress());
            add(pack.getDate());
            add(pack.getStatus());
            add(pack.getTrackNumber());
            add(advertisement.getWeight());
            add(advertisement.getCost());
        }};

        final CellProcessor[] processors = getProcessorsForAdvertisement();
        listWriter.writeHeader(header);
        listWriter.write(advertisementData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }


    private static CellProcessor[] getProcessorsForPrepaymentBook() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new FmtDate("dd/MM/yyyy"),
                new Optional(new ParseInt()),
                new NotNull(),
                new NotNull(),
                new FmtDate("dd/MM/yyyy")
        };

        return processors;
    }

    @Override
    public void generatePrepaymentBook(PrepaymentBookStatement prepaymentBookStatement, OutputStream outputStream) throws DocumentException, IOException {

        Passport passport = null;
        try {
            passport = MySqlPassportDao.getInstance().read(prepaymentBookStatement.getPassportId());
        } catch (DaoException e) {
            e.printStackTrace();
        }

        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_PREFERENCE);
        final String[] header = {"Имя клиента","Номер паспорта","Выдан (дата)","Средства на счету (бел.руб)","Глава организации","Бухгалтер",
                "Дата"};

        final Passport finalPassport = passport;
        final List<Object> prepaymentBookData = new ArrayList<Object>() {{
            add(prepaymentBookStatement.getClientName());
            add(finalPassport.getPassportNumber());
            add(finalPassport.getIssueDate());
            add(prepaymentBookStatement.getUnpaidCost());
            add(prepaymentBookStatement.getOrganizationHeadName());
            add(prepaymentBookStatement.getBookkeeperName());
            add(prepaymentBookStatement.getDate());
        }};

        final CellProcessor[] processors = getProcessorsForPrepaymentBook();
        listWriter.writeHeader(header);
        listWriter.write(prepaymentBookData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }


    private static CellProcessor[] getProcessorsForReceipt() {
        CellProcessor[] processors = {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new ParseInt(),
                new FmtDate("dd/MM/yyyy")
        };

        return processors;
    }

    @Override
    public void generateReceipt(Receipt receipt, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_PREFERENCE);
        final String[] header = {"Имя плательщика","Услуга","Данные оплаты","Стоимость (бел.руб)","Дата оплаты"};

        final List<Object> receiptData = new ArrayList<Object>() {{
            add(receipt.getClientName());
            add(receipt.getServiceName());
            add(receipt.getPaymentData());
            add(receipt.getCost());
            add(receipt.getDate());
        }};

        final CellProcessor[] processors = getProcessorsForReceipt();
        listWriter.writeHeader(header);
        listWriter.write(receiptData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }

    private static CellProcessor[] getProcessorsForSeachStatement() {
        CellProcessor[] processors = {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new FmtDate("dd/MM/yyyy"),
                new Optional(),
                new Optional(),
                new NotNull(),
                new NotNull(),
                new FmtDate("dd/MM/yyyy")
        };

        return processors;
    }

    @Override
    public void generateSearchStatement(SearchPackageStatement searchPackageStatement, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_PREFERENCE);
        final String[] header = {"Содержание заявления","Получатель","Отправитель","Тип посылки","Адрес","Дата отправления","Статус посылки",
                "Трек-код", "Контактный телефон", "Менеджер", "Дата"};

        by.bsuir.spp.bean.document.Package pack = searchPackageStatement.getPostPackage();
        final List<Object> advertisementData = new ArrayList<Object>() {{
            add(searchPackageStatement.getPetitionContent());
            add(pack.getGetterUser().getSecondName() + " " + pack.getGetterUser().getFirstName());
            add(pack.getSenderName());
            add(pack.getType());
            add(pack.getAddress());
            add(pack.getDate());
            add(pack.getStatus());
            add(pack.getTrackNumber());
            add(searchPackageStatement.getPhoneNumber());
            add(searchPackageStatement.getPostManagerName());
            add(searchPackageStatement.getCurrentDate());
        }};

        final CellProcessor[] processors = getProcessorsForSeachStatement();
        listWriter.writeHeader(header);
        listWriter.write(advertisementData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }
}
