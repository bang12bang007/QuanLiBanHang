package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;

import entity.Order;

public class Order_Dao {
	private ArrayList<Order> order_list;

	public Order_Dao() {
		order_list = new ArrayList<Order>();
	}
                public boolean addOrderList(Order od){
                    if(order_list.contains(od)){
                        return false;
                    }else{
                        order_list.add(od);
                        return false;
                    }
                }
	public ArrayList<Order> getOrderList() {
		return order_list;
	}

	public boolean addOrder(Order order) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "INSERT INTO Order VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = connectDB.prepareStatement(sql);

		if (order_list.contains(order)) {
			return false;
		} else {
			ps.setString(1, order.getId());
			ps.setString(2, order.getCustomer().getId());
			ps.setDate(3, order.getOrderDate());
			ps.setDouble(4, order.getTotalAmount());
			ps.setString(5, order.getStatus());
			ps.setTimestamp(6, order.getCreatedAt());
			ps.executeUpdate();
			return true;
		}
	}

	public boolean deleteOrder(int id) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "DELETE FROM Order WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		return true;
	}

	public boolean updateOrder(Order order) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "UPDATE Order SET customer_id = ?, order_date = ?, total_amount = ?, status = ?, created_at = ? WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setString(1, order.getCustomer().getId());
		ps.setDate(2, order.getOrderDate());
		ps.setDouble(3, order.getTotalAmount());
		ps.setString(4, order.getStatus());
		ps.setTimestamp(5, order.getCreatedAt());
		ps.setString(6, order.getId());
		ps.executeUpdate();
		return true;
	}

	public Order getOrderById(int id) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Order WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getString("id"));

			//
			Customer_Dao customer = new Customer_Dao();

			order.setCustomer(customer.getCustomer(rs.getInt("customer_id")));
			order.setOrderDate(rs.getDate("order_date"));
			order.setTotalAmount(rs.getDouble("total_amount"));
			order.setStatus(rs.getString("status"));
			order.setCreatedAt(rs.getTimestamp("created_at"));
			return order;
		}
		return null;

	}

	// getall
	public ArrayList<Order> getAllOrder() throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Order";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getString("id"));

			//
			Customer_Dao customer = new Customer_Dao();

			order.setCustomer(customer.getCustomer(rs.getInt("customer_id")));
			order.setOrderDate(rs.getDate("order_date"));
			order.setTotalAmount(rs.getDouble("total_amount"));
			order.setStatus(rs.getString("status"));
			order.setCreatedAt(rs.getTimestamp("created_at"));
			order_list.add(order);
		}
		return order_list;
	}
        public Double getTotalOrders(){
            double total =0;
            for(int i =0; i< order_list.size();i++){
                total += order_list.get(i).getTotalAmount();
            }
            return total;
        }
}
