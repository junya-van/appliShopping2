package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 購入履歴クラス
 */
public class HistoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemId;			// 商品ID
	private String itemName;		// 商品名
	private int quantity;			// 購入数
	private LocalDate date;			// 購入日

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します
	 */
	public HistoryBean() {

		itemId = "";
		itemName = "";
		quantity = 0;
		date = LocalDate.of(0001, 01, 01);

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
	 * 購入数を返却します
	 * @return 購入数
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 購入数を設定します
	 * @param quantity 購入数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 購入日を返却します
	 * @return 購入日
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * 購入日を設定します
	 * @param date 購入日
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
