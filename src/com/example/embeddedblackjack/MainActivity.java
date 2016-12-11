package com.example.embeddedblackjack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends Activity {

    RadioButton playerCnt = (RadioButton)findViewById(R.id.playerCnt);
    RadioButton aiCnt = (RadioButton)findViewById(R.id.aiCnt);
    RadioButton levelCnt = (RadioButton)findViewById(R.id.levelCnt);
	Button gameStartBtn = (Button)findViewById(R.id.gameStartBtn);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //게임 시작 누르면 InGame으로 바뀜

        gameStartBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        
				String realPlayerCnt = playerCnt.getText().toString();
				String realaiCnt = playerCnt.getText().toString();
				String reallevelCnt = playerCnt.getText().toString();
				
				//화면 전환
				Intent intent = new Intent(MainActivity.this,InGameActivity.class);
				intent.putExtra("playerCnt", realPlayerCnt); //플레이어 수 전달
				intent.putExtra("aiCnt", realaiCnt); //ai수 전달
				intent.putExtra("levelCnt", reallevelCnt); //레벨 값 전달
				startActivity(intent);
			}
		});
        
        //라디오 버튼 제어
        //플레이어 수랑 AI수의 합은 6
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
