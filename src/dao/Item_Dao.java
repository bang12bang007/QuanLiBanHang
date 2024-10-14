package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.Item;

public class Item_Dao {
	private ArrayList<Item> item_list;

	public Item_Dao() {
		item_list = new ArrayList<Item>();
	}

	public boolean addItem(Item item) {

		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "INSERT INTO Item VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, item.getId());
			ps.setString(2, item.getName());
			ps.setDouble(3, item.getPrice());
			ps.setInt(4, item.getQuantity());
			ps.setString(5, item.getCategory());
			ps.setTimestamp(6, item.getCreatedAt());
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
			String sql = "DELETE FROM Item WHERE id = ?";
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
			String sql = "UPDATE Item SET name = ?, price = ?, quantity = ?, category = ?, createdAt = ? WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setDouble(2, item.getPrice());
			ps.setInt(3, item.getQuantity());
			ps.setString(4, item.getCategory());
			ps.setTimestamp(5, item.getCreatedAt());
			ps.setInt(6, item.getId());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeItem(Item item) {
		try {
			Connection connectDB = ConnectDB.getInstance().getConnection();
			String sql = "DELETE FROM Item WHERE id = ?";
			PreparedStatement ps = connectDB.prepareStatement(sql);
			ps.setInt(1, item.getId());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Item getItemByID(int id) throws SQLException {
        try {
            Connection connectDB = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM Item WHERE id = ?";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Item(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("category"), rs.getTimestamp("createdAt"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}
	
	public ArrayList<Item> getAllItem() throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Item";
		PreparedStatement ps = connectDB.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategory(rs.getString("category"));
			item.setCreatedAt(rs.getTimestamp("createdAt"));
			item_list.add(item);
		}
		return item_list;
	}

}
