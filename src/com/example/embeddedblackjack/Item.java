package com.example.embeddedblackjack;

import java.util.ArrayList;

public class Item {
	/*
	 * ���� ī�� ���ĺ���
	 * ī�� �� �� ������ �ٲٱ�
	 * �̰��� ��, ���� X2
	 * �ٸ� �÷��̾�� ī�� �� �� �ٲٱ�
	 * BUST���� ��, ī�� �� �� ������
	 * �й� �ÿ� ���� �ݾ��� �����ޱ�
	 */
	
	final Player player = new Player();
	final Dealer dealer = new Dealer();
	final InGameActivity ingameactivity = new InGameActivity();
	final Deck deck = new Deck(ingameactivity.levelCnt);
	
	void nowItem(){
		//�̹� ��⿡�� � ī�带 ������ � �������� ����� ������
		//S,D,H,C�� 1�� ����, A~K���� ���� ������ 6���� ����, �׷� �� ������� ���� ���� ������� �Ҵ�
		//Deck���� �����ϰ� ���� �� �� 6�� �̾ƿ���

	}
	
	void peek(){
		//���� ī�� ���ĺ���
		System.out.print(dealer.Card.indexOf(2)); 
		//�Ǵ� ���� ListView���� ���̰� �ϱ� -> ��ȹ�� ���� �޶���
	}
	
	void cardChange(){
		//ī�� �� �� ������ �ٲٱ�
		//������ ī�� �ٽ� ���� ����
		//���� ���� ī�� �����ϴ� â ���� ���� ��
		//�ش� �ε��� �� �޾ƿͼ� �� �� DELETE�ϰ� ��ü �� �迭�� �־���
		//! player.Card.remove(i); //i = �ε��� ��
		//! deck.cardShuffled.add(������ �ε���) = player.Card.indexOf(i); //�迭�� ��� �Ǵ°��� �𸣰���
	}
	
	void doubleWin(){
		//�̰��� ��, ���� X2
		player.total += player.bet * 2;
	}
	
	void otherPlayerCardChange(){
		//�ٸ� �÷��̾�� ī�� �� �� �ٲٱ�
		//�ٸ� �÷��̾� �迭����� swap ���
	}
	
	void abandonCard(){
		//Bust, ī�� �� �� ������
		if(player.score > 21 && player.hasAce != true){
			//! player.Card.remove(i); //i = �ε��� ��
		}
	}
	
	void getBackBet(){
		//�ݾ� �����ޱ�
		//BUST Ȥ�� �������� ��
		player.total += player.bet; //��𿡼� BET�ݾ��� ������ ���� �� �ѹ� �� ���� ���̳ʽ� �Ǵ� ���� 0���� ����
		
	}
}
