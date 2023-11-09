package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UsersDAO extends BaseDAO {

	public static void registerUser(User user) {

		try (Connection con = getConnection();
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO users (user_id, username, password) VALUES (UUID(), ?, ?)")) {

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());

			int r = ps.executeUpdate();

			if (r != 0) {
				System.out.println("新規登録成功");
			} else {
				System.out.println("新規登録失敗");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isUserNameExists(String userName) {
		boolean exists = false;

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT USERNAME FROM USERS WHERE USERNAME = ?")) {

			ps.setString(1, userName);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					exists = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public static User findUser(User user) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT user_id, username, password FROM users WHERE username = ? AND password = ?")) {

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user.setUserId(rs.getString("user_id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
