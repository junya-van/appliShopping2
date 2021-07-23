package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ShoppingDao;

/**
 * ショッピング処理クラス
 */
public class ShoppingLogic {

	/**
	 * 商品一覧のリストを返却します
	 * @return 商品一覧のリスト
	 */
	public ArrayList<ItemBean> getItem() {

		ArrayList<ItemBean> beanList = new ArrayList<>();
		ShoppingDao dao = null;
		ResultSet rs = null;

		try {

			dao = new ShoppingDao();
			rs = dao.selectItem();

			// 検索結果を1レコードずつ処理
			while(rs.next()) {

				ItemBean bean = new ItemBean();

				bean.setItemId(rs.getString("item_id"));
				bean.setItemName(rs.getString("item_name"));
				bean.setPrice(rs.getInt("price"));
				bean.setText(rs.getString("text"));
				bean.setQuantity(rs.getInt("quantity"));

				beanList.add(bean);

			}

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			// 処理終了時に各接続を解除
			dao.close();

		}

		return beanList;

	}

	/**
	 * 商品IDを基に商品情報を返却します
	 * @return       商品情報
	 * @param itemId 商品ID
	 */
	public ItemBean getItem(String itemId) {

		ItemBean bean = null;
		ShoppingDao dao = null;
		ResultSet rs = null;

		try {

			dao = new ShoppingDao();
			rs = dao.selectItem(itemId);

			if(rs.next()) {

				bean = new ItemBean();
				bean.setItemId(rs.getString("item_id"));
				bean.setItemName(rs.getString("item_name"));
				bean.setPrice(rs.getInt("price"));
				bean.setText(rs.getString("text"));
				bean.setQuantity(rs.getInt("quantity"));

			} else {

				return bean;

			}


		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			// 処理終了時に各接続を解除
			dao.close();

		}

		return bean;

	}

	/**
	 * 購入履歴のリストを返却します
	 * @return   購入履歴のリスト
	 * @param id ユーザID
	 */
	public ArrayList<HistoryBean> getHistory(String id) {

		ArrayList<HistoryBean> beanList = new ArrayList<>();
		ShoppingDao dao = null;
		ResultSet rs = null;

		try {

			dao = new ShoppingDao();
			rs = dao.selectHistory(id);

			// 検索結果を1レコードずつ処理
			while(rs.next()) {

				HistoryBean bean = new HistoryBean();

				bean.setItemId(rs.getString("item_id"));
				bean.setItemName(rs.getString("item_name"));
				bean.setQuantity(rs.getInt("quantity"));
				// 購入日を設定
				// java.sql.date型をjava.time.LocalDate型に変換する
				bean.setDate(rs.getDate("date").toLocalDate());

				beanList.add(bean);

			}

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			// 処理終了時に各接続を解除
			dao.close();

		}

		return beanList;
	}
}
