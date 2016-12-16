package com.example.embeddedblackjack;

import java.util.ArrayList;

public class Item {
	/*
	 * 딜러 카드 훔쳐보기
	 * 카드 한 장 덱에서 바꾸기
	 * 이겼을 때, 보상 X2
	 * 다른 플레이어와 카드 한 장 바꾸기
	 * BUST됐을 때, 카드 한 장 버리기
	 * 패배 시에 배팅 금액을 돌려받기
	 */
	
	final Player player = new Player();
	final Dealer dealer = new Dealer();
	final InGameActivity ingameactivity = new InGameActivity();
	final Deck deck = new Deck(ingameactivity.levelCnt);
	
	void nowItem(){
		//이번 경기에서 어떤 카드를 받으면 어떤 아이템을 얻는지 보여줌
		//S,D,H,C중 1개 랜덤, A~K까지 랜덤 돌려서 6가지 만듦, 그런 뒤 순서대로 위에 쓰인 순서대로 할당
		//Deck에서 랜덤하게 돌린 것 중 6개 뽑아오기

	}
	
	void peek(){
		//딜러 카드 훔쳐보기
		System.out.print(dealer.Card.indexOf(2)); 
		//또는 딜러 ListView에서 보이게 하기 -> 기획에 따라 달라짐
	}
	
	void cardChange(){
		//카드 한 장 덱에서 바꾸기
		//선택한 카드 다시 덱에 섞기
		//빼고 싶은 카드 선택하는 창 띄우면 좋을 듯
		//해당 인덱스 값 받아와서 그 값 DELETE하고 전체 덱 배열에 넣어줌
		//! player.Card.remove(i); //i = 인덱스 값
		//! deck.cardShuffled.add(마지막 인덱스) = player.Card.indexOf(i); //배열이 어떻게 되는건지 모르겠음
	}
	
	void doubleWin(){
		//이겼을 때, 보상 X2
		player.total += player.bet * 2;
	}
	
	void otherPlayerCardChange(){
		//다른 플레이어와 카드 한 장 바꾸기
		//다른 플레이어 배열생기면 swap 고고
	}
	
	void abandonCard(){
		//Bust, 카드 한 장 버리기
		if(player.score > 21 && player.hasAce != true){
			//! player.Card.remove(i); //i = 인덱스 값
		}
	}
	
	void getBackBet(){
		//금액 돌려받기
		//BUST 혹은 딜러한테 짐
		player.total += player.bet; //어디에서 BET금액을 빼는지 몰라서 걍 한번 더 더해 마이너스 되는 돈을 0으로 만듦
		
	}
}
