package gofish_assn;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This represents one of our two players for the Go Fish Game
 */
public class Player {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> book = new ArrayList<Card>();
	private String name;
	
	/**
	 * @param name Holds a string with name of Player.
	 */
	public Player(String name) {		//done
		this.name = name;
	}
	
	/**
	 * @param c Card given to be added to hand
	 */
	public void addCardToHand(Card c) {		//done
		hand.add(c);		//added
	}
	
	/**
	 * @param c Card given to be added to book
	 */
	public void addCardToBook(Card c) {
		book.add(c);
	}
	
	/**
	 * @param c Card to be removed from hand
	 * @return Card that was removed.
	 */
	public Card remove(Card c) {
		if(hand.contains(c)) {
			hand.remove(c);
		}
		return c;
	}
	
	/**
	 * Removes a card from our hand when an opponent has the matching card (same rank)
	 * @param c Card to determine which card we are removing from hand
	 * @return Card that we are removing from the hand
	 */
	public Card removeCardFromHand(Card c) {
		Card d = new Card();
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getRank() == c.getRank()) {
				d = hand.remove(i);
			}
		}
		return d;
	}
	
	/**
	 * @return the player's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return String that shows a player's hand in string form
	 */
	public String handToString() {				// this works - but change it up 
		String handString = hand.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
		return handString;
	}
	
	/**
	 * 
	 * @return String that shows a player's book in string form
	 */
	public String bookToString() {	//need to implement
		String bookString = book.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
		return bookString;
	}
	
	/**
	 * @return int that is the size of a player's hand
	 */
	public int getHandSize() {	
		return hand.size();	//added
	}
	
	/**
	 * @return int that is the size of a player's book
	 */
	public int getBookSize() {
		return book.size();	//added
	}
	
	
	//Here are some ideas for additional functionality that you may want/need
	//OPTIONAL
    // comment out if you decide to not use it
    //this function will check a players hand for a pair. 
    //If a pair is found, it moves the cards to the book and returns true

	/**
	 * This method checks our hand to see if we have a book that can be 
	 * added to our book
	 * @return True if we have a card to be removed, false if not
	 * 
	 */
    public boolean checkHandForBook() {
    		for(int i = 0; i < hand.size()-1; i++) {
    			for(int j = i+1; j < hand.size(); j++) {
    				Card refCard = new Card(hand.get(i));
    				Card compCard = new Card(hand.get(j));

    				if(refCard.getRank() == compCard.getRank()){
    					book.add(refCard);
    					book.add(compCard);
    					hand.remove(j);
    					hand.remove(i);
    					return true;
    				}
    			}
    		}
        	return false; //stubbed
    }
    
	/**
	 * This method checks our hand to see if we have a book that can be 
	 * added to our book 
	 * @param c Card that determines what book should be formed
	 * @return True if we have a card to be removed, false if not
	 * 
	 */
    public boolean checkHandForBook(Card c) {
    		for(int i = 0; i < hand.size(); i++) {
    			Card refCard = new Card(hand.get(i));
    			if(refCard.getRank() == c.getRank()) {
    				book.add(refCard);
    				hand.remove(i);
    				return true;
    			}
    		}
    		return false;
     }

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
   /**
    * @param c Card that determines what rank is to be compared
    * @return true if we find the requested rank
    */
    public boolean rankInHand(Card c) {
	    	for(int i = 0; i < hand.size(); i++) {
	    		if(hand.get(i).getRank() == c.getRank()) {
	    			return true;
	    		}
	    	}
	    	return false; //stubbed
    }
    
    //uses some strategy to choose one card from the player's
    //hand so they can say "Do you have a 4?"
    /**
     * Chooses a random card from a player's hand to see 
     * if the other player has a card of the same rank
     * @return Card that is to be compared
     */
    public Card chooseCardFromHand() {
	    	Card c = new Card();
        int element=(int)(Math.random()*hand.size());  
        c = hand.get(element);
	    	return c;
    }
    
    /**
     * Chooses a card at a specific location to ask the other player
     * @param i the location that decides which card is to be removed
     * @return c Card that is to be removed
     */
    	public Card chooseCardFromHand(int i) {
    		Card c = new Card();
    		c = hand.get(i);
    		return c;
    	}
    
    //Does the player have the card c in her hand?
    	/**
    	 * @param c Card we are looking for
    	 * @return a boolean value to see if we have found the card
    	 */
    public boolean cardInHand(Card c) {
	    	for(int i = 0; i < hand.size(); i++) {
	    		if(hand.get(i).equals(c)) {	//this should compare the actual card w/ .equals instead of == 
	    			return true;
	    		}
	    	}
    	return false; //stubbed
    }
    
    /**
     * @param rank Finding card of a specific rank
     * @return c Card that we have found (if we find it)
     */
    public Card findCard(int rank) {
    		for(int i = 0; i < hand.size(); i++) {
    			if(hand.get(i).getRank() == rank) {
    				Card c = new Card(hand.get(i));
    				return c;
    			}
    		}
    		return null;
    }
    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c
    
 /*   public boolean sameRankInHand(Card c) {			this function is redundant because of RankInHand
    	return false; //stubbed 
    }	*/

}
