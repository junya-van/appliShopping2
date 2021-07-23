package servlet;

import java.io.IOException;
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
 * 購入(レジ)処理のリクエストに対する要求を処理するコントローラ
 */
@WebServlet("/BuyItemServlet")
public class BuyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * カートに入ってる商品の総額を計算して保存、購入確認画面へフォワードします
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int total = 0;

		HttpSession session = request.getSession();
		session = request.getSession();
		List<ItemBean> cart = (List<ItemBean>) session.getAttribute("cart");

		for(ItemBean bean : cart) {
			total += (bean.getPrice() * bean.getCount());
		}

		// リクエストスコープに商品の総額をセット
		request.setAttribute("total", total);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/confirm.jsp");
		rd.forward(request, response);


	}

}
