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
        
    	//������ �޾ƿ���
        Intent intent = getIntent();
        String playerCnt = intent.getExtras().getString("playerCnt");
        String aiCnt = intent.getExtras().getString("aiCnt");
        String levelCnt = intent.getExtras().getString("levelCnt");
        
        if(playerCnt.equals("2��")){
        	
        }else if(playerCnt.equals("3��")){
        	
        }
        
        if(aiCnt.equals("1��")){
        	
        }else if(aiCnt.equals("2��")){
        	
        }else if(aiCnt.equals("3��")){
        	
        }else if(aiCnt.equals("4��")){
        	
        }
        
        if(levelCnt.equals("1��")){
        	deckNum.append("1 DECK");
        }else if(levelCnt.equals("2��")){
        	deckNum.append("2 DECK");
        }else if(levelCnt.equals("4��")){
        	deckNum.append("4 DECK");
        }else if(levelCnt.equals("6��")){
        	deckNum.append("6 DECK");
        }
        
    };

}
