package by.bsuir.spp.dao;

import by.bsuir.spp.bean.document.Advertisement;

import java.util.List;

/**
 * Created by Кирилл on 2/15/2016.
 */
public interface AdvertisementDao extends GenericDao<Advertisement, Integer> {

    List<Advertisement> getAllAdvertisement();
    List<Advertisement> getAdvertisementsByPassportId(int passportId);
}
