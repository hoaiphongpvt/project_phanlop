
package untils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MyDataAccess {
    private String host = "";
    private String user = "";
    private String pw = "";
    private String database ="";
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    public MyDataAccess(String host, String user, String pw, String database){
        this.host = host;
        this.user = user;
        this.pw = pw;
        this.database = database;
    }
    
    public void RegDriver() throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(java.lang.ClassNotFoundException e){
            throw new Exception("MYSQL JDBC Driver not found.");
        }
    }
    
    protected Connection getConnection() throws Exception{
        if(this.con == null){
            RegDriver();
            String url = "jdbc:mysql://" + this.host + ":3306/" + this.database + "?useUnicode=true&characterEncoding=utf8";
            try{
                this.con = DriverManager.getConnection(url,user,pw);
                
            }catch(SQLException e){
                throw new Exception("Không thể kết nối đến CSDL");
            }
        }
        return this.con;
    }
    protected Statement getStatement()throws Exception{
        if(this.st == null?true:this.st.isClosed()){
            this.st = this.getConnection().createStatement();
        }
        return this.st;
    }
    public ResultSet executeQuery(String qry) throws Exception{
       try{
           
           this.rs = this.getStatement().executeQuery(qry);
       }
       catch(Exception e){
           throw new Exception("Error:" + e.getMessage() + "-" + qry);
       }
       return this.rs;
    }
    public int executeUpdate(String qry) throws Exception{
        int res = 0;
        try{
            res = this.getStatement().executeUpdate(qry);
        }catch(Exception e){
            throw new Exception("Error:" + e.getMessage() + "-" + qry);
        }
        finally{
            this.close();
        }
        return res;
    }
    public void close() throws Exception{
       if(this.rs != null && !this.rs.isClosed()){
           this.rs.close();
           this.rs = null;
       }
       if(this.st != null && !this.st.isClosed()){
           this.st.close();
           this.st = null;
       }
       if(this.con != null && !this.con.isClosed()){
           this.con.close();
           this.con = null;
       }
    }
}