package main;

import java.util.Random;
//これはScanner input = new Scanner(System.in);を記載した後で自動的に入力された。
import java.util.Scanner;

/** (/**)と入力してエンターを押すと、コメント欄が自動入力される。このタイプのコメントはターゲットを決めて注釈するときに用いるのが普通。
  *Ctrl+Shift+Cで選択範囲を一括コメント化できる
 *手始めにじゃんけんゲームだ。
 * @author 蝶野
 */
public class Janken2{
	/**
	 * ここからコーディング開始
	 * @param args 20行目は起動時引数というものだが、基本的に使わない。
	 * 仕事の単位をメソッドという。
	 * パラメータはjavaの授業では使わない。
	 * Ctrl+スペースを多用せよ。英単語をじかに入力するとスペルミスの発生率が高いので。
	 */
	public static void main(String[] args) {

		//まずは入力を取り込む装置の準備。下のinputは変数名。ScannerはintやLongのようなものだと考えよう。
		//javaでは、まず情報の種類を書き、その後に情報名を記入というのが作法。
		//  (System.in)のinはSystem.outのoutの反対。今回はコンソールから情報を入手したいので、in.Scannerは情報読み取り装置。
		//()の中身がどこから引き出すのか指定していることを表す。
		//型名 変数名 ＝ 値(新しく作る) 型名(必要な情報)；
		//          (代入)
		//int と newの色が同じなのは「予約語」という同じグループなので。
		Scanner input = new Scanner(System.in);
		//乱数発生装置の準備。これは発生させるための装置の準備をしているだけ。
		//ユーザーの手の入力の前にメッセージを表示して、ルールの説明をしておこう。
		Random rand = new Random();
		System.out.println("じゃんけんで勝負！");
		System.out.println("0:グー");
		System.out.println("1:チョキ");
		System.out.println("2:パー");
		System.out.println("じゃんけんポン");

		//ユーザーの手の入力を判断。
		//int 変数 →変数を数値で覚えておこう ということ。
		//input.nextInt();の（）内に数値を記入すると、どの進数で処理するかを指定するかの命令になる。16と記入すると、16進法で。無記入だと10進法でデフォルト。
		//  何も数字を記入しないことが大半。
		//input.nextInt();について後で聴こう。
		int user = input.nextInt();
		//コンピューターの手の出力を判断。
		//cp側の変数。ユーザー、pcという具合にSの数に応じて変数が必要か？
		//nextInt(3)はグー、チョキ、パーのいずれかが必要なので、3通りのどれかが出る。0.1.2の3通り。記入の数-1の数まで出る。
		// ここで数値を指定しないと何十億通りのパターンが出されてしまう。
		int comp = rand.nextInt(3);

		//ユーザーの手を出力。数字を入力して数値として渡す。
		//文字+数字 の場合、数字は文字扱いになる。

		switch( user ) {
		case 0:{
			System.out.println("あなたの手はグー");
		}	break;
		case 1:{
		System.out.println("あなたの手はチョキ");
		} 	break;
		case 2:{
			System.out.println("あなたの手はパー");
		} 	break;
		}
		//コンピュータの手を出力。
		//()の中身、検査対象がどのような状態(数値)ならば、どのような結果を返すか、ということを表している。
		switch( comp ) {
		case 0:{
			System.out.println("コンピュータの手はグー");
		}	break;
		case 1:{
		System.out.println("コンピュータの手はチョキ");
		} 	break;
		case 2:{
			System.out.println("コンピュータの手はパー");
		} 	break;
		}

		//勝負
		//特定の数字で勝敗を判定。その際、cp-user+3することで、結果が負の数になっても打ち消せる。その後で割る3をして算出された余りの数を判定に利用する。
		// 「余り算」は判定の時に利用できる。余り残には%を。
		//式の結果だけではなく、前程で手を加える。図を書いて〇×△などから法則を発見する。また、結果ではなく、余りから求めるなど、もっと視点を変えるべき。
		int result = (comp - user + 3) % 3;

		//勝敗判定を表示
		//｛｝の囲まれた部分のことを「ブロック構造」、あるいは「スコープ」という(スナイパースコープのように、あるいはバームクーヘンのように)。何かしらの判断の一塊。
		// ブロックは一つの文(コードのこと)のように見えるが、一つのブロックに複数の文が含まれることもある＝一つの文で複数の命令を起動することもできる と覚えておくこと。
		//マトリョーシカの如く、ブロックの中にif文を入れて、更にブロックを・・・というのもできる。
		//		if (result == 0) {
		//			System.out.println("引き分け");
		//		} else if (result == 2) {
		//			System.out.println("負け");
		//		} else
		//			System.out.println("勝ち");

		//		if (result == 0) {
		//			System.out.println("引き分け");  を表す
		//resultの値がどの数値のときにどの動作を行うかを、if文よりもスマートに表せる。
		//これによってif文よりも可読性の高いコードになる。
		//テキストではブロック( {} )を記載しないテキストが大半。ここで書いているのは習得のため。
		//｛｝は忘れやすい。僕も波括弧のせいでエラーになっていた。要注意。
		//if文より、switch文のほうが現場では好まれる。これはピンボイントで条件分岐する。範囲選択は不可。
		switch (result) {
		case 0: {
			System.out.println("引き分け");
			System.out.println("もう一度");
		}	break;
		case 1:{
			System.out.println("勝ち");
		}   break;
		case 2:{
			System.out.println("負け");
		}   break;
		}
	}

}
