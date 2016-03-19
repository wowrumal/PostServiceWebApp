package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Кирилл on 2/15/2016.
 */
public class MySqlAdvertisementDao implements AdvertisementDao {

    private static final MySqlAdvertisementDao instance = new MySqlAdvertisementDao();

    private MySqlAdvertisementDao(){}

    public static MySqlAdvertisementDao getInstance() { return instance;}

    private static final String INSERT_ADVERTISEMENT_QUERY = "insert into `advertisement` (idPackage, weight, cost, idPassport, addressForGetting) "+
            "values (?,?,?,?,?)";
    private static final String SELECT_ALL_ADVERTISEMENT = "select * from `advertisement`";
    private static final String SELECT_ADVERTISEMENTS_BY_PASSPORT_ID = "select * from `advertisement` WHERE idPassport = ?";
    private static final String SELECT_ADVERTISEMENT_BY_ID = "select * from `advertisement` where idPackage=?";
    private static final String DELETE_ADVERTISEMENT_BY_ID = "delete from `advertisement` where idPackage=?";
    private static final String UPDATE_ADVERTISEMENT_BY_ID = "update `advertisement` set weight=?, cost=?, addressForGetting=? "+
            "where idPackage=?";

    @Override
    public List<Advertisement> getAllAdvertisement() {
        List<Advertisement> advertisements = new ArrayList<>();

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ADVERTISEMENT);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())
            {
                Advertisement advertisement = new Advertisement();

                Package myPackage = new Package();
                myPackage.setIdPackage(resultSet.getInt(1));
                advertisement.setPostPackage(myPackage);

                advertisement.setWeight(resultSet.getInt(2));
                advertisement.setCost(resultSet.getInt(3));

                Passport passport = new Passport();
                passport.setPassportId(resultSet.getInt(4));
                advertisement.setPassport(passport);

                advertisement.setAddressForGetting(resultSet.getString(5));

                advertisements.add(advertisement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return advertisements;
    }

    @Override
    public List<Advertisement> getAdvertisementsByPassportId(int passportId) {
        List<Advertisement> advertisements = new ArrayList<>();

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ADVERTISEMENTS_BY_PASSPORT_ID)) {
            statement.setInt(1, passportId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                {
                    Advertisement advertisement = new Advertisement();

                    Package myPackage = new Package();
                    myPackage.setIdPackage(resultSet.getInt(1));
                    advertisement.setPostPackage(myPackage);

                    advertisement.setWeight(resultSet.getInt(2));
                    advertisement.setCost(resultSet.getInt(3));

                    Passport passport = new Passport();
                    passport.setPassportId(resultSet.getInt(4));
                    advertisement.setPassport(passport);

                    advertisement.setAddressForGetting(resultSet.getString(5));

                    advertisements.add(advertisement);
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return advertisements;
    }

    @Override
    public Integer create(Advertisement newInstance) throws DaoException {

        try (Connection connection =  ConnectionPoolImpl.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ADVERTISEMENT_QUERY))
        {
            statement.setInt(1, newInstance.getPostPackage().getIdPackage());
            statement.setInt(2, newInstance.getWeight());
            statement.setInt(3, newInstance.getCost());
            statement.setInt(4, newInstance.getPassport().getPassportId());
            statement.setString(5, newInstance.getAddressForGetting());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return newInstance.getPostPackage().getIdPackage();
    }

    @Override
    public Advertisement read(Integer id) throws DaoException {
        Advertisement advertisement = null;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ADVERTISEMENT_BY_ID)) {

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                {
                    advertisement = new Advertisement();

                    Package myPackage = new Package();
                    myPackage.setIdPackage(resultSet.getInt(1));
                    advertisement.setPostPackage(myPackage);

                    advertisement.setWeight(resultSet.getInt(2));
                    advertisement.setCost(resultSet.getInt(3));

                    Passport passport = new Passport();
                    passport.setPassportId(resultSet.getInt(4));
                    advertisement.setPassport(passport);

                    advertisement.setAddressForGetting(resultSet.getString(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return advertisement;
    }

    @Override
    public void update(Advertisement obj) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ADVERTISEMENT_BY_ID)) {

            statement.setInt(1, obj.getWeight());
            statement.setInt(2, obj.getCost());
            statement.setString(3, obj.getAddressForGetting());
            statement.setInt(4, obj.getPostPackage().getIdPackage());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Advertisement obj) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_ADVERTISEMENT_BY_ID)) {

            statement.setInt(1, obj.getPostPackage().getIdPackage());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
