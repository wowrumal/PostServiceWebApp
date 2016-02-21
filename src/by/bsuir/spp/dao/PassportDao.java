package by.bsuir.spp.dao;

import by.bsuir.spp.bean.Passport;

import java.util.List;

public interface PassportDao extends GenericDao<Passport, Integer> {

    List<Passport> getAllPassports();
    List<Integer> getIdPassports();
}
