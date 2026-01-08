
package com.mycompany.oems;
import java.sql.*;
public class javaconnect {
    Connection con;
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oems","root","");
            System.out.println("connected Successful");
            return con;        
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

       public static void main(String[] args){
           connectDB();

}
}