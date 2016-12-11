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
        
        //���� ���� ������ InGame���� �ٲ�

        gameStartBtn.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        
				String realPlayerCnt = playerCnt.getText().toString();
				String realaiCnt = playerCnt.getText().toString();
				String reallevelCnt = playerCnt.getText().toString();
				
				//ȭ�� ��ȯ
				Intent intent = new Intent(MainActivity.this,InGameActivity.class);
				intent.putExtra("playerCnt", realPlayerCnt); //�÷��̾� �� ����
				intent.putExtra("aiCnt", realaiCnt); //ai�� ����
				intent.putExtra("levelCnt", reallevelCnt); //���� �� ����
				startActivity(intent);
			}
		});
        
        //���� ��ư ����
        //�÷��̾� ���� AI���� ���� 6
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
