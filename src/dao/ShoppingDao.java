package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import model.ItemBean;

/**
 * ショッピングDAOクラス
 */
public class ShoppingDao {

	// データベース接続と結果取得の為の変数
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// データベース接続に使用する情報
	private final String JDBC_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost/shop2";
	private final String DB_USER = "root";
	private final String DB_PASS = "MYSQLJUNYA";

	/**
	 * データベースから商品と在庫を取得します
	 * @return 商品情報(ResultSet)
	 * @throws SQLException
	 */
	public ResultSet selectItem() throws SQLException {

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "select item.item_id, item.item_name, item.price, item.text, stock.quantity from item inner join stock on item.item_id = stock.item_id";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		}

		return rs;

	}

	/**
	 * 商品IDを基にデータベースから商品と在庫を取得します
	 * @param itemId 商品ID
	 * @return       商品情報(ResultSet)
	 * @throws       SQLException
	 */
	public ResultSet selectItem(String itemId) throws SQLException {

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "select item.item_id, item.item_name, item.price, item.text, stock.quantity from item inner join stock on item.item_id = stock.item_id where item.item_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, itemId);

			rs = ps.executeQuery();

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		}

		return rs;

	}

	/**
	 * データベースからユーザの購入履歴を取得します
	 * @param id ユーザID
	 * @return   購入履歴(ResultSet)
	 * @throws   SQLException
	 */
	public ResultSet selectHistory(String id) throws SQLException {

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "select history.item_id, item.item_name, history.quantity, history.date from history inner join item on history.id = ? and history.item_id = item.item_id";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		}

		return rs;

	}

	/**
	 * カート情報を基にデータベースの在庫を更新(マイナス)します
	 * @param  cart カート情報
	 * @throws SQLException
	 */
	public void updateItem(List<ItemBean> cart) throws SQLException {

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// 手動コミットモードに切り替え
			con.setAutoCommit(false);

			String sql = "update stock set quantity = quantity - ? where item_id = ?";

			// カートの要素数分SQL文を設定して実行
			for(ItemBean bean : cart) {

				ps = con.prepareStatement(sql);
				ps.setInt(1, bean.getCount());
				ps.setString(2, bean.getItemId());
				ps.executeUpdate();

			}

			// コミット
			con.commit();

		} catch(SQLException e) {

			try {
				// ロールバック
				con.rollback();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		}

	}

	/**
	 * 購入履歴テーブルを更新します
	 * @param cart カート情報
	 * @param id   ユーザID
	 */
	public void updateHistory(List<ItemBean> cart, String id) throws SQLException {

		try {

			Class.forName(JDBC_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// 手動コミットモードに切り替え
			con.setAutoCommit(false);

			String sql = "insert into history (id, item_id, quantity, date) values (?, ?, ?, ?)";

			// カートの要素数分SQL文を設定して実行
			for(ItemBean bean : cart) {

				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, bean.getItemId());
				ps.setInt(3, bean.getCount());
				ps.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
				ps.executeUpdate();

			}

			// コミット
			con.commit();

		} catch(SQLException e) {

			try {
				// ロールバック
				con.rollback();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}

		} catch(ClassNotFoundException e) {

			// JDBCドライバが見つからなかった場合
			e.printStackTrace();

		}
	}

	/**
	 * コネクションをクローズします
	 */
	public void close() {
		try {

			//データベースとの接続を解除
			if(con != null) {
				con.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}

		} catch(SQLException e) {

			// 接続解除に失敗
			e.printStackTrace();

		}

	}
}
