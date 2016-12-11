package com.example.embeddedblackjack;

import com.example.embeddedblackjack.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InGameActivity extends Activity {
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
	6,65,20,6,65,19,6,65,66,6,65,19,6,65,66,65,
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
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);
        System.loadLibrary("blackjack");
        
    	final TextView deckNum = (TextView)findViewById(R.id.deckNum);
    	final Button hitBtn = (Button)findViewById(R.id.hit);
    	final Button stayBtn = (Button)findViewById(R.id.Stay);
    	final Button splitBtn = (Button)findViewById(R.id.Split);
    	final Button doubleBtn = (Button)findViewById(R.id.Double);
    	final Button insuranceBtn = (Button)findViewById(R.id.Insurance);
    	final Button evenmoneyBtn = (Button)findViewById(R.id.Even_money);

    	//Piezo
    	piezo.setDaemon(true);
		piezo.start();

		PiezoData = 0;
		PiezoControl(PiezoData);
        
    	//설정값 받아오기
        Intent intent = getIntent();
        
        int playerCnt = intent.getIntExtra("playerCnt",0);
        int aiCnt = intent.getIntExtra("aiCnt",0);
        int levelCnt = intent.getIntExtra("levelCnt",0);
        
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
        
        //임시 노래
        hitBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
