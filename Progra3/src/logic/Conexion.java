package logic;

import com.mongodb.*;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion{
    
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public static final String USERNAME = "dbaproy2";
    public static final String PASSWORD = "1234";
    static Connection cn;
    static Statement st;
    static ResultSet rs;
    
    static DB db;
    static DBCollection coleccion;
    
    public static Connection getConexion() throws SQLException{
        cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return cn;
    }
    
    public static ResultSet getRS(String query) throws SQLException{
        cn = Conexion.getConexion();
        st = cn.createStatement();
        rs = st.executeQuery(query);
        return rs;
    }
    
    public static DB getDB() throws UnknownHostException{
        Mongo mongo = new Mongo("localhost",27017);
        db = mongo.getDB("proyecto3");
        return db;
    }
    
    public static DBCollection getTabla(String tabla) throws UnknownHostException{
        db = getDB();
        coleccion = db.getCollection(tabla);
        return coleccion;
    }
    
}
