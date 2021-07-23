package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
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
 * カート情報削除に対するリクエストを処理するコントローラ
 */
@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * カートから商品を削除します.<br>
	 * 具体的には、カート画面(cart.jsp)より、「商品ID = 削除」という形でリクエストパラメータが送られてきます.<br>
	 * リクエストパラメータから、パラメータ名(商品ID)を取得してitemIdにセットします.<br>
	 * そしてそのitemIdを基に商品を削除します
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> names = request.getParameterNames();

		String name = "";		// 現在のパラメータ名
		String itemId = "";		// 商品ID

		if(names.hasMoreElements()) {

			name = names.nextElement();

			if("削除".equals(request.getParameter(name))) {

				itemId = name;

			}

		}

		HttpSession session = request.getSession();
		List<ItemBean> cart = (List<ItemBean>) session.getAttribute("cart");

		// 商品を削除
		Iterator<ItemBean> it = cart.iterator();
		while(it.hasNext()) {
			ItemBean bean = it.next();
			if(itemId.equals(bean.getItemId())) {
				it.remove();
			}
		}

		// カート情報を上書き保存
		session.setAttribute("cart", cart);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		rd.forward(request, response);

	}

}
