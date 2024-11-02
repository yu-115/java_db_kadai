package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		//データベース接続情報
		final String URL = "jdbc:mysql://localhost/challenge_java";
		final String USER_NAME = "root";
		final String PASSWORD = "tKcLfULY!W%Lp4X";
		
		//INSERT文のフォーマット
		String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?);";
		String sql2 = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002;";
		
		//投稿データ
		int[] userId = {1003, 1002, 1003, 1001, 1002};
		String[] postedAt = {"2023-02-08", "2023-02-08", "2023-02-09", "2023-02-09", "2023-02-10"};
		String[] postContent = {"昨日の夜は徹夜でした・・", "お疲れ様です！", "今日も頑張ります！", "無理は禁物ですよ", "明日から連休ですね！"};
		int[] likes = {13, 12, 18, 17, 20};
		
		//データベース接続&SQL文の送信準備
		try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				PreparedStatement statement = con.prepareStatement(sql);
				PreparedStatement statement2 = con.prepareStatement(sql2)) {
			System.out.println("データベース接続成功：" + con);
			System.out.println("レコード追加を実行します");
			
			int rowCnt = 0;
			for(int i = 0; i < userId.length; i++) {
				//SQL文の？部分をリストのデータに置き換え
				statement.setInt(1, userId[i]);
				statement.setString(2, postedAt[i]);
				statement.setString(3, postContent[i]);
				statement.setInt(4, likes[i]);
				
				//SQL文を実行（DBMSに送信）
				System.out.println("レコード追加=" + statement.toString() );
				rowCnt += statement.executeUpdate();
			}
				System.out.println(rowCnt + "件のレコードが追加されました");
				
				ResultSet result = statement2.executeQuery();
				
				//SQL文の検索結果を抽出
				System.out.println("ユーザーIDが1002のレコードを検索しました");
				while(result.next()) {
					Date resultAt = result.getDate("posted_at");
					String resultContent = result.getString("post_content");
					int resultLikes = result.getInt("likes");
					
					System.out.println(result.getRow() + "件目：投稿日時=" + resultAt
							+ "/投稿内容=" + resultContent + "/いいね数=" + resultLikes);
				}
			} catch (SQLException e) {
			System.out.println("データベース接続失敗:" + e.getMessage());

		}

	}

}