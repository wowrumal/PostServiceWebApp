package by.bsuir.spp.dao;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;

import java.util.List;

/**
 * Created by Кирилл on 2/20/2016.
 */
public interface PrepaymentBookDao extends GenericDao<PrepaymentBookStatement, Integer> {

    List<PrepaymentBookStatement> getAllPrepaymentBooks();
}
