package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Customer;

public class Customer_Dao {
	private ArrayList<Customer> customer_list;

	public Customer_Dao() {
		customer_list = new ArrayList<Customer>();
	}

	public boolean addCustomer(Customer customer) {

		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, customer.getId());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhone());
			ps.setString(5, customer.getAddress());
			ps.setTimestamp(6, customer.getCreatedAt());
			
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public ArrayList<Customer> getCustomerList() {
		return customer_list;
	}

	public boolean deleteCustomer(int id) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "DELETE FROM Customer WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateCustomer(Customer customer) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "UPDATE Customer SET name = ?, email = ?, phone = ?, address = ?, created_at = ? WHERE id = ?";
		
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getAddress());
			ps.setTimestamp(5, customer.getCreatedAt());
			ps.setString(6, customer.getId());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Customer getCustomer(String id) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM Customer WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id1 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				  
				Timestamp createdAt = rs.getTimestamp("created_at");
				Customer customer = new Customer(id1, name, email, phone, address, createdAt);
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Customer> getAllCustomer() throws SQLException {

		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Customer";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String address = rs.getString("address");
			java.sql.Timestamp createdAt = rs.getTimestamp("created_at");
			Customer customer = new Customer(id, name, email, phone, address, createdAt);
			customer_list.add(customer);
		}
		return customer_list;

	}

}
