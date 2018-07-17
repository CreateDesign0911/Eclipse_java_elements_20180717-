package main;

import java.util.Random;
//これはScanner input = new Scanner(System.in);を記載した後で自動的に入力された。
import java.util.Scanner;

/** (/**)と入力してエンターを押すと、コメント欄が自動入力される。このタイプのコメントはターゲットを決めて注釈するときに用いるのが普通。
  *Ctrl+Shift+Cで選択範囲を一括コメント化できる
 *手始めにじゃんけんゲームだ。
 * @author 蝶野
 */
public class Janken {
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
		//追加したコマンド。
		System.out.println("9:終える");
		System.out.println("じゃんけんポン");

		//じゃんけんの勝負回数をカウントするコマンド。繰り返す前に記述する。+勝ち負け引き分けカウントのために変数追加。
		int count = 0;
		int win = 0;
		int lose = 0;


		//do~whileで繰り返し命令になる
		do {
			//ユーザーの手の入力を判断。
			//int 変数 →変数を数値で覚えておこう ということ。
			//input.nextInt();の（）内に数値を記入すると、どの進数で処理するかを指定するかの命令になる。16と記入すると、16進法で。無記入だと10進法でデフォルト。
			//  何も数字を記入しないことが大半。
			//input.nextInt();について後で聴こう。
			int user = input.nextInt();
			//ユーザーの入力が9の時には中断するコマンド=break を使う。繰り返しでbreakを使うと、繰り返しから脱出できる。
			if (user == 9) {
				break;
			}

			//コンピューターの手の出力を判断。
			//cp側の変数。ユーザー、pcという具合にSの数に応じて変数が必要か？
			//nextInt(3)はグー、チョキ、パーのいずれかが必要なので、3通りのどれかが出る。0.1.2の3通り。記入の数-1の数まで出る。
			// ここで数値を指定しないと何十億通りのパターンが出されてしまう。
			int comp = rand.nextInt(3);

			//ユーザーの手を出力。数字を入力して数値として渡す。
			show("あなた", user);

			//コンピュータの手を出力。
			show("コンピュータ", comp);

			//勝負
			//特定の数字で勝敗を判定。その際、cp-user+3することで、結果が負の数になっても打ち消せる。その後で割る3をして算出された余りの数を判定に利用する。
			// 「余り算」は判定の時に利用できる。余り残には%を。
			//式の結果だけではなく、前程で手を加える。図を書いて〇×△などから法則を発見する。また、結果ではなく、余りから求めるなど、もっと視点を変えるべき。
			judge(user, comp);
			//勝敗判定を表示
			switch (judge(user, comp)) {
			case 1:{
				win = win + 1;
			}break;
			case 2:{
				lose = lose + 1;
			}break;
			case 3:{

			}break;

			}

			//カウントコマンド、勝負の後に+1する。
			count = count + 1;
		} while (true);
		//whileの()にtrueを入れると永久に繰り返すことになる。

		//勝負が終わった後で一度だけ勝負回数をポップ。繰り返し処理の｛｝の範囲外なので、ループ中には表れない。
		System.out.println(count + "回勝負しました。");
		System.out.println(
				win + "勝" +
				lose + "敗" +
				(count - win - lose) + "引き分けでした");

	}

	/**
	 * oneの出した手を文字で表現します；
	 * @param one 主体の文字表現。
	 * @param hand 出した手の番号。
	 */
	//新章：メソッドの記述
	//メインのブロックが終わった後にメソッドの定義を書くのがお決まりの展開。
	//showメソッドというのは、どこかで使われるために準備している。使われるときは、（）の中の数値を用いる。
	//約束  戻り値の型メソッド名( 因数の方 因数の名前[, …])
	//あなたとコンピュータで別個に記述していた判定のテンプレ文章を一括化した。

	public static void show(String one, int hand) {
		switch (hand) {
		case 0: {
			System.out.println(one + "の手はグー");
		}
			break;
		case 1: {
			System.out.println(one + "の手はチョキ");
		}
			break;
		case 2: {
			System.out.println(one + "の手はパー");
		}
			break;

		}
	}

	/**
	 * 勝敗を判定するメソッド。
	 * @param user
	 * @param comp
	 * @return 勝敗を示す値。
	 */
	//void だと返事なし。intにすると返事ありになる。
	public static int judge(int user, int comp) {
		int result = (comp - user + 3) % 3;
		switch (result) {
		case 0: {
			System.out.println("引き分け");
			System.out.println("もう一度");
		}
			break;
		case 1: {
			System.out.println("勝ち");
		}
			break;
		case 2: {
			System.out.println("負け");
		}
			break;
		}
		//intへ変更した後、エラーが出た。返す値が必要なので、以下の文を追加。
		return result;
	}
}