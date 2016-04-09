package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.controller.command.DocumentCommand;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.documentgenerator.DocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.PdfDocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.XlsDocumentGenerator;
import by.bsuir.spp.exception.dao.DaoException;
import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadReceiptDocCommand implements DocumentCommand {

    private static final DocumentCommand instance = new DownloadReceiptDocCommand();

    private DownloadReceiptDocCommand() {
    }

    public static DocumentCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        int idReceipt = Integer.parseInt(request.getParameter(RequestParameterName.RECEIPT_ID));
        ReceiptDao receiptDao = MySqlReceiptDao.getInstance();
        try {
            Receipt receipt = receiptDao.read(idReceipt);

            String fileName = "receipt" + receipt.getClientName() + "_" + receipt.getServiceName() + receipt.getReceiptId();

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
                    pdfDocumentGenerator.generateReceipt(receipt, response.getOutputStream());
                    break;
                }
                case "xls" : {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    documentGenerator = XlsDocumentGenerator.getInstance();
                    documentGenerator.generateReceipt(receipt, response.getOutputStream());
                    break;
                }
                case "xml" : {
                    break;
                }
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return "controller?command=select_receipt&receipt_id=" + idReceipt;
    }
}
