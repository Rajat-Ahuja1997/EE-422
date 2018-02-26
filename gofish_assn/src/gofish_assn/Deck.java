package gofish_assn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Random;

/**
 * @param deck Arraylist of cards
 * @param NUM_CARDS Final int holding the number of cards in a deck
 */
public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck
	
	/**
	 * @param suit is a loop counter for 4 suits
	 * @return Nothing, this is a constructor
	 */
	public Deck() {	
		for (Card.Suits suit: Card.Suits.values()) { 
			for (int a = 1; a <= 13; a++) {
				Card currentCard = new Card(a, suit);
				deck.add(currentCard);
			}
		}
	}
	
	/**
	 * @param temp is an empty deck to fill our random cards with
	 * @param element is a random int that gets set with math random
	 * @return Nothing, this is a void method
	 */
	public void shuffle() {
		ArrayList<Card> temp = new ArrayList<Card>();
		while(!deck.isEmpty()) {
            int element=(int)(Math.random()*deck.size()); //we get a random element from deck from 1-52
            temp.add(deck.get(element));	//we get the card at location "element" and add it to our temp deck
            deck.remove(element);   
		}
		deck = temp;
		
	}
	
	/**
	 * @return Nothing, but outputs our deck to a text file
	 */
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
	
	/**
	 * @param c Card that gets our top card of the deck
	 * @return Card is the card on top of the deck
	 */
	public Card dealCard() {
		Card c = null;
		if(!deck.isEmpty()) {
			c = deck.get(0);
			deck.remove(0);
		}
		return c;
	}
	
	/**
	 * @return Int the size of our deck
	 */
	public int deckSize() {
		return deck.size();
	}
	

}
