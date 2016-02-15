package by.bsuir.spp.dao;

import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;

import java.util.List;

/**
 * Created by Кирилл on 2/15/2016.
 */
public interface PackageDao extends GenericDao<Package, Integer> {

    List<Package> getAllPackages();
}
