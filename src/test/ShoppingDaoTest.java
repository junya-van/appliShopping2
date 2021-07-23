package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.ShoppingDao;

public class ShoppingDaoTest {

	@Test
	public void 商品情報ResultSetを取得できているかテスト() {

		ShoppingDao dao = new ShoppingDao();

		try {

			ResultSet rs = dao.selectItem();
			assertNotNull(rs);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

	}

	@Test
	public void 購入履歴ResultSetを取得できているかテスト() {

		ShoppingDao dao = new ShoppingDao();

		try {

			ResultSet rs = dao.selectHistory("00001");
			assertNotNull(rs);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}
	}

	@Test
	public void 商品IDを基に商品情報ResultSetを取得できているかテスト() {

		ShoppingDao dao = new ShoppingDao();

		try {

			ResultSet rs = dao.selectItem("s0001");
			assertNotNull(rs);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

	}

}
