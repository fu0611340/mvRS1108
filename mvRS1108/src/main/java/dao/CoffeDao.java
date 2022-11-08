package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Coffee;

public class CoffeDao {
	public static Connection getConnection() {
		return null;
	}
	
	public int InsertCoffee(Coffee cf)
		    throws Exception{
		    Connection con=null;
		    PreparedStatement insert= null;
		   
		    String insertStatement =
		        "insert into classicmodels.coffees(COF_NAME,SUP_ID,PRICE,SALES,TOTAL)" +
		        "values (?,?,?,?,?)";

		    try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
		        con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234");
		        con.setAutoCommit(false);    
		        insert = con.prepareStatement(insertStatement);
		        insert.setString(1, cf.getCofName()); 
		        insert.setInt(2, cf.getSupId());           
		        insert.setDouble(3, cf.getPrice());
		        insert.setInt(4, cf.getSales());
		        insert.setInt(5, cf.getTotal());
		        int result = insert.executeUpdate();
		        con.commit();
		        return result;
		    } catch (Exception e ) {
		        System.out.println("SQL Error"+e.getMessage());
		        if (con != null) {
		            try {
		                System.err.print("Transaction is being rolled back. ");
		                con.rollback();
		            } catch(SQLException ex) {
		                System.out.println("Rollback Error:"+ex.getMessage());
		                throw ex;
		            }
		        }
		        throw e;
		    } finally {
		    	con.setAutoCommit(true);
		        if (insert != null) {
		            insert.close();
		        }
		        if (insert != null) {
		            insert.close();
		        }
		    }
		
		}   
}
