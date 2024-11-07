package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		//データベース接続情報
		final String URL = "jdbc:mysql://localhost/challenge_java";
		final String USER_NAME = "root";
		final String PASSWORD = "tKcLfULY!W%Lp4X";
		
		//INSERT文のフォーマット
		String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
		String sql2 = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
		
		//データベース接続&SQL文の送信準備
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				Statement statement = con.createStatement()) {
			System.out.println("データベース接続成功：" + con);
			
			//更新のSQL文を実行（DBMSに送信）
			System.out.println("レコード更新：" + statement.toString());
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました");
			
			//並び替えのSQL文を実行（DBMSに送信）
			System.out.println("レコード取得：" + sql2);
			ResultSet result = statement.executeQuery(sql2);
			System.out.println("数学・英語の点数が高い順に並び替えました");
			
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int scoreMath = result.getInt("score_math");
				int scoreEnglish = result.getInt("score_english");
				
				System.out.println(result.getRow() + "件目：生徒ID=" + id
						+ "/氏名=" + name + "/数学=" + scoreMath + "/英語=" + scoreEnglish);
			}
		} catch (SQLException e) {
			System.out.println("データベース接続失敗:" + e.getMessage());
		}

	}

}