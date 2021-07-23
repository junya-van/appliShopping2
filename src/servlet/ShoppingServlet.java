package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemBean;
import model.ShoppingLogic;

/**
 * 商品一覧画面表示や詳細画面表示に対するリクエストを処理するコントローラ
 */
@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッドで呼び出された場合の処理.<br>
	 * 商品の詳細画面へフォワードします.<br>
	 * 具体的には、商品一覧画面(itemList.jsp)の詳細ボタンより、「商品ID = 詳細」という形でリクエストパラメータが送られてきます.<br>
	 * リクエストパラメータから、パラメータ名(商品ID)を取得してそれをitemIdにセットします.<br>
	 * そしてそのitemIdを基に商品情報を保存し、詳細画面へフォワードします
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> names = request.getParameterNames();

		String name = "";			// 現在のパラメータ名
		String itemId = "";			// 商品ID

		// 詳細ボタンがクリックされた場所を特定
		while(names.hasMoreElements()) {

			name = names.nextElement();

			if("詳細".equals(request.getParameter(name))) {

				itemId  = name;

			}

		}

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ItemBean bean = shoppingLogic.getItem(itemId);

		// 商品情報をセッションスコープに格納
		HttpSession session = request.getSession();
		session.setAttribute("item", bean);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/details.jsp");
		rd.forward(request, response);

	}

	/**
	 * POSTメソッドで呼び出された場合の処理.<br>
	 * 商品一覧を取得して商品一覧画面へフォワードします
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ShoppingLogic shoppingLogic = new ShoppingLogic();
		ArrayList<ItemBean> beanList = shoppingLogic.getItem();

		request.setAttribute("itemList", beanList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		rd.forward(request, response);

	}

}
