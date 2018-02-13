package gofish_assn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Random;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck
	
	//creates a new sorted deck
	//we want to add all 52 cards to the deck, with each card
	//having a rank and a suit
	//deck.add(rank, suit)
	//
	public Deck() {	
		for (Card.Suits suit: Card.Suits.values()) { 
			for (int a = 1; a <= 13; a++) {
				Card currentCard = new Card(a, suit);
				deck.add(currentCard);
			}
		}
	}
	
	public void shuffle() {
		ArrayList<Card> temp = new ArrayList<Card>();
		while(!deck.isEmpty()) {
            int element=(int)(Math.random()*deck.size()); //we get a random element from deck from 1-52
            temp.add(deck.get(element));	//we get the card at location "element" and add it to our temp deck
            deck.remove(element);   
		}
		deck = temp;
		
	}
	
	public void printDeck() throws IOException {
		try {
			PrintWriter output = new PrintWriter("output.txt");
			for(int i = 0; i < deck.size(); i++) {
				output.println(deck.get(i));
			}
			output.close();
		}
		catch (IOException ex) {
			System.out.println("IO Exception!" + ex);
		}
	}
	
	
	public Card dealCard() {
		Card c = null;
		if(!deck.isEmpty()) {
			c = deck.get(0);
			deck.remove(0);
		}
		return c;
	}
	
	public int deckSize() {
		
		return deck.size();
	}
	

}
