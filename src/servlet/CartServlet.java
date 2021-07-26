package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemBean;

/**
 * カート処理に対するリクエストを処理するコントローラ
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッドでリクエストされた場合の処理.<br>
	 * カート画面に移動します
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		rd.forward(request, response);
	}

	/**
	 * POSTメソッドでリクエストされた場合の処理(詳細画面から商品をカートに追加する時の処理).<br>
	 * 初回時(カートに何も入っていない状態)と、カートに商品を追加した際に、既に同じ商品が入っていた場合と別の商品が入っていた場合に処理が分岐します
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ItemBean> cart;
		RequestDispatcher rd;

		HttpSession session = request.getSession();
		ItemBean bean = (ItemBean) session.getAttribute("item");

		// リクエストパラメータから購入数を取得
		request.setCharacterEncoding("UTF-8");
		int count = Integer.parseInt(request.getParameter(bean.getItemId() + "list"));

		if(session.getAttribute("cart") == null) {

			// 初回時の処理

			bean.setCount(count);

			// カートに追加
			cart = new ArrayList<>();
			cart.add(bean);
			session.setAttribute("cart", cart);

			rd = request.getRequestDispatcher("/WEB-INF/jsp/cartResult.jsp");

		} else {

			cart = (List<ItemBean>) session.getAttribute("cart");

			if(cart.indexOf(bean) != -1) {

				// カートには既に同じ商品が入っていた場合

				int x = cart.indexOf(bean);

				if((count + cart.get(x).getCount()) > bean.getQuantity()) {

					// カートに追加した際の購入数と既にカートに入っていた商品との購入数の合計が在庫を超えていた場合
					request.setAttribute("errorMsg", "追加されませんでした。在庫数を超える数は指定できません");

					rd = request.getRequestDispatcher("/WEB-INF/jsp/details.jsp");

				} else {

					// カート情報更新
					cart.get(x).setCount(count);
					session.setAttribute("cart", cart);

					rd = request.getRequestDispatcher("/WEB-INF/jsp/cartResult.jsp");

				}

			} else {

				// カートには別の商品が入っていた場合

				bean.setCount(count);

				// カートに追加
				cart.add(bean);
				session.setAttribute("cart", cart);

				rd = request.getRequestDispatcher("/WEB-INF/jsp/cartResult.jsp");

			}

		}

		rd.forward(request, response);

	}

}
