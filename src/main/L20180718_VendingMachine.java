package main;

import java.util.Scanner;

/**
 * 自動販売機の如きプログラムクラス。
 * @author SKT
 *このクラスは自動で入っていた。クラス名とファイル名は合致している必要がある。
 */
public class VenderMachine {
	/**
	 * メインメソッド
	 * @param args 起動時引数
	 */
	public static void main(String[] args) {

		//講師は手作業で入力していた。このinputは変数名。
		Scanner input = new Scanner(System.in);

		//[]は配列を使うという合図。束ねて扱う、という意思表明。
		// Stringの後に変数名ではなく[]があるのは、配列を扱うという宣言。
		String[] goods =new String[] {
				//このように"",と書き連ねることで配列を作成する。さいごのロウの,は無くても問題ない。+1行で全て記載しても問題はないが、編集が面倒になる。
				"orange",
				"apple",
				"tea",
				"coffee",
				"water",
	};
	//配列：同じ種類のものを束ねること。
	//,の前の数字は、商品の在庫数。
	//上のgoodsとこのstockは関連性がないらしい。それを関連付けて使用するために、行数を合わせて作る必要がある。
	//このソースコードでは、商品の在庫表示を明らかにするために使う。
	int[] stock = new int[] {
			2,
			1,
			3,
			4,
			1,
	};
		System.out.println("自動販売機販売開始");
		do {
			//配列で多用される繰り返し処理。dowhileより多用される。
			//    初期条件   繰り返し条件      繰り返し時処理       補足： ,ではなく、;というのが特殊。ここでgoodsが自動入力されるのは、22行目でgoodsが配列指定されているため。
			//               (どうなったらやめる？→goodsよりiが大きくなったらループ終了)+.lengthを付与することで、配列の総数を教えてくれる。その時、棚が空だとnullと表示してくれる。
			//               ＜ではなく、<=となると、エラーになる。
			for (int i = 0; i < goods.length; i++) {
				//マニュアル入力だった。このsysoutによって、[0]orangeという具合に表示される。
				System.out.println(
						"[" + i + "]" + goods[i]  + "在庫:" + stock[i] + "個");
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
			if ( selection == 9) {
				break;
			}
			//ここからは販売の種類を。
			//selection(選択した数)に該当するstock(商品)の在庫が1未満なら売り切れと表示する。
			if (stock[selection] < 1) {
				System.out.println("売り切れです。");
				//繰り返し構文を継続して繰り返し処理内の冒頭へ戻る。中段処理であるbreakの対極。
				continue;
			}
			//売れた商品を--処理。
			stock[selection]--;
			System.out.println( stock[selection] + "をお買い上げありがとうございます。");
		} while (true);

}

}
