package main;

import java.util.Scanner;

/**
 * 自動販売機の如きプログラムクラス。
 * @author SKT
 *このクラスは自動で入っていた。クラス名とファイル名は合致している必要がある。
 */
public class L20180718_VendingMachine {
	/**
	 * メインメソッド
	 * @param args 起動時引数
	 */
	public static void main(String[] args) {

		//講師は手作業で入力していた。このinputは変数名。
//★配列を利用するならば、最初からこうしておくべきだったな、と思った。別クラスを用意する必要はあるが、こちらのほうが遥かに楽だ。
		Scanner input = new Scanner(System.in);
		Drink[] items = new Drink[] {
			new Drink("Orange",1,120),
			new Drink("Apple",1,120),
			new Drink("Tea",1,130),
			new Drink("Coffee",1,130),
			new Drink("Water",1,100),
	};

		//[]は配列を使うという合図。束ねて扱う、という意思表明。
		// Stringの後に変数名ではなく[]があるのは、配列を扱うという宣言。
		//★配列のポイントは番号を使うということ。後述のselectionのように利用することで、記述を大きく簡略化できる。
	//String[] goods = new String[] {
				//このように"",と書き連ねることで配列を作成する。さいごのロウの,は無くても問題ない。+1行で全て記載しても問題はないが、編集が面倒になる。

//商品名を記述していたが、削除した。

	//};
		//配列：同じ種類のものを束ねること。
		//,の前の数字は、商品の在庫数。
		//上のgoodsとこのstockは関連性がないらしい。それを関連付けて使用するために、行数を合わせて作る必要がある。
		//このソースコードでは、商品の在庫表示を明らかにするために使う。
	//int[] stock = new int[] {
//				items[0].stock =1;
//				items[1].stock =1;
//				items[2].stock =1;
//				items[3].stock =1;
//				items[4].stock =1;
	//};

	//int[] price = new int[] {
//				items[0].price =120;
//				items[1].price =120;
//				items[2].price =130;
//				items[3].price =130;
//				items[4].price =100;
	//};

		System.out.println("自動販売機販売開始");


		//★カウント用変数は、ループ処理の外側に準備しておくこと。事前準備が必要なタイプの情報とされる。
		int total = 0;

		do {
			//配列で多用される繰り返し処理。dowhileより多用される。
			//    初期条件   繰り返し条件      繰り返し時処理       補足： ,ではなく、;というのが特殊。ここでgoodsが自動入力されるのは、22行目でgoodsが配列指定されているため。
			//               (どうなったらやめる？→goodsよりiが大きくなったらループ終了)+.lengthを付与することで、配列の総数を教えてくれる。その時、棚が空だとnullと表示してくれる。
			//               ＜ではなく、<=となると、エラーになる。
			for (int i = 0; i < items.length; i++) {
				//ここのdはDrinkの項目を変数として利用するための宣言。
				Drink d = items[i];
				//マニュアル入力だった。このsysoutによって、[0]orangeという具合に表示される。
				System.out.println(
						"[" + i + "]" + d.name + "在庫:" + d.stock + "個:" + d.price + "円");
			}
			System.out.println("[9]帰る");

			//上記と同じことをdo whileで済ませる方法。
			//			{
			//				int i = 0;
			//				do {
			//					System.out.println(
			//							"[" + i + "]" + goods[i] );
			//
			//					i++;
			//				} while ( i < goods.length );
			//ユーザーの選択番号を格納する変数。
			int selection = input.nextInt();
			if (selection == 9) {
				break;
			}
			//ここからは販売の種類を。
			//selection(選択した数)に該当するstock(商品)の在庫が1未満なら売り切れと表示する。
			if (items[selection].stock  < 1) {
				System.out.println("売り切れです。");
				//繰り返し構文を継続して繰り返し処理内の冒頭へ戻る。中段処理であるbreakの対極。
				continue;
			}
			//売れた商品を--処理。
			items[selection].stock--;
			//いきなり total+= とするとエラーになる。そのため、まずはtotal= 0;と記述して変数を準備しておき、その後に+＝を記述する・・・が
			//int total = 0;    売上累積処理の直前…というよりブロック構造の関係上、この位置だとうまく動作しない。この位置だと、繰り返し処理のたびにtotal=0処理されるので。
			//  int total = 0; は繰り返し構文の直前に記述するのが望ましい。
			total += items[selection].price;
			System.out.println(items[selection].name + "をお買い上げありがとうございます。");

			//在庫0になったらプログラム終了させる動作を記述。
			//★＜重要＞物を計量するコマンドを使う。繰り返し構文内でカウントするためのテンプレ記述がある。
			    //★まずは、数字を覚えておくための変数を用意しておく。remains += のための布石(逆算せよ)。
				//★こちらはtotalと同じ位置に置くと、在庫が尽きても終了処理が実行されなくなる。remainsはこの{}内でしか使用しないので、totalと同じ位置に設置しない。total処理は外側でも使うことを考えて、ループ処理外で。
			int remains = 0;
			//配列と相性の良い繰り返し処理。
			for (int i = 0; i < items.length; i++) {
				//remains + を省略すると、さいごのWaterが0になったときに、全てが売り切れだと誤表示してしまう。0+の項目があれば全ての項目を累積処理するが、 stock[i]のみだと各項目が保存されずさいごの項目の数のみが記録されるため。
				//remains = remains + stock[i];   ←下の正式な記述。
				remains += items[i].stock;
			}
			if( remains == 0 ) {
				System.out.println("すべて売り切れです。");
				System.out.println("ご利用ありがとうございました。");
				break;
			}
		} while (true);
		System.out.println("売上は" + total + "円です。");

	}

}
