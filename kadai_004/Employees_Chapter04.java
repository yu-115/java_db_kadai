package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {

	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost/challenge_java";
		final String USER_NAME = "root";
		final String PASSWORD = "tKcLfULY!W%Lp4X";
		
		String sql = """
				CREATE TABLE employees (
				id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
				name VARCHAR(60) NOT NULL,
				email VARCHAR(255) NOT NULL,
				age INT(11),
				address VARCHAR(255)
				);
				""";
		
		try(Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			Statement statement = con.createStatement()) {
				int rowCnt = statement.executeUpdate(sql);
				System.out.println("データベース接続成功");
				System.out.println("テーブルを作成:rowCnt=" + rowCnt);
			} catch(SQLException e) {
				
				System.out.println("データベース接続失敗:" + e.getMessage());
			}

	}

}
