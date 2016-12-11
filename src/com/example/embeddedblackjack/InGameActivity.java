package com.example.embeddedblackjack;

import com.example.embeddedblackjack.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class InGameActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);
        
    	final TextView deckNum = (TextView)findViewById(R.id.deckNum);
        
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
    };

}
