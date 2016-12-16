package com.example.embeddedblackjack;



import java.util.ArrayList;

public class Player {
	int total = 15000;
	int bet = 0;
	int nth_card = 0;
	int score = 0;
	boolean My_turn = false;

	ArrayList Card = new ArrayList<String>(); //받은 카드 저장
	ArrayList Spilted =  new ArrayList<String>(); //Spilted된 카드 저장
	
	void First_draw(String card){
	}
	
	void Hit(String card){
		nth_card++;
		Card.add(card);
		char var = card.charAt(1);	
		if(var == 'J' || var == 'Q' || var =='K')
		{score = score + 10;}
		else if(var =='A'){score = score + 11;}
		else if(var == '1'){score = score + 10;}
		else {score = score + (var - 48);}
		
		if(score > 21)
		{
			boolean hasAce = false;
			int AceIndex = 0;
			for(int i=0; i < nth_card; i++)
			{
				String c = (String)Card.get(i);
				char check = c.charAt(1);
				if(check == 'A')
				{
					hasAce = true;
					AceIndex = i;
					break;
				}
			}
			
			if(hasAce == true)
			{
			  String Temp = (String)Card.get(AceIndex);
			  Temp = Temp.replaceAll("A", "a");
			  Card.remove(AceIndex);
			  Card.add(AceIndex, Temp);
			  score = score - 10;
			}
			else
				Bust();
		}
	}
		
	void Bust(){
		Card.removeAll(Card);
		nth_card = 0;
		if(Spilted.isEmpty())
			My_turn = false;
		else
		{Card.add(Spilted.get(0));
		 Spilted.remove(0);}
	}
	
	void Stay(){
		My_turn = false;
	}
	
	void Split(){
		Spilted.add(Card.get(nth_card));
		Card.remove(nth_card);}
}
