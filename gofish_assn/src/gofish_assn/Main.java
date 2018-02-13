package gofish_assn;

import java.io.IOException;

public class Main {
	
	public static void main(String args[]) throws IOException {
		Deck d = new Deck();
		d.shuffle();
		d.printDeck();
		System.out.println(d.deckSize());
		d.dealCard();
		System.out.println(d.deckSize());

		Player player1 = new Player("Rajat");
		Player player2 = new Player("Priebe");
		// TODO Auto-generated constructor stub
		
		for(int i = 0; i < 7; i++) {
			player1.addCardToHand(d.dealCard());
			player2.addCardToHand(d.dealCard());
		}
		System.out.println(player1.handToString());
		System.out.println(player2.handToString());
		player1.checkHandForBook();
		player2.checkHandForBook();
		System.out.println(player1.bookToString());
		System.out.println(player2.bookToString());
		
		player1.addCardToHand(d.dealCard());
	//	System.out.println(player1.handToString());
	//	System.out.println(d.deckSize());
		System.out.println("Rajat asks, do you have a " + player1.chooseCardFromHand().printRankToString() +"?");


	}
}


