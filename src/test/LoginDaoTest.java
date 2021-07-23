package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.LoginDao;

public class LoginDaoTest {

	@Test
	public void ユーザ検索に成功するテスト() {

		LoginDao dao = new LoginDao();

		try {

			ResultSet rs = dao.selectUser("00001", "test1pass");
			assertNotNull(rs);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

	}

	@Test
	public void ユーザ検索に失敗するテスト() {

		LoginDao dao = new LoginDao();

		try {

			ResultSet rs = dao.selectUser("00000", "00000");
			assertNotNull(rs);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}
	}

}
