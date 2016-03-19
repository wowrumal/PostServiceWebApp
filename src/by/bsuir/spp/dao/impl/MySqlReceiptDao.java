package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MySqlReceiptDao implements ReceiptDao {

    private static final String INSERT_RECEIPT_QUERY = "insert into receipt (clientName, paymentData, cost, `date`, idUser, serviceName) values (?,?,?,?,?,?)";
    private static final String SELECT_RECEIPT_BY_ID = "select * from receipt where id=?";
    private static final String SELECT_ALL_RECEIPT = "select * from receipt";
    private static final String DELETE_BY_ID = "delete from receipt where id=?";
    private static final String SELECT_RECEIPTS_BY_USER_ID = "select * from receipt where idUser=?";


    private static final MySqlReceiptDao instance = new MySqlReceiptDao();

    private MySqlReceiptDao() {

    }

    public static MySqlReceiptDao getInstance() {
        return instance;
    }

    @Override
    public List<Receipt> getAllReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_RECEIPT)) {
            while (resultSet.next()) {
                Receipt receipt = new Receipt();
                receipt.setReceiptId(resultSet.getInt(1));
                receipt.setClientName(resultSet.getString(2));
                receipt.setPaymentData(resultSet.getString(3));
                receipt.setCost(resultSet.getInt(4));
                receipt.setDate(new java.util.Date(resultSet.getDate(5).getTime()));
                receipt.setUserId(resultSet.getInt(6));
                receipt.setServiceName(resultSet.getString(7));
                receipts.add(receipt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return receipts;
    }

    @Override
    public List<Receipt> getUserReceipts(int userId) {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_RECEIPTS_BY_USER_ID))
        {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Receipt receipt = new Receipt();
                    receipt.setReceiptId(resultSet.getInt(1));
                    receipt.setClientName(resultSet.getString(2));
                    receipt.setPaymentData(resultSet.getString(3));
                    receipt.setCost(resultSet.getInt(4));
                    receipt.setDate(new java.util.Date(resultSet.getDate(5).getTime()));
                    receipt.setUserId(resultSet.getInt(6));
                    receipt.setServiceName(resultSet.getString(7));
                    receipts.add(receipt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return receipts;
    }

    @Override
    public Integer create(Receipt newInstance) throws DaoException {
        Integer receiptId = null;

        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_RECEIPT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newInstance.getClientName());
            statement.setString(2, newInstance.getPaymentData());
            statement.setInt(3, newInstance.getCost());
            statement.setDate(4, new Date(newInstance.getDate().getTime()));
            statement.setInt(5, newInstance.getUserId());
            statement.setString(6, newInstance.getServiceName());
            statement.execute();

            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    receiptId = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return receiptId;
    }

    @Override
    public Receipt read(Integer id) throws DaoException {
        Receipt receipt = null;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_RECEIPT_BY_ID)) {
            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    receipt = new Receipt();
                    receipt.setReceiptId(resultSet.getInt(1));
                    receipt.setClientName(resultSet.getString(2));
                    receipt.setPaymentData(resultSet.getString(3));
                    receipt.setCost(resultSet.getInt(4));
                    receipt.setDate(resultSet.getDate(5));
                    receipt.setUserId(resultSet.getInt(6));
                    receipt.setServiceName(resultSet.getString(7));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return receipt;
    }

    @Override
    public void update(Receipt obj) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Receipt obj) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, obj.getReceiptId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
