package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.LoginUserBean;

/**
 * ユーザ登録DAOクラス
 */
public class UserRegistDao {

	// データベース接続と結果取得の為の変数
	private Connection con = null;
	private PreparedStatement ps = null;

	// データベース接続に使用する情報
	private final String JDBC_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost/shop2";
	private final String DB_USER = "root";
	private final String DB_PASS = "MYSQLJUNYA";

	/**
	 * データベースにユーザ情報を登録します
	 * @param bean 登録するユーザ情報
	 * @return 　　登録件数
	 * @throws 　　SQLException
	 */
	public int insert(LoginUserBean bean) throws SQLException, SQLIntegrityConstraintViolationException {

		int result = 0;

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "insert into user(id, pass, name, age) values(?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, bean.getId());
			ps.setString(2, bean.getPass());
			ps.setString(3, bean.getName());
			ps.setInt(4, bean.getAge());

			result = ps.executeUpdate();

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		} catch(SQLIntegrityConstraintViolationException e) {

			// userテーブルのカラムid(ユーザID)はprimarykeyの為、ユーザIDが重複していた場合キャッチして0を返す
			return result;

		}

		return result;

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

		} catch(SQLException e) {

			// データベース接続との接続解除に失敗した場合
			e.printStackTrace();

		}
	}

}
