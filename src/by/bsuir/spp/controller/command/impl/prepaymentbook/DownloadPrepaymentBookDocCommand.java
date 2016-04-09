package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.controller.command.DocumentCommand;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
import by.bsuir.spp.documentgenerator.DocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.PdfDocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.XlsDocumentGenerator;
import by.bsuir.spp.exception.dao.DaoException;
import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadPrepaymentBookDocCommand implements DocumentCommand {

    private static final DocumentCommand instance = new DownloadPrepaymentBookDocCommand();

    private DownloadPrepaymentBookDocCommand() {
    }

    public static DocumentCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        int bookNumber = Integer.parseInt(request.getParameter(RequestParameterName.PREPAYMENT_BOOK_NUMBER));

        PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();

        try {
            PrepaymentBookStatement prepaymentBookStatement = prepaymentBookDao.read(bookNumber);

            String fileName = "prepaymentBook" + prepaymentBookStatement.getStatementNumber();

            String docType = request.getParameter(RequestParameterName.DOC_TYPE);
            DocumentGenerator documentGenerator;
            switch (docType) {
                case "pdf" : {
                    fileName += ".pdf";
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    PdfDocumentGenerator pdfDocumentGenerator = (PdfDocumentGenerator) PdfDocumentGenerator.getInstance();
                    pdfDocumentGenerator.setIMAGE_BACK(request.getServletContext().getRealPath("/image/background.jpg"));
                    pdfDocumentGenerator.generatePrepaymentBook(prepaymentBookStatement, response.getOutputStream());
                    break;
                }
                case "xls" : {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    documentGenerator = XlsDocumentGenerator.getInstance();
                    documentGenerator.generatePrepaymentBook(prepaymentBookStatement, response.getOutputStream());
                    break;
                }
                case "xml" : {
                    break;
                }
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return "/controller?command=select_prepayment_book&prepayment_book_number=" + bookNumber;

    }

}