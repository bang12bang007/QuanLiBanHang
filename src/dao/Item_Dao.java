package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.Item;
import java.util.List;
import utils.AppUtils.*;

public class Item_Dao {
	private ArrayList<Item> item_list;

	public Item_Dao() {
		item_list = new ArrayList<Item>();
	}

	public boolean addItem(Item item) {

		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "INSERT INTO items VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, item.getId());
			ps.setString(2, item.getName());
			ps.setDouble(3, item.getPrice());
			ps.setInt(4, item.getQuantity());
			ps.setString(5, item.getCategory());
			ps.setTimestamp(6, item.getCreatedAt());
			ps.setDate(7, item.getExpiredDate());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public ArrayList<Item> getItemList() {
		return item_list;
	}

	public boolean deleteItem(int id) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "DELETE FROM items WHERE id = ?";
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

	public boolean updateItem(Item item) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "UPDATE items SET name = ?, price = ?, quantity = ?, category = ?, created_at = ? WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setDouble(2, item.getPrice());
			ps.setInt(3, item.getQuantity());
			ps.setString(4, item.getCategory());
			ps.setTimestamp(5, item.getCreatedAt());
			ps.setString(6, item.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

// xóa sản phẩm theo id
	public boolean deleteItem(String id) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "DELETE FROM items WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Item getItemByID(String id) throws SQLException {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM items WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Item(rs.getString("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"),
						rs.getString("category"), rs.getTimestamp("created_at"), rs.getDate("expiredDate"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Item> getAllItem() throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Items";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("created_at"));
			item.setExpiredDate(rs.getDate("expiredDate"));
			item_list.add(item);
		}
		return item_list;
	}

	public List<Object[]> getItemFromSQL() throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT id, name, price, quantity, category FROM items";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Object[]> data = new ArrayList<>();
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			int quantity = rs.getInt("quantity");
			String category = rs.getString("category");
			Object[] row = { id, name, price, quantity, category };
			data.add(row);
		}
		rs.close();
		ps.close();
		connectDB.close();
		return data;
	}

	public Item getItemById(String id) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Items WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Item item = new Item();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("created_at"));
			item.setExpiredDate(rs.getDate("expiredDate"));
			return item;
		}
		return null;
	}

	// getb category
	public ArrayList<Item> getItemByCategory(String category) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM items WHERE category = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("created_at"));
			item.setExpiredDate(rs.getDate("expiredDate"));
			item_list.add(item);
		}
		return item_list;
	}

	// tìm sản phẩm quá hạn
	public ArrayList<Item> getItemExpired() throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Items WHERE expiredDate < GETDATE()";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("created_at"));
			item.setExpiredDate(rs.getDate("expiredDate"));
			item_list.add(item);
		}
		return item_list;
	}

	// tìm sản phẩm hết hạn theo category
	public ArrayList<Item> getItemExpiredByCategory(String category) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Items WHERE expiredDate < GETDATE() AND category = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("created_at"));
			item.setExpiredDate(rs.getDate("expiredDate"));
			item_list.add(item);
		}
		return item_list;
	}

	// cập nhật só lượng sản phẩm khi có mã
	public boolean updateQuantityItem(String id, int quantity) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "UPDATE Items SET quantity = ? WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setString(2, id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// tìm theo tên sản phẩm
	public ArrayList<Item> getItemByName(String name) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Items WHERE name = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("created_at"));
			item.setExpiredDate(rs.getDate("expiredDate"));
			item_list.add(item);
		}
		return item_list;
	}

	public boolean updateQuantity(String id, int newQuantity) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "UPDATE items SET quantity = ? WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, newQuantity);
			ps.setString(2, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Item> getItemByNameFinal(String name) throws SQLException {
		ArrayList<Item> item_list = new ArrayList<>();
		Connection connectDB = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connectDB = ConnectDB.getInstance().getConnection();

			String sql = "SELECT * FROM Items WHERE LOWER(name) LIKE LOWER(?)";
			ps = connectDB.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setCategory(rs.getString("category"));
				item.setCreatedAt(rs.getTimestamp("created_at"));
				item.setExpiredDate(rs.getDate("expiredDate"));
				item_list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log lỗi hoặc xử lý theo yêu cầu
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (connectDB != null)
				connectDB.close();
		}

		return item_list;
	}

}
