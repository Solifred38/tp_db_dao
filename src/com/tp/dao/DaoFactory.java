package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        DaoFactory instance = new DaoFactory(
                //"jdbc:mysql://192.168.1.43:3307/javaee?verifyServerCertificate=false&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "fromager");
                //"jdbc:mysql://192.168.1.54:3306/javaee?verifyServerCertificate=false&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
        		"jdbc:mysql://lamp_db_1:3306/javaee?verifyServerCertificate=false&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // R�cup�ration du Dao
    public ArticleDao getArticleDao() {
        return new ArticleDaoImpl(this);
    }
    // R�cup�ration du Dao
    public MagasinDao getMagasinDao() {
        return new MagasinDaoImpl(this);
    }
}