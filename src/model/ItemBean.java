package model;

import java.io.Serializable;

/**
 * 商品情報クラス
 */
public class ItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemId;		// 商品ID
	private String itemName;	// 商品名
	private int price;			// 商品単価
	private String text;		// 商品の説明
	private int quantity;		// 商品数(在庫数)

	private int count;			// 購入数(カートに入ってる数)

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します
	 */
	public ItemBean() {
		itemId = "";
		itemName = "";
		price = 0;
		quantity = 0;
		count = 0;
	}

	/**
	 * equalsメソッドのオーバーライド.<br>
	 * 商品IDが等しければ等価とみなします。
	 * @return 真偽値
	 */
	@Override
	public boolean equals(Object o) {

		if(o == this) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(!(o instanceof ItemBean)) {
			return false;
		}

		ItemBean r = (ItemBean) o;

		if(!this.itemId.equals(r.itemId)) {
			return false;
		}

		return true;

	}

	/**
	 * 商品IDを返却します
	 * @return 商品ID
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * 商品IDを設定します
	 * @param itemId 商品ID
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 商品名を返却します
	 * @return 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 商品名を設定します
	 * @param itemName 商品名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 商品価格(単価)を返却します
	 * @return 商品価格
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 商品価格(単価)を設定します
	 * @param price 商品価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 商品の説明を返却します
	 * @return 商品の説明
	 */
	public String getText() {
		return text;
	}

	/**
	 * 商品の説明を設定します
	 * @param text 商品の説明
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 商品数を返却します
	 * @return 商品数
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 商品数を設定します
	 * @param quantity 商品数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 購入数(カートに入ってる数)を返却します
	 * @return 購入数(カートに入ってる数)
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 購入数(カートに入ってる数)を設定します
	 * 加算代入しています
	 * @param count 購入数(カートに入ってる数)
	 */
	public void setCount(int count) {
		this.count += count;
	}
}