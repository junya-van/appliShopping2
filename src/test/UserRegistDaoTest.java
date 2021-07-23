package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import dao.UserRegistDao;
import model.LoginUserBean;

public class UserRegistDaoTest {

	@Test
	public void ユーザ情報の登録に成功するテスト() {

		UserRegistDao dao = new UserRegistDao();

		try {

			LoginUserBean bean = new LoginUserBean();
			bean.setId("00003");
			bean.setPass("test3pass");
			bean.setName("テスト3 太郎");
			bean.setAge(50);

			int result = dao.insert(bean);

			assertSame(1, result);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

	}

	@Test
	public void ユーザ情報登録に失敗するテスト() {

		UserRegistDao dao = new UserRegistDao();

		try {

			LoginUserBean bean = new LoginUserBean();
			bean.setId("00002");	// ユーザIDの重複により登録できない
			bean.setPass("test1pass2");
			bean.setName("テスト1 花子");
			bean.setAge(25);

			int result = dao.insert(bean);

			assertSame(0, result);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

	}

}
