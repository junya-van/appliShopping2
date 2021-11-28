URL: http://shoppingmodoki.com/

# 概要
Schooというネット教材の「Java中級ショッピング風サイト」(https://schoo.jp/class/2933![image](https://user-images.githubusercontent.com/72599378/143299216-63c716e1-f8f6-4c5b-9594-26c183cb1562.png)
)を元に作りました。


![Webp net-gifmaker7](https://user-images.githubusercontent.com/72599378/143733150-e0f0c04d-ecfd-4d85-a34e-777a08d46f17.gif)

# 使用技術
### フロントエンド
HTML

### バックエンド
Java(サーブレット)

JSP

MySQL 8.0.23

### テスト
JUnit 5

### インフラ
AWS(Elastic Beanstalk,　EC2, RDS, Route53)

# 機能
ログイン、ログアウト機能

サインアップ機能

商品一覧表示機能

商品詳細表示機能

カート機能(追加、削除)

商品購入確認画面表示機能

購入処理機能

購入履歴表示機能



# 工夫した点
教材のアプリではカート機能がないので追加しました。カートに商品を追加して再度同じ商品をカートに追加すると、前回追加した数に加算するようにしました。
<br>また、カートに追加する時に在庫数を超えるのであればエラーメッセージを表示して追加できないようにしました。

![Webp net-gifmaker8](https://user-images.githubusercontent.com/72599378/143733495-8d4d2255-2270-4b79-92ce-d5d265c0f3fa.gif)

# 苦労した点
### カート機能
カートに商品を追加して再度同じ商品を追加する場合、前回追加した数に加算するようにしたかったのですが、以下のような仕様に悩まされました。

・カートに商品を追加して再度同じ商品をカートに追加した際、カートに入ってる商品の数が後から追加した数に更新されている。

・カートに商品Aを2個追加→商品Bを1個追加→商品Aを個追加するとカートは「A2個B1個A1個」といった状態で格納されている

そこで、フローチャートを書いたり色々試行錯誤して以下のようにコードを記述することで無事実装に成功しました。

(ソースコード一部抜粋)

 ```
 
 /**
	 * POSTメソッドでリクエストされた場合の処理(詳細画面から商品をカートに追加する時の処理).<br>
	 * 初回時(カートに何も入っていない状態)と、カートに商品を追加した際に、同じ商品が入っていた場合とそうでなかった場合に処理が分岐します
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

				// カートに同じ商品が入っていた場合

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
