package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShoppingDao;
import model.ItemBean;
import model.LoginUserBean;

/**
 * 購入確定に対するリクエストを処理するコントローラ
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ユーザIDとカート情報を基にデータベースを更新(対象の商品在庫をマイナスする、購入履歴を追加)します
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープからユーザIDとカート情報を取得
		HttpSession session = request.getSession();
		String id = ((LoginUserBean)session.getAttribute("user_db")).getId();
		List<ItemBean> cart = (List<ItemBean>) session.getAttribute("cart");

		ShoppingDao dao = new ShoppingDao();

		try {

			dao.updateItem(cart);
			dao.updateHistory(cart, id);

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

		// カート情報を破棄
		session.removeAttribute("cart");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		rd.forward(request, response);

	}

}
