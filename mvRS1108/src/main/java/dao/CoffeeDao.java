package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Coffee;

public class CoffeeDao {
	public static void main(String[] args) {
		System.out.println(new CoffeeDao().getCoffee());
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public List<Coffee> getCoffee(){	
		List<Coffee> l = new ArrayList<>();
		String sql = "SELECT * FROM classicmodels.coffees";
		Connection con = getConnection();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Coffee c = new Coffee();
				c.setCofName(rs.getString("cof_name"));
				c.setSupId(rs.getInt("sup_id"));
				c.setPrice(rs.getDouble("price"));
				c.setSales(rs.getInt("sales"));
				c.setTotal(rs.getInt("total"));
				l.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return l;
	}
	
	public int InsertCoffee(Coffee cf) throws SQLException {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement insert = null;

		String insertStatement = "insert into classicmodels.coffees(COF_NAME,SUP_ID,PRICE,SALES,TOTAL)"
				+ "values (?,?,?,?,?)";

		try {
			con.setAutoCommit(false);
			insert = con.prepareStatement(insertStatement);
			insert.setString(1, cf.getCofName());
			insert.setInt(2, cf.getSupId());
			insert.setDouble(3, cf.getPrice());
			insert.setInt(4, cf.getSales());
			insert.setInt(5, cf.getTotal());
			result = insert.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("SQL Error" + e.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back. ");
					con.rollback();
				} catch (SQLException ex) {
					System.out.println("Rollback Error:" + ex.getMessage());
				}
			}
		} finally {
			con.setAutoCommit(true);
			if (insert != null) {
				insert.close();
			}
			if (insert != null) {
				insert.close();
			}
		}
		return result;
	}

	public int updateCoffeeSales(Coffee cf) throws SQLException {
		Connection con = null;
		PreparedStatement updateSales = null;
		PreparedStatement updateTotal = null;

		String updateString = "update classicmodels.COFFEES " + "set SALES = ? where COF_NAME = ?";

		String updateStatement = "update classicmodels.COFFEES " + "set TOTAL =  ? " + "where COF_NAME = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei",
					"root", "1234");

			con.setAutoCommit(false);
			updateSales = con.prepareStatement(updateString);
			updateTotal = con.prepareStatement(updateStatement);

			// for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
			updateSales.setInt(1, cf.getSales());
			updateSales.setString(2, cf.getCofName());
			int r1 = updateSales.executeUpdate();
			updateTotal.setInt(1, cf.getTotal());
			updateTotal.setString(2, cf.getCofName());
			int r2 = updateTotal.executeUpdate();
			if (r1 > 0 && r2 > 0) {
				con.commit();
				return 1;
			} else {
				con.rollback();
				System.out.println("Transaction Failed");
				return 0;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println(e.getMessage());
				}
			}
		} finally {

			con.setAutoCommit(true);
			if (updateSales != null) {
				updateSales.close();
			}
			if (updateTotal != null) {
				updateTotal.close();
			}

		}
		return 0;
	}
	public int deleteCoffee(Coffee cf) {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement st = null;
		String sql = "DELETE FROM classicmodels.coffees WHERE cof_name = ?";
		
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setString(1, cf.getCofName());
			int r = st.executeUpdate();
			if(r > 0) {
				con.commit();
				result = 1;
			}
			else {
				System.out.println("Transaction is Rolling Back");
				try{
					con.rollback();
				} catch (SQLException e){
					System.out.println("Transaction Failed");
					e.printStackTrace();
				}
				result = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return result;
	}
}
