package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ログインDAOクラス
 */
public class LoginDao {

	// データベース接続と結果取得の為の変数
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// AWS上でデータベース接続に使用する情報
	private final String RDS_HOSTNAME = System.getProperty("RDS_HOSTNAME");
	private final String RDS_PORT = System.getProperty("RDS_PORT");
	private final String RDS_DB_NAME = System.getProperty("RDS_DB_NAME");
	private final String RDS_USERNAME = System.getProperty("RDS_USERNAME");
	private final String RDS_PASSWORD = System.getProperty("RDS_PASSWORD");
	private final String JDBC_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://" + RDS_HOSTNAME + ":" + RDS_PORT + "/" + RDS_DB_NAME + "?user=" + RDS_USERNAME + "&password=" + RDS_PASSWORD;


	/**
	 * データベースから、指定されたIDとパスワードを使ってユーザ情報を検索します
	 * @param id 	ログインID
	 * @param pass パスワード
	 * @return     ユーザ情報(ResultSet)
	 * @throws     SQLException
	 */
	public ResultSet selectUser(String id, String pass) throws SQLException {

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL);

			String sql = "select name, age from user where id = ? and pass = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pass);

			rs = ps.executeQuery();

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		}

		return rs;

	}

	/**
	 * コネクションをクローズします
	 */
	public void close() {

		try {

			// データベースとの接続を解除する
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}

		} catch(SQLException e) {

			// データベース接続との接続解除に失敗した場合
			e.printStackTrace();

		}
	}
}
