package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HistoryBean;
import model.LoginLogic;
import model.LoginUserBean;
import model.ShoppingLogic;

/**
 * ログイン処理に対するリクエストを処理するコントローラ
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッドでリクエストされた場合の処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// doPostで処理
		doPost(request, response);

	}

	/**
	 * POSTメソッドでリクエストされた場合の処理.<br>
	 * 押されたボタンやリンクによって処理が分岐します
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("submit");

		HttpSession session = request.getSession();
		RequestDispatcher rd;

		if("ログイン".equals(btn)) {

			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			LoginLogic loginLogic = new LoginLogic();
			LoginUserBean bean = loginLogic.getUserData(id, pass);

			if(bean != null) {

				// モデルの情報が存在する場合はセッションスコープにユーザ情報とログイン状態を保存
				session.setAttribute("user_db", bean);
				session.setAttribute("login_db", "login");

				rd = request.getRequestDispatcher("/ShoppingServlet");

			} else {

				rd = request.getRequestDispatcher("/WEB-INF/jsp/loginNg.jsp");

			}

			rd.forward(request, response);

		} else if("ログアウト".equals(btn)) {

			// セッションスコープを破棄
			session.invalidate();

			response.sendRedirect("/appliShopping2/");

		} else if("history".equals(btn)) {

			// セッションスコープからユーザIDを取得
			String id = ((LoginUserBean)session.getAttribute("user_db")).getId();

			ShoppingLogic shoppingLogic = new ShoppingLogic();
			ArrayList<HistoryBean> beanList = shoppingLogic.getHistory(id);

			request.setAttribute("history", beanList);

			rd = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");
			rd.forward(request, response);

		}



	}

}
