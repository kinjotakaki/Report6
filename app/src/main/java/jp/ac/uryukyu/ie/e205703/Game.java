package jp.ac.uryukyu.ie.e205703;

import java.util.*;

public class Game {
    static List <String> bot = new ArrayList<>();
    static List <String> player = new ArrayList<>();
    static boolean Continue = true;
    //static boolean Continu_bot = true;
    static boolean Continue_player = true;
    static int sum_bot = 0;
    static int sum_player = 0;
    
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

    public static int cal(String index){
        int goukei = 0;
        if (index.equals("Joker")){
            goukei+=30;
        }else{
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