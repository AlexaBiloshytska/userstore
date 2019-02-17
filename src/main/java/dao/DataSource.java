package dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static volatile DataSource datasource;
    private BasicDataSource dataSource;

    private DataSource()  {
        dataSource = new BasicDataSource();
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:mem:test;INIT=runscript from 'classpath:init.sql'");
        dataSource.setDriverClassName("org.h2.Driver" );
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(180);
    }


    public static DataSource getInstance() {
        if (datasource == null) {
            synchronized (DataSource.class) {
                if (datasource == null) {
                    datasource = new DataSource();
                }
            }
            datasource = new DataSource();
        }
        return datasource;
    }

    public Connection getConnection() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] Unable to get datasource connection");
        }
    }

}
