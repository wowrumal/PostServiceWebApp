package by.bsuir.spp.dao;

import by.bsuir.spp.bean.document.Receipt;

import java.util.List;

public interface ReceiptDao extends GenericDao<Receipt, Integer> {
    List<Receipt> getAllReceipts();
    List<Receipt> getUserReceipts(int userId);
}
