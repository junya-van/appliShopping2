package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.LoginDao;

/**
 * ログイン処理クラス
 */
public class LoginLogic {

	/**
	 * ユーザ情報を返却します
	 * @param id 　　ユーザID
	 * @param pass 　パスワード
	 * @return 　　　ユーザ情報
	 */
	public LoginUserBean getUserData(String id, String pass) {

		LoginUserBean bean = null;
		LoginDao dao = null;
		ResultSet rs = null;

		try {

			dao = new LoginDao();
			rs = dao.selectUser(id, pass);

			if(rs.next()) {

				// 検索結果が存在する場合はbeanに値をセット

				bean = new LoginUserBean();

				bean.setId(id);
				bean.setPass(pass);
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));

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

}
