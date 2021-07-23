package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserRegistDao;
import model.LoginUserBean;

/**
 * ユーザ登録処理に対するリクエストに応えるコントローラ
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッドでリクエストされた場合の処理.<br>
	 * 登録フォーム画面へフォワードします
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/insertForm.jsp");
		rd.forward(request, response);

	}

	/**
	 * POSTメソッドでリクエストされた場合の処理
	 * ユーザ登録処理をします。結果によってリクエストスコープに格納されるメッセージが変わります
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		int age = Integer.parseInt(request.getParameter("age"));

		LoginUserBean bean = new LoginUserBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPass(pass);
		bean.setAge(age);

		UserRegistDao dao = new UserRegistDao();

		String msg = "";

		try {

			int result = dao.insert(bean);

			if(result != 0) {

				msg =  "登録に成功しました" ;

			} else {

				msg = "登録に失敗しました。このユーザIDは既に使用されています";

			}

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();

		}

		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/insertReceipt.jsp");
		rd.forward(request, response);

	}

}
