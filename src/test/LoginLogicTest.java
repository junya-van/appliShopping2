package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.LoginLogic;
import model.LoginUserBean;

public class LoginLogicTest {

	@Test
	public void ユーザ検索に成功するテスト() {

		LoginLogic loginLogic = new LoginLogic();
		LoginUserBean bean = loginLogic.getUserData("00001", "test1pass");
		assertNotNull(bean);

	}

	@Test
	public void ユーザ検索に失敗するテスト() {

		LoginLogic loginLogic = new LoginLogic();
		LoginUserBean bean = loginLogic.getUserData("00000", "00000");
		assertNull(bean);

	}

	@Test
	public void ユーザ検索に失敗するテスト2() {

		LoginLogic loginLogic = new LoginLogic();
		LoginUserBean bean = loginLogic.getUserData(null , null);
		assertNull(bean);

	}

}
