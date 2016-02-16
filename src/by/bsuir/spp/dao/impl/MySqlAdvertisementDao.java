package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Кирилл on 2/15/2016.
 */
public class MySqlAdvertisementDao implements AdvertisementDao {

    private static final MySqlAdvertisementDao instance = new MySqlAdvertisementDao();

    private MySqlAdvertisementDao(){}

    private static MySqlAdvertisementDao GetInstance() { return instance;}

    private static final String INSERT_ADVERTISEMENT_QUERY = "insert into `advertisement` (weight, cost, idPassport, addressForGetting) "+
            "values (?,?,?,?)";
    private static final String SELECT_ALL_ADVERTISEMENT = "select * from `advertisement`";
    private static final String SELECT_ADVERTISEMENT_BY_ID = "select * from `advertisement` where id=?";
    private static final String DELETE_ADVERTISEMENT_BY_ID = "delete from `advertisement` where id=?";
    private static final String UPDATE_ADVERTISEMENT_BY_ID = "update `advertisement` set weight=?, cost=?, addressForGetting=? "+
            "where id=?";

    @Override
    public List<Advertisement> getAllAdvertisement() {
        return null;
    }

    @Override
    public Integer create(Advertisement newInstance) throws DaoException {
        int id = 0;

        try (Connection connection = (Connection) ConnectionPoolImpl.getInstance();
             PreparedStatement statement = connection.prepareStatement(INSERT_ADVERTISEMENT_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, newInstance.getWeight());
            statement.setInt(2, newInstance.getCost());

            //statement.setInt(3, newInstance.getPassport());

            statement.setString(4, newInstance.getAddressForGetting());
            statement.execute();
            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if (resultSet.next())
                    id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Advertisement read(Integer id) throws DaoException {
        Advertisement advertisement = null;

        try(Connection connection = (Connection) ConnectionPoolImpl.getInstance();
            PreparedStatement statement = connection.prepareStatement(SELECT_ADVERTISEMENT_BY_ID)) {

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                {
                    advertisement = new Advertisement();
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
        }
        return advertisement;
    }

    @Override
    public void update(Advertisement obj) throws DaoException {

    }

    @Override
    public void delete(Advertisement obj) throws DaoException {

    }
}
