package by.bsuir.spp.listener;

import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionProviderListener implements ServletContextListener {

    private IConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        connectionPool = ConnectionPoolImpl.getInstance();
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Connection pool init error.");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (connectionPool != null) {
            connectionPool.dispose();
        }
    }
}
