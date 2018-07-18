
//在庫数と価格が別個に記述されているのは問題らしい。
//それを改善するために、一度に複数情報を記述したバージョン。
package main;
/**
 * 自動販売機で販売される商品クラス。
 * @author 4gc215
 *
 */
public class Drink {
//まずはクラス定義を行う。
	/**
	 * 商品名
	 */
	String name;
	/**
	 * gennzainozaiko
	 *
	 */
	int stock;
	/**
	 * 商品単価
	 */
	int price;
	/**
	 * コンストラクタ という
	 * @param name 商品名
	 * @param stock 初期在庫数
	 * @param price 単価
	 */
	Drink( String name, int stock, int price ){
		this.name = name;
		this.stock = stock;
		this.price = price;

	}
}
