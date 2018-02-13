package gofish_assn;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> book = new ArrayList<Card>();
	private String name;
	
	public Player(String name) {		//done
		this.name = name;
	}
	
	public void addCardToHand(Card c) {		//done
		hand.add(c);		//added
	}
	
	public Card removeCardFromHand(Card c) {
		if(hand.contains(c)) {
			hand.remove(c);
		}
		return c;
	}
	
	public void getCardFromOpponent(Player player, Card c) {	//I created this method - not original
		if(player.cardInHand(c) == true) {					//probably not necessary because we can call addHand(other player.remove card from hand)
			player.removeCardFromHand(c);
			hand.add(c);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String handToString() {				// this works - but change it up 
		String handString = hand.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
		return handString;
	}
	
	public String bookToString() {	//need to implement
		String bookString = book.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
		return bookString;
	}
	
	public int getHandSize() {	
		return hand.size();	//added
	}
	
	
	public int getBookSize() {
		return book.size();	//added
	}
	
	
	//Here are some ideas for additional functionality that you may want/need
	//OPTIONAL
    // comment out if you decide to not use it
    //this function will check a players hand for a pair. 
    //If a pair is found, it moves the cards to the book and returns true

    public boolean checkHandForBook() {
    	Card c = new Card();
    	Card d = new Card();
    		for(int i = 0; i < hand.size(); i++) {			//Super inefficient, try to optimize
    			for(int j = i + 1; j < hand.size(); j++) {	//only works for 1 pair initially
    				if(hand.get(i).getRank() == hand.get(j).getRank()) {
    					c = hand.get(i);
    					d = hand.get(j);
    					book.add(hand.get(i));
    					book.add(hand.get(j));
    					hand.remove(i);
    					hand.remove(j);
    					continue;
    				}
    			}
    		}
    	return false; //stubbed
    }

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
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
    public Card chooseCardFromHand() {
	    	Card c = new Card();
        int element=(int)(Math.random()*hand.size());  
        c = hand.get(element);
	    	return c;
    }
    
    
    //Does the player have the card c in her hand?
    public boolean cardInHand(Card c) {
    	for(int i = 0; i < hand.size(); i++) {
    		if(hand.get(i).equals(c)) {	//this should compare the actual card w/ .equals instead of == 
    			return true;
    		}
    	}
    	return false; //stubbed
    }
    

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c
    
 /*   public boolean sameRankInHand(Card c) {			this function is redundant because of RankInHand
    	return false; //stubbed 
    }	*/

}
