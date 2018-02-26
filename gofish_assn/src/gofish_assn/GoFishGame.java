package gofish_assn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that actually plays our Go Fish game
 */
public class GoFishGame {

	Player p1 = new Player("Rajat");
	Player p2 = new Player("Roger");
	Deck deck = new Deck();
	private PrintWriter output;
	
	/**
	 * Our default constructor that initializes the game
	 */
	public GoFishGame() {
		try {
			File outputFile = new File("GoFishResults.txt");
			output = new PrintWriter(outputFile);
		}
		catch(IOException ex) {
			System.out.println("File doesn't exist: "+ ex);
		}
		deck.shuffle();
		initializeHands();
	}
	
	/**
	 * Initializes our player's hands with 7 cards each
	 */
	private void initializeHands() {
		for(int i = 0; i < 7; i++) {
			p1.addCardToHand(deck.dealCard());
			p2.addCardToHand(deck.dealCard());
		}
		p1.checkHandForBook();
		p2.checkHandForBook();
		
	}
	
	/**
	 * @param p Player that we need to see has a card
	 * @return Card that our player has (or doesn't have) 
	 */
	private Card askPlayerForCard(Player p) {
		if (p.getHandSize() == 0) {
			output.println(p.getName() + " has no cards");
			return null;
		}
		else {
			Card c = p.chooseCardFromHand();
			output.println(p.getName() + " asks - Do you have a " + c.printRankToString() + "?\n");
			return c;
		}
	}
	
	/**
	 * 
	 * @param p Player that may have a card (of a specific rank)
	 * @param c Card that we are looking to match up rank-wise
	 * @return Card that the opponent has
	 */
	private Card checkIfPlayerHasCard(Player p, Card c) {
		Card retCard = null;
		if(p.rankInHand(c)) {
			output.println(p.getName() + " says - Yes, I have a " + c.printRankToString()+"\n");
			retCard = p.removeCardFromHand(c);
		}
		else {
			output.println(p.getName() + " says - No, Go Fish.\n");
		}
		return retCard;
	}
	
	/**
	 * @param p Player that needs to draw a card from the deck
	 */
	private void goFish(Player p) {
		Card c = deck.dealCard();
		p.addCardToHand(c);
		p.checkHandForBook();
		output.println(p.getName() + " draws a card.\n");
		
	}
	
	/**
	 * This method is where the player adds a card to their book. In our implementation, this needs to be called twice
	 * @param p Player that forms a book with card c
	 * @param c Card that a player forms a book with
	 */
	private void book(Player p, Card c) {
		p.addCardToBook(c);
		p.remove(c);
		//p.checkHandForBook();
	}
	
	/**
	 * The actual gameplay of our game.
	 */
	public void playGame() {
		output.println(p1.getName() + " and " + p2.getName() + " are about to play Go Fish!\n");
		Player asking = p1;
		Player answering = p2;
		while(p1.getBookSize() + p2.getBookSize() < 52){	
			Card c = askPlayerForCard(asking);
			if(c == null) {
				goFish(asking);
				Player temp = asking;
				asking = answering;
				answering = temp;
			}
			else {
				Card retCard = checkIfPlayerHasCard(answering, c);
				if(retCard == null) {
					goFish(asking);
					Player temp = asking;
					asking = answering;
					answering = temp;
				}
				else {
					book(asking, c);
					book(asking, retCard);
					output.println(asking.getName() + " books the " + c.printRankToString() + "\n");
				}
			}
		}
	/*	Card d = asking.chooseCardFromHand(0);
		output.println(asking.getName() + " asks - Do you have a " + d.printRankToString() + "?\n");
		Card retCard = checkIfPlayerHasCard(answering, d);
		book(asking, d);
		book(asking, retCard); */
		Results();
	}
	
	/**
	 * Shows who won/lost the game based on our book sizes.
	 */
	public void Results() {
		
		if(p1.getBookSize() > p2.getBookSize()) {
			output.println(p1.getName() + " wins with " + p1.getBookSize()/2 + " pairs." );
			output.println(p1.bookToString());
		//	output.println(p1.handToString());
			output.println(p2.getName() + " has " + p2.getBookSize()/2 + " pairs.");
			output.println(p2.bookToString());
		//	output.println(p2.handToString());
			try {
				deck.printDeck();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(p1.getBookSize() < p2.getBookSize()) {
			output.println(p2.getName() + " wins with " + p2.getBookSize()/2 + " pairs." );
			output.println(p2.bookToString());
		//	output.println(p2.handToString());
			output.println(p1.getName() + " has " + p1.getBookSize()/2 + " pairs.");
			output.println(p1.bookToString());
		//	output.println(p1.handToString());
		}
		else {
			output.println(p1.getName() + " and " + p2.getName() + " tied!");
			output.println(p1.bookToString());
			output.println(p2.bookToString());

		}
		output.close();
	}
}
