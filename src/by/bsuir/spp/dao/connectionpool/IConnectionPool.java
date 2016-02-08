package by.bsuir.spp.dao.connectionpool;

import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.Connection;

public interface IConnectionPool {
    void init() throws ConnectionPoolException;
    void dispose();
    Connection getConnection() throws ConnectionPoolException;

    interface IPooledConnection extends Connection {}
}
