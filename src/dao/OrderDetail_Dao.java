package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.OrderDetail;
import javax.swing.table.DefaultTableModel;

public class OrderDetail_Dao {
	private ArrayList<OrderDetail> orderDetail_list;

	public OrderDetail_Dao() {
		orderDetail_list = new ArrayList<OrderDetail>();
	}

	public boolean addOrderDetail(OrderDetail orderDetail, DefaultTableModel model) throws Exception {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "INSERT INTO OrderDetail VALUES(?,?,?,?,?)";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			if (orderDetail_list.contains(orderDetail)) {
				return false;
			} else {
				ps.setString(1, orderDetail.getId());
				ps.setString(2, orderDetail.getOrder().getId());
				ps.setString(3, orderDetail.getItem().getId());
				ps.setInt(4, orderDetail.getQuantity());
				ps.setDouble(5, orderDetail.getPrice());
				int rowsAffected = ps.executeUpdate();
                                                                        if (rowsAffected > 0) {
                                                                            Object[] rowData = new Object[] {
                                                                                orderDetail.getId(),
                                                                                orderDetail.getOrder().getId(),
                                                                                orderDetail.getItem().getId(),
                                                                                orderDetail.getQuantity(),
                                                                                orderDetail.getPrice(),
                                                                                orderDetail.getTotal(),
                                                                                orderDetail.getCreatedAt()
                                                                            };
                                                                            model.addRow(rowData);
                                                                            return true;
                                                                        }
                                                     }
                                    } catch (Exception e) {
                                                    e.printStackTrace();
                                    }
                                     return false;
}

	// get all order details
	public ArrayList<OrderDetail> getOrderDetailList() {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM OrderDetail";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(rs.getString("id"));
				Order_Dao orderDao = new Order_Dao();
				orderDetail.setOrder(orderDao.getOrderById(rs.getString("order_id")));
				Item_Dao itemDao = new Item_Dao();
				orderDetail.setItem(itemDao.getItemByID(rs.getString("item_id")));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setPrice(rs.getDouble("price"));
				orderDetail_list.add(orderDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetail_list;
	}
	
	public OrderDetail getOrderDetailById(int id) {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM OrderDetail WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(rs.getString("id"));
				Order_Dao orderDao = new Order_Dao();
				orderDetail.setOrder(orderDao.getOrderById(rs.getString("order_id")));
				Item_Dao itemDao = new Item_Dao();
				orderDetail.setItem(itemDao.getItemByID(rs.getString("item_id")));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setPrice(rs.getDouble("price"));
				return orderDetail;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateOrderDetail(OrderDetail orderDetail) throws Exception {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "UPDATE OrderDetail SET order_id = ?, item_id = ?, quantity = ?, price = ? WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, orderDetail.getOrder().getId());
			ps.setString(2, orderDetail.getItem().getId());
			ps.setInt(3, orderDetail.getQuantity());
			ps.setDouble(4, orderDetail.getPrice());
			ps.setString(5, orderDetail.getId());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteOrderDetail(int id) throws Exception {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "DELETE FROM OrderDetail WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
