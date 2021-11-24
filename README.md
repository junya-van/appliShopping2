# appliShopping2
Schooというネット教材の「Java中級ショッピング風サイト」(appliShopping)を元に作った。
教材ではpageディレクティブやスクリプトレット、スクリプト式を使っていたが今回はJSTLやEL式を使った。
また、カート機能がなかったので追加した。
購入履歴にも日付をセットするようにした。


# 苦労した点
##カート機能
カートに商品を追加して再度同じ商品を追加する場合、前回追加した数に加算するようにしたかったのですが、以下のような仕様に悩まされました。

カートに商品を追加して再度同じ商品をカートに追加した際、カートに入ってる商品の数が後から追加した数に更新されている。

カートに商品Aを2個追加→商品Bを追加→商品Aを一個追加するとカートは「A2個B1個A1個」といった状態で格納されている

そこでフローチャートを書いたりして試行錯誤して以下のようにコードを記述することで無事実装に成功しました。

(ソースコード一部抜粋)

 ```
 
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
 
 
  ```
