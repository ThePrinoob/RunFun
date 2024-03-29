package net.ictcampus.RunFun.javaDB;

//~~~ Imports ~~~
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // ~~~ Instanzvariabeln ~~~
    private String dbUrl;
    private String dbUser;
    private String dbPwd;
    private static ConnectionFactory connectionFactory;

    // ~~~ Konstruktor ~~~
    private ConnectionFactory(String url, String user, String pwd) {
        dbUrl = url;
        dbUser = user;
        dbPwd = pwd;
    }
    
    // ~~~ Methoden ~~~
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {

            connectionFactory = new ConnectionFactory("jdbc:mysql://srv108:3306/ingoldd", "ingoldd",
                    "Password2019$");
        }
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPwd());
        return conn;
    }
    
    // ~~~ Getter && Setter ~~~
    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

}