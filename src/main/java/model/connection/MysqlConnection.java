package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class MysqlConnection implements IConnection {

    private static MysqlConnection singleinstance;

    private String serverName = "localhost";
    private String portNumber = "3306";
    private String dbName = "myvocabulary";
    private String user = "root";
    private String pass = "";

    private MysqlConnection(){}

    public static synchronized MysqlConnection getInstance(){
        //safe-thread implementation. it can be used with threads.
        if(Objects.isNull(singleinstance))
            singleinstance = new MysqlConnection();
        return singleinstance;
    }

    @Override
    public Connection getConnectionAcces() {
        Connection conn = null;
        try {
            conn =  DriverManager.getConnection(loadURLforDatabase(), user, pass);
        }catch (SQLException  exception){
            //for better debugging, loggin system should be used. 
            exception.printStackTrace();
        }
        return conn;
    }

    private String loadURLforDatabase(){
        StringBuilder dburl = new StringBuilder();
        dburl.append("jdbc:").append("mysql").append("://").append(serverName).append(":").append(portNumber).append("/").append(dbName).append("?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true");
        return dburl.toString();
    }

}
