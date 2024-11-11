package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import static java.sql.Date.valueOf;
import entity.Order;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
			ps.setDate(6, valueOf(order.getCreatedAt()));
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
		ps.setDate(5, valueOf(order.getCreatedAt()));
		ps.setString(6, order.getId());
		ps.executeUpdate();
		return true;
	}

	public Order getOrderById(String id) throws SQLException {
		Connection connectDB = ConnectDB.getInstance().getConnection();
		String sql = "SELECT * FROM Order WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getString("id"));

			//
			Customer_Dao customer = new Customer_Dao();

			order.setCustomer(customer.getCustomer(rs.getString("customer_id")));
			order.setOrderDate(rs.getDate("order_date"));
			order.setTotalAmount(rs.getDouble("total_amount"));
			order.setStatus(rs.getString("status"));
			order.setCreatedAt(rs.getDate("created_at").toLocalDate());
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

			order.setCustomer(customer.getCustomer(rs.getString("customer_id")));
			order.setOrderDate(rs.getDate("order_date"));
			order.setTotalAmount(rs.getDouble("total_amount"));
			order.setStatus(rs.getString("status"));
			order.setCreatedAt(rs.getDate("created_at").toLocalDate());
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
        public double getRevenueToday() throws SQLException {
        Connection connectDB = ConnectDB.getInstance().getConnection();
        String sql = "SELECT SUM(total_amount) AS revenue FROM orders WHERE order_date = ?";
        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("revenue");
        }
        return 0;
    }

    public double getRevenueYesterday() throws SQLException {
        Connection connectDB = ConnectDB.getInstance().getConnection();
        String sql = "SELECT SUM(total_amount) AS revenue FROM orders WHERE order_date = ?";
        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(LocalDate.now().minusDays(1)));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("revenue");
        }
        return 0;
    }

public Map<LocalDate, Double> getRevenueLast7DaysByDate() throws SQLException {
    Map<LocalDate, Double> revenueByDate = new HashMap<>();
    LocalDate today = LocalDate.now();
    LocalDate startDate = today.minusDays(6);

    // Tạo một map với doanh thu 0 cho tất cả các ngày trong khoảng thời gian 7 ngày qua
    for (LocalDate date = startDate; !date.isAfter(today); date = date.plusDays(1)) {
        revenueByDate.put(date, 0.0);
    }

    // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật doanh thu thực tế cho các ngày có dữ liệu
    Connection connectDB = ConnectDB.getInstance().getConnection();
    String sql = "SELECT order_date, SUM(total_amount) AS revenue FROM orders WHERE order_date BETWEEN ? AND ? GROUP BY order_date";
    PreparedStatement ps = connectDB.prepareStatement(sql);
    ps.setDate(1, Date.valueOf(startDate));
    ps.setDate(2, Date.valueOf(today));
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        LocalDate date = rs.getDate("order_date").toLocalDate();
        double revenue = rs.getDouble("revenue");
        revenueByDate.put(date, revenue);
    }

    return revenueByDate;
}


    public Map<LocalDate, Double> getDailyRevenueThisMonth() throws SQLException {
        Map<LocalDate, Double> revenueByDate = new HashMap<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);

        // Tạo một map với doanh thu 0 cho tất cả các ngày trong tháng này
        for (LocalDate date = startOfMonth; !date.isAfter(today); date = date.plusDays(1)) {
            revenueByDate.put(date, 0.0);
        }

        // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật doanh thu thực tế cho các ngày có dữ liệu
        Connection connectDB = ConnectDB.getInstance().getConnection();
        String sql = "SELECT order_date, SUM(total_amount) AS revenue FROM orders WHERE order_date BETWEEN ? AND ? GROUP BY order_date";
        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(startOfMonth));
        ps.setDate(2, Date.valueOf(today));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            LocalDate date = rs.getDate("order_date").toLocalDate();
            double revenue = rs.getDouble("revenue");
            revenueByDate.put(date, revenue);
        }

        return revenueByDate;
    }



    public Map<LocalDate, Double> getDailyRevenueLastMonth() throws SQLException {
    Map<LocalDate, Double> revenueByDate = new HashMap<>();
    LocalDate firstDayOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
    LocalDate lastDayOfLastMonth = firstDayOfLastMonth.withDayOfMonth(firstDayOfLastMonth.lengthOfMonth());

    // Tạo một map với doanh thu 0 cho tất cả các ngày trong tháng trước
    for (LocalDate date = firstDayOfLastMonth; !date.isAfter(lastDayOfLastMonth); date = date.plusDays(1)) {
        revenueByDate.put(date, 0.0);
    }

    // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật doanh thu thực tế cho các ngày có dữ liệu
    Connection connectDB = ConnectDB.getInstance().getConnection();
    String sql = "SELECT order_date, SUM(total_amount) AS revenue FROM orders WHERE order_date BETWEEN ? AND ? GROUP BY order_date";
    PreparedStatement ps = connectDB.prepareStatement(sql);
    ps.setDate(1, Date.valueOf(firstDayOfLastMonth));
    ps.setDate(2, Date.valueOf(lastDayOfLastMonth));
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        LocalDate date = rs.getDate("order_date").toLocalDate();
        double revenue = rs.getDouble("revenue");
        revenueByDate.put(date, revenue);
    }

    return revenueByDate;
}



    public Map<Integer, Double> getMonthlyRevenueThisYear() throws SQLException {
    Map<Integer, Double> monthlyRevenue = new HashMap<>();
    Connection connectDB = ConnectDB.getInstance().getConnection();
    
    String sql = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS revenue " +
                 "FROM orders WHERE YEAR(order_date) = ? " +
                 "GROUP BY MONTH(order_date) " +
                 "ORDER BY month";

    PreparedStatement ps = connectDB.prepareStatement(sql);
    ps.setInt(1, LocalDate.now().getYear());

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        int month = rs.getInt("month");
        double revenue = rs.getDouble("revenue");
        monthlyRevenue.put(month, revenue);
    }

    rs.close();
    ps.close();
    return monthlyRevenue;
}

    public Map<Integer, Double> getMonthlyRevenueLastYear() throws SQLException {
        Map<Integer, Double> monthlyRevenue = new HashMap<>();
        Connection connectDB = ConnectDB.getInstance().getConnection();

        String sql = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS revenue " +
                     "FROM orders WHERE YEAR(order_date) = ? " +
                     "GROUP BY MONTH(order_date) " +
                     "ORDER BY month";

        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setInt(1, LocalDate.now().getYear() - 1);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int month = rs.getInt("month");
            double revenue = rs.getDouble("revenue");
            monthlyRevenue.put(month, revenue);
        }

        rs.close();
        ps.close();
        return monthlyRevenue;
    }

    public double getRevenueSamePeriodLastMonth() throws SQLException {
    Connection connectDB = ConnectDB.getInstance().getConnection();
    String sql = "SELECT SUM(total_amount) AS revenue FROM orders WHERE order_date = ?";
    PreparedStatement ps = connectDB.prepareStatement(sql);
    // Tính ngày của cùng kỳ tháng trước
    LocalDate sameDayLastMonth = LocalDate.now().minusMonths(1);
    ps.setDate(1, Date.valueOf(sameDayLastMonth));
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        return rs.getDouble("revenue");
    }
    return 0;
}

        
        // 
}
