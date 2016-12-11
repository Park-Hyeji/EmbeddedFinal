package com.example.embeddedblackjack;

import com.example.embeddedblackjack.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InGameActivity extends Activity {
TextView deckNum = (TextView)findViewById(R.id.deckNum);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);
        
    	//Ό³Α¤°ª ΉήΎΖΏΐ±β
        Intent intent = getIntent();
        String playerCnt = intent.getExtras().getString("playerCnt");
        String aiCnt = intent.getExtras().getString("aiCnt");
        String levelCnt = intent.getExtras().getString("levelCnt");
        
        if(playerCnt.equals("2Έν")){
        	
        }else if(playerCnt.equals("3Έν")){
        	
        }
        
        if(aiCnt.equals("1Έν")){
        	
        }else if(aiCnt.equals("2Έν")){
        	
        }else if(aiCnt.equals("3Έν")){
        	
        }else if(aiCnt.equals("4Έν")){
        	
        }
        
        if(levelCnt.equals("1µ¦")){
        	deckNum.append("1 DECK");
        }else if(levelCnt.equals("2µ¦")){
        	deckNum.append("2 DECK");
        }else if(levelCnt.equals("4µ¦")){
        	deckNum.append("4 DECK");
        }else if(levelCnt.equals("6µ¦")){
        	deckNum.append("6 DECK");
        }
        
    };

}
