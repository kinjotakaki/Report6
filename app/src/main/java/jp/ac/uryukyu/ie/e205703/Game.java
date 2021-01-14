package jp.ac.uryukyu.ie.e205703;

import java.util.*;
/** @author
 * Gameクラス
 ランダムで配布された相手のカードを選び数字と倍率の異なるアルファベットで得点を争う
 */
public class Game {
    /**
     * static List <String> bot = new ArrayList<>(); //botのカード一覧
    static List <String> player = new ArrayList<>();//playerのカード一覧（持ち札）
    static boolean Continue = true;　//ゲーム続行可能か
    static boolean Continue_player = true;　//playerのターンかbotのターンを示す
    static int sum_bot = 0;  //
    static int sum_player = 0;//
    */
    static List <String> bot = new ArrayList<>();
    static List <String> player = new ArrayList<>();
    static boolean Continue = true;
    static boolean Continue_player = true;
    static int sum_bot = 0;
    static int sum_player = 0;

    /**
     * メソッドを繋げて本ゲームを動かすメソッド
     * 標準入力を受け取る
     * １ターンごとの処理を書いているメソッドを条件を満たす限り続ける
     */
    public static void play_game(){
        Distribute_card();
    while(Continue){
        if (Continue_player){
            show_bot();
            show_player();
            System.out.print("選択するカード番号を１〜"+bot.size()+"の中から選択してください");
            Scanner sc = new Scanner(System.in);
            String s = sc.next();
            int i = Integer.parseInt(s);
            System.out.println("あなたが選んだカードは"+bot.get(i-1));
            Sum(cal(bot.get(i-1)));
            bot.remove(bot.get(i-1));
            Continue_player = false;
        }else{
            int i = (int)(Math.random()*player.size());
            System.out.println("相手の選んだカードは"+player.get(i));
            Sum(cal(player.get(i)));
            player.remove(i);
            Continue_player = true;
        }
            System.out.println();  
    
    }     
    Judgment();
}

    /**勝者判定するメソッド
     */
    public static void Judgment(){
        System.out.println("あなたのポイント数:"+sum_player);
        System.out.println("相手のポイント数:"+sum_bot);
        if (sum_player > sum_bot){
            System.out.println(sum_player+"ポイントであなたの勝ちです");
        }else if(sum_player < sum_bot){
            System.out.println(sum_bot+"ポイントであなたの負けです");
        }else if (sum_player == sum_bot){
            System.out.println("引き分け");
        }
    }

    /**現時点でのポイントを表示するメソッド
     *  @param sum プレイヤーの加算ポイント
     * calメソッドからの引数を受けとる
     * 獲得ポイントと合計ポイントを表示
     * */ 
    public static void Sum(int sum){
        if (Continue_player){
            sum_player +=sum;
            System.out.println("あなたの獲得ポイント："+sum);
            System.out.println("あなたの合計ポイント："+sum_player);
        }else{
            sum_bot +=sum;
            System.out.println("相手の獲得ポイント："+sum);
            System.out.println("相手の合計ポイント"+sum_bot);
            
        }
    }

    /**
     * 得点を計算するメソッド
     * アルファベットと数字（倍率）をそれぞれ取り出して得点化する
     * @param index 手札から受け取った値
     * @return ポイントを計算した結果を返す
     */
    public static int cal(String index){
        int goukei = 0;
        if (index.equals("Joker")){
            return goukei+=30;
        }else if (player.contains(index) || bot.contains(index)){
        String i = index.replaceAll("[^A-Za-z]","");
        String h = index.replaceAll("[^\\d]","");
        int dis_num = Integer.parseInt(h);
        if (i.equals("S")){
            goukei += dis_num * 1;
        }
        else if(i.equals("C")){
            goukei += dis_num*2;
        }
        else if(i.equals("H")){
            goukei += dis_num*3;
        }
        else if (i.equals("D")){
            goukei += dis_num*5;
        }
    }
    return goukei;
    }

    /**
     * botのカードを見せるメソッド
     * 相手に見られてはいけないので全て[?]で出力される
     */
    public static void show_bot(){
        if (bot.size() ==0){
            Continue = false;
        }else{
        System.out.println("相手のカード");
        for (String l:bot){
            System.out.print("[?]");
        }
        System.out.println();
    }
    }

    /**
     * player自身の手札をみることができるメソッド
     */
    public static void show_player(){
        if (player.size() ==0){
            Continue = false;
        }else{
            System.out.println("自分のカード");
            for (String li:player){
                System.out.print("[");
                System.out.print(li);
                System.out.print("]");
            }
    }   System.out.println();
    }

    /**
     * playerとbotにカードを配るメソッド
     * 予め用意されたカードをランダムに両者に分配する
     */
    public static void Distribute_card(){
        List <String> card = new ArrayList<>();
        for (String item : new String[]{"S1","S2","S3","S4","H1","H2","H3","H4","D1","D2","D3","D4","C1","C2","C3","C4","Joker",})
            card.add(item);
            System.out.println("今回使用するカード一覧"+card);
        for (int i =0;card.size() >1; i++){
            Random n = new Random();
            int random_num = n.nextInt(card.size());
            bot.add(card.get(random_num));  //player1にカードを配布
            card.remove(random_num); //取ったカードは除去
            Random m = new Random();
            int random_num1 = m.nextInt(card.size());
            player.add(card.get(random_num1));
            card.remove(random_num1);
            }
        if (card.size() == 1){
            bot.add(card.get(0));
            card.remove(0);
        }
        }   
}