package siparis;

import java.sql.*;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Siparis {
    
    public static ArrayList<String> orders = new ArrayList<>();
    public static ArrayList<Double> ordersPrices = new ArrayList<>();
    public static ArrayList<String> menu = new ArrayList<>();
    public static Map<String,Integer> menuPrices = new HashMap<>();
    public static double money = 0;
    
    
    public static void addDataBase(String queryVal , double price){
        try{
              // create a mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost/test";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, "root", "");

              // create a sql date object so we can use it in our INSERT statement
              Calendar calendar = Calendar.getInstance();
              java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

              // the mysql insert statement
              String query = " insert into current_orders (order_name, total_price) values (?, ?)";

              // create the mysql insert preparedstatement
              PreparedStatement preparedStmt = conn.prepareStatement(query);
              preparedStmt.setString (1, queryVal);
              preparedStmt.setDouble(2, price);
 

              // execute the preparedstatement
              preparedStmt.execute();

              conn.close();
            }
            catch (Exception e){
              System.err.println("Got an exception!");
              System.err.println(e.getMessage());
            }
    }
    
    
    public static void deleteData(String name){
        try{
            // create the mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/test";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // create the mysql delete statement.
            // i'm deleting the row where the id is "3", which corresponds to my
            // "Barney Rubble" record.
            String query = "delete from current_orders where order_name = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
          }
          catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
          }
    }
    
    
    
    public static void main(String[] args){
        new mainScreen().setVisible(true);
    }   
}
