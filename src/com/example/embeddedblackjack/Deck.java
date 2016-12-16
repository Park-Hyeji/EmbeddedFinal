package com.example.embeddedblackjack;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
	  final String One_Card_Set[] = { "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK",
			  						  "DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
			  						  "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
			  						  "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK"};
	  int num_of_deck;
	  int nth_card;
	  ArrayList <String>Total_Card_Set;
	  String[] Card_Shuffled;
	
	  Deck(int num_of_deck){
		  this.num_of_deck = num_of_deck;
		  this.nth_card = 0;
		  Total_Card_Set = new ArrayList<String>();
		  Card_Shuffled = new String[52 * num_of_deck];
		  for(int i=0; i<num_of_deck; i++)
		  {Total_Card_Set.addAll(Arrays.asList(One_Card_Set));}}
	  
	  void Shuffle()
	  {
		  for(int i =0; i<num_of_deck * 52; i++)
		  {
	            int r = (int) (Math.random() * i);
	            String swap = Total_Card_Set.get(i);
	            Card_Shuffled[i] = Total_Card_Set.get(r);
	            Card_Shuffled[r] = swap;
		  }
	  }
	  
	  String distribute_card()
	  {if(nth_card < num_of_deck * 52) 
		  return Card_Shuffled[nth_card++];
	  else return "Deck is Empty";}
}
