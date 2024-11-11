package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con ;
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
            return instance;
	}

	public static Connection getConnection() {

		String url = "jdbc:sqlserver://localhost:1433;databasename=taphoa";

            String user = "sa";
            String password = "123456789";
            try {
		con = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
		ex.printStackTrace();
            }
            return con;
	}

	public static void disconnect() {
            if (con != null)
		try {
                    con.close();
		} catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
}
