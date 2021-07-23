package model;

import java.io.Serializable;

/**
 * ユーザ情報クラス(userテーブルのレコードを表しています)
 */
public class LoginUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;			// ID
	private String pass;		// パスワード
	private String name;		// 名前
	private int age;			// 年齢

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します
	 */
	public LoginUserBean() {
		this.id = "";
		this.pass = "";
		this.name = "";
		this.age = 0;
	}

	/**
	 * IDを返却します
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * IDを設定します
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * パスワードを返却します
	 * @return パスワード
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * パスワードを設定します
	 * @param pass パスワード
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 名前を返却します
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定します
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 年齢を返却します
	 * @return 年齢
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 年齢を設定します
	 * @param age 年齢
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
