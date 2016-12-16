package com.example.embeddedblackjack;

import java.util.ArrayList;

import com.example.embeddedblackjack.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InGameActivity extends Activity {
	ArrayAdapter<String> adapter1;
	
	//Piezo
	PiezoThread piezo = new PiezoThread();
	int PiezoData;
	int music = 0;
	int index = 0;
	boolean alive = true;
	int musicOne = 0;
	
	//슈퍼마리오 GameOver = 게임 짐
	int music1[] = {5,18,0,18,18,17,7,5,3,0,3,1,0,0,17,0,0,5,0,3,6,7,6,52,53,52,5,5};
	int duration1[] = {8,8,8,8,8,8,8,8,8,8,8,8,4,8,8,8,8,8,4,4,8,8,8,4,4,4,4,2};
	
	//슈퍼마리오 Level Complete = 게임 이김
	int music2[] = {1,3,5,17,19,21,19,1,50,52,17,66,68,19,2,4,53,18,69,69,69,69,33};
	int duration2[] = {8,8,8,8,8,4,4,8,8,8,8,8,4,4,8,8,8,8,8,4,8,8,8,2};

	//슈퍼마리오 Coin Sound = 아이템 사용
	int music3[] = {7,19,7,19,0,7,19,7,19};
	int duration3[] = {8,8,8,8,4,8,8,8,8};
		
	//슈퍼라미오 1UP = 아이템 얻음
	int music4[] = {19,21,35,33,34,36};
	int duration4[] = {8,8,8,8,8,8};
	
	//말할 수 없는 비밀 OST 배틀2 =  내 턴 or 게임 초기 화면 (파#,도#솔#,레#)
	int music5[] = {5,52,65,5,52,65,5,52,19,5,52,65,5,52,19,65,
	6,65,67,6,65,19,6,65,66,6,65,19,6,65,66,65,
	17,65,22,17,65,68,17,65,67,17,65,19,17,65,66,19,
	65,0,81,0,0,0};
	int duration5[] = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,
	16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,
	16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,
	4,4,4,4,4,4};
	
	//슈퍼마리오 Castle Complete = 블랙잭으로 이김
	int music6[] = {17,5,3,17,5,3,17,65,52,4,65,52,4,65,66,53,5,66,53,5,66,20,20,20,21,0};
	int duration6[] = {8,8,8,8,8,8,2,8,8,8,8,8,8,2,8,8,8,8,8,8,4,8,8,8,2,2};
	
	public native int PiezoControl(int value);	// JNI Interface

	//변수
	int Total_cash = 5000;
	int Bet = 0;
	
	//item에서 쓸려고
	int levelCnt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);
        System.loadLibrary("blackjack");
        
        //TextView
    	final TextView deckNum = (TextView)findViewById(R.id.deckNum);
    	final TextView total_money = (TextView)findViewById(R.id.cashNum);
    	final TextView bet_money = (TextView)findViewById(R.id.betNum);
    	
    	//Button
    	final Button hitBtn = (Button)findViewById(R.id.hit);
    	final Button stayBtn = (Button)findViewById(R.id.Stay);
    	final Button splitBtn = (Button)findViewById(R.id.Split);
    	final Button doubleBtn = (Button)findViewById(R.id.Double);
    	final Button insuranceBtn = (Button)findViewById(R.id.Insurance);
    	final Button evenmoneyBtn = (Button)findViewById(R.id.Even_money);
    	final Button chip_1 = (Button)findViewById(R.id.chip_1);
    	final Button chip_5 = (Button)findViewById(R.id.chip_5);
    	final Button chip_20 = (Button)findViewById(R.id.chip_20);
    	final Button chip_100 = (Button)findViewById(R.id.chip_100);
    	final Button chip_500 = (Button)findViewById(R.id.chip_500);
    	final Button gameStartBtn = (Button)findViewById(R.id.gameStartBtn);
    	
    	//게임 버튼 처음엔 enable, 게임 시작해야 눌리게 함
    	hitBtn.setEnabled(false);
    	stayBtn.setEnabled(false);
    	splitBtn.setEnabled(false);
    	doubleBtn.setEnabled(false);
    	insuranceBtn.setEnabled(false);
    	evenmoneyBtn.setEnabled(false);
    	 
    	//Piezo
    	piezo.setDaemon(true);
		piezo.start();

		PiezoData = 0;
		PiezoControl(PiezoData);
        
    	//설정값 받아오기
        Intent intent = getIntent();
        final int playerCnt = intent.getIntExtra("playerCnt",0);
        final int aiCnt = intent.getIntExtra("aiCnt",0);
        levelCnt = intent.getIntExtra("levelCnt",0); //item에서 쓸려고
       
        
        int total_player = 0;
        total_player = playerCnt + aiCnt;
        
        ListView spot2 = (ListView)findViewById(R.id.player2);
        ListView spot3 = (ListView)findViewById(R.id.player3);
        ListView spot4 = (ListView)findViewById(R.id.player4);
        ListView spot5 = (ListView)findViewById(R.id.player5);
        ListView spot6 = (ListView)findViewById(R.id.player6);
        ListView spot7 = (ListView)findViewById(R.id.dealer);

        if(total_player < 6){spot6.setVisibility(View.GONE);}
        if(total_player < 5){spot5.setVisibility(View.GONE);}
        if(total_player < 4){spot4.setVisibility(View.GONE);}
        if(total_player < 3){spot3.setVisibility(View.GONE);}
        
        if(playerCnt == 1){
        	
        }else if(playerCnt == 2){
        	
        }
        
        if(aiCnt == 1){
        	
        }else if(aiCnt == 2){
        	
        }else if(aiCnt == 3){
        	
        }else if(aiCnt == 4){
        	
        }
        
        if(levelCnt == 1){
        	deckNum.setText("1 DECK");
        }else if(levelCnt == 2){
        	deckNum.setText("2 DECK");
        }else if(levelCnt == 4){
        	deckNum.setText("4 DECK");
        }else if(levelCnt == 6){
        	deckNum.setText("6 DECK");
        }
        
        final Player p1 = new Player();
    	final Player p2 = new Player();
    	final Deck deck = new Deck(levelCnt);
    	final Dealer dealer = new Dealer();
    	
    	//배팅 부분
     	total_money.setText(Integer.toString(p1.total));
     	bet_money.setText(Integer.toString(p1.bet));
     	
     	chip_1.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View arg0) {
        		if(p1.bet + 1 <= p1.total)
        		{   p1.bet = p1.bet + 1;
        			bet_money.setText(Integer.toString(p1.bet));
        		}
        		else
        			Toast.makeText(getApplicationContext(), "보유하신 금액을 초과하였습니다.", Toast.LENGTH_LONG).show();
		     	}
		 });
        
     	chip_5.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View arg0) {
        		if(p1.bet + 5 <= p1.total)
		        {   p1.bet = p1.bet + 5;
		           bet_money.setText(Integer.toString(p1.bet));
		        }
		        else
		           Toast.makeText(getApplicationContext(), "보유하신 금액을 초과하였습니다.", Toast.LENGTH_LONG).show();
        	}
        });
     	
     	chip_20.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
            if(p1.bet + 20 <= p1.total)
            {   p1.bet = p1.bet + 20;
               bet_money.setText(Integer.toString(p1.bet));
            }
            else
               Toast.makeText(getApplicationContext(), "보유하신 금액을 초과하였습니다.", Toast.LENGTH_LONG).show();
         }
        });
     	
     	chip_100.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
            if(p1.bet + 100 <= p1.total)
            {   p1.bet = p1.bet + 100;
               bet_money.setText(Integer.toString(p1.bet));
            }
            else
               Toast.makeText(getApplicationContext(), "보유하신 금액을 초과하였습니다.", Toast.LENGTH_LONG).show();
         }
        });

     	chip_500.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
            if(p1.bet + 500 <= p1.total)
            {  p1.bet = p1.bet + 500;
               bet_money.setText(Integer.toString(p1.bet));
            }
            else
               Toast.makeText(getApplicationContext(), "보유하신 금액을 초과하였습니다.", Toast.LENGTH_LONG).show();
         }
        });
     	
     	adapter1 = new ArrayAdapter<String>(this,R.layout.simpleitem, p1.Card);
        ListView spot1 = (ListView)findViewById(R.id.player1);
        spot1.setAdapter(adapter1);
     	
        //게임 시작!!
        gameStartBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				if(p1.bet != 0){
					Toast.makeText(getApplicationContext(), "게임이 시작됩니다.", Toast.LENGTH_LONG).show();
					chip_1.setEnabled(false);
					chip_5.setEnabled(false);
					chip_20.setEnabled(false);
					chip_100.setEnabled(false);
					chip_500.setEnabled(false);
					gameStartBtn.setEnabled(false);
					
					hitBtn.setEnabled(true);
			    	stayBtn.setEnabled(true);
			    	splitBtn.setEnabled(true);
			    	doubleBtn.setEnabled(true);
			    	insuranceBtn.setEnabled(true);
			    	evenmoneyBtn.setEnabled(true);
			    	
			    	deck.Shuffle();
			    	
			    	p1.Hit(deck.distribute_card());
			    	adapter1.notifyDataSetChanged();
			    	dealer.recieve(deck.distribute_card());
			    	
			    	p1.Hit(deck.distribute_card());
			    	adapter1.notifyDataSetChanged();
			    	dealer.recieve(deck.distribute_card());
			    }
				else{
					Toast.makeText(getApplicationContext(), "배팅을 해야 게임이 가능합니다.", Toast.LENGTH_LONG).show();
				}
			}
		});

        //임시 노래
        hitBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				p1.Hit(deck.distribute_card());
				adapter1.notifyDataSetChanged();
				index = 0;
				musicOne = 0;
				music = 1;
			}
		});
        stayBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index = 0;
				musicOne = 0;
				music = 2;
			}     	
        });
        splitBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index = 0;
				musicOne = 0;
				music = 3;
			}
        });
        doubleBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index = 0;
				musicOne = 0;
				music = 4;
			}
        });
        insuranceBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index = 0;
				musicOne = 0;
				music = 5;
			}      	
        });
        evenmoneyBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index = 0;
				musicOne = 0;
				music = 6;
			}    	
        });    
    }
    
    class PiezoThread extends Thread{
    	public void run(){
    		while(alive){
				//알람이 꺼지기 전까지 노래 반복
				//반복 알람일 때, 버튼을 누르기 전까지 lcd, dotmatrix, piezo다 실행되야 됌
				if(music == 1 && musicOne == 0){
					int noteDuration = 1000/duration1[index];
    				PiezoControl(music1[index]);
    				try{
    					sleep(noteDuration);
    				}catch(InterruptedException e){
    				}
    				PiezoControl(0);
    				try{
    					sleep(100);
    				}catch(InterruptedException e){
    				}
    				index++;
    				if(index == music1.length){
    					musicOne = 1;
    				}
				}else if(music == 2 && musicOne == 0){
					int noteDuration = 1000/duration2[index];
    				PiezoControl(music2[index]);
    				try{
    					sleep(noteDuration);
    				}catch(InterruptedException e){
    				}
    				PiezoControl(0);
    				try{
    					sleep(100);
    				}catch(InterruptedException e){
    				}
    				index++;
    				if(index == music2.length){
    					musicOne = 1;
    				}
				}else if(music == 3 && musicOne == 0){
					int noteDuration = 1000/duration3[index];
    				PiezoControl(music3[index]);
    				try{
    					sleep(noteDuration);
    				}catch(InterruptedException e){
    				}
    				PiezoControl(0);
    				try{
    					sleep(100);
    				}catch(InterruptedException e){
    				}
    				index++;
    				if(index == music3.length){
    					musicOne = 1;
    				}
				}else if(music == 4 && musicOne == 0){
					int noteDuration = 1000/duration4[index];
    				PiezoControl(music4[index]);
    				try{
    					sleep(noteDuration);
    				}catch(InterruptedException e){
    				}
    				PiezoControl(0);
    				try{
    					sleep(100);
    				}catch(InterruptedException e){
    				}
    				index++;
    				if(index == music4.length){
    					musicOne = 1;
    				}
				}else if(music == 5 && musicOne == 0){
					int noteDuration = 1000/duration5[index];
    				PiezoControl(music5[index]);
    				try{
    					sleep(noteDuration);
    				}catch(InterruptedException e){
    				}
    				PiezoControl(0);
    				try{
    					sleep(100);
    				}catch(InterruptedException e){
    				}
    				index++;
    				if(index == music5.length){
    					musicOne = 1;
    				}
				}else if(music == 6 && musicOne == 0){
					int noteDuration = 1000/duration6[index];
    				PiezoControl(music6[index]);
    				try{
    					sleep(noteDuration);
    				}catch(InterruptedException e){
    				}
    				PiezoControl(0);
    				try{
    					sleep(100);
    				}catch(InterruptedException e){
    				}
    				index++;
    				if(index == music6.length){
    					musicOne = 1;
    				}
				}
    		}
    	}
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            alive = false;
            piezo.interrupt();
            PiezoControl(0);
        }
        return super.onKeyDown(keyCode, event);
    }
}
