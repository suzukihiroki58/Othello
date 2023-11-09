package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	protected static final String url = "jdbc:mariadb://localhost:3306/othello";
	protected static final String username = "root";
	protected static final String password = "";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	protected static void closeResources(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
