package by.bsuir.spp.documentgenerator;

import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.bean.document.SearchPackageStatement;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.OutputStream;

public interface DocumentGenerator {
    void generatePackage(by.bsuir.spp.bean.document.Package pack, OutputStream outputStream) throws DocumentException, IOException;
    void generateAdvertisement(Advertisement advertisement, OutputStream outputStream) throws DocumentException, IOException;
    void generatePrepaymentBook(PrepaymentBookStatement prepaymentBookStatement, OutputStream outputStream) throws DocumentException, IOException;
    void generateReceipt(Receipt receipt, OutputStream outputStream) throws DocumentException, IOException;
    void generateSearchStatement(SearchPackageStatement searchPackageStatement, OutputStream outputStream) throws DocumentException, IOException;
}
