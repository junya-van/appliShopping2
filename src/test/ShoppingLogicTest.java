package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.HistoryBean;
import model.ItemBean;
import model.ShoppingLogic;

public class ShoppingLogicTest {

	@Test
	public void 商品情報一覧を取得するテスト() {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ArrayList<ItemBean> beanList = shoppingLogic.getItem();
		assertNotNull(beanList);

	}

	@Test
	public void 購入履歴一覧を取得するテスト() {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ArrayList<HistoryBean> beanList = shoppingLogic.getHistory("00001");
		assertNotNull(beanList);
	}

	@Test
	public void 商品IDを基に商品情報を取得するテスト() {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ItemBean bean = shoppingLogic.getItem("s0001");
		assertNotNull(bean);
		System.out.println(bean.getItemName());

	}

	@Test
	public void 商品IDを基に商品情報を取得するテスト2() {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ItemBean bean = shoppingLogic.getItem("s0002");
		assertNotNull(bean);
		System.out.println(bean.getItemName());

	}

	@Test
	public void 商品IDを基に商品情報取得に失敗するテスト() {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ItemBean bean = shoppingLogic.getItem(null);
		assertNull(bean);

	}

	@Test
	public void 商品IDを基に商品情報取得に失敗するテスト2() {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ItemBean bean = shoppingLogic.getItem("0000");
		assertNull(bean);

	}



}
