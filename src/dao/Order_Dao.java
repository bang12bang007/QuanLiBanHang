package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import static java.sql.Date.valueOf;
import entity.Order;
import java.util.Date;
import java.util.List;
import static utils.AppUtils.*;
import utils.AppUtils.DuplicateOrderException;

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
                            try {
                                Connection connectDB = ConnectDB.getInstance().getConnection();
                                        String sql = "INSERT INTO orders VALUES(?,?,?,?,?)";
                                        PreparedStatement ps = connectDB.prepareStatement(sql);
                                        
                                        if (order_list.contains(order)) {
                                                return false;
                                        } else {
                                                ps.setString(1, order.getId());
                                                ps.setString(2, order.getCustomer().getId());
                                                ps.setDate(3, java.sql.Date.valueOf(order.getOrderDate()));
                                                ps.setDouble(4, order.getTotalAmount());
                                                ps.setString(5, order.getStatus());
                                                ps.executeUpdate();
                                                return true;
                                        }
                            } catch (SQLException e) {
//                                e.printStackTrace();
                                return false;   
                            }
                        }

	public boolean deleteOrder(int id) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "DELETE FROM orders WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		return true;
	}

	public boolean updateOrder(Order order) throws SQLException, DuplicateOrderException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "UPDATE orders SET customer_id = ?, order_date = ?, total_amount = ?, status = ?, created_at = ? WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setString(1, order.getCustomer().getId());
		ps.setDate(5, java.sql.Date.valueOf(order.getOrderDate()));
		ps.setDouble(3, order.getTotalAmount());
		ps.setString(4, order.getStatus());
		ps.setString(6, order.getId());
		ps.executeUpdate();
		return true;
	}

	public Order getOrderById(int id) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM orders WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getString("id"));
			Customer_Dao customer = new Customer_Dao();
			order.setCustomer(customer.getCustomer(rs.getInt("customer_id")));
			order.setOrderDate(rs.getDate("order_date").toLocalDate());
			order.setTotalAmount(rs.getDouble("total_amount"));
			order.setStatus(rs.getString("status"));
			return order;
		}
		return null;
	}

    public List<Object[]> getAllOrder() throws SQLException {
                Connection connectDB = ConnectDB.getInstance().getConnection();
                String sql = "SELECT id,customer_id,order_date,total_amount,status FROM orders";
                PreparedStatement ps = connectDB.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Object[]> data = new ArrayList<>();
	while (rs.next()) {
                        String id = rs.getString("id");
                        String customer = rs.getString("customer_id");
                        String customer_id = (customer == null) ? "Trống":customer;
                        Date order_date_non = rs.getDate("order_date");
                        String order_date = String.valueOf(order_date_non);
                        double total_amount = rs.getInt("total_amount");
                        String status = rs.getString("status");
                        Object[] row = { id,customer_id,formatMoney(total_amount),order_date,status};
                        data.add(row);
                 }
                rs.close();
                ps.close();
                connectDB.close();
                return data;
    }
        public Double getTotalOrders(){
            double total =0;
            for(int i =0; i< order_list.size();i++){
                total += order_list.get(i).getTotalAmount();
            }
            return total;
        }
        public boolean isOrderIDExist(String orderID) throws SQLException{
            Connection connectDB = ConnectDB.getInstance().getConnection();
            String sql = "SELECT COUNT(*) FROM orders WHERE id = ?";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1, orderID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                 return rs.getInt(1) > 0;
            }
            return false;
        }        
}
