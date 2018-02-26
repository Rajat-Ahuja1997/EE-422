package gofish_assn;

public class Card {
	/**
	 * @author Rajat Ahuja/RA29697
	 * 
	 */
	
	public enum Suits {club, diamond, heart, spade};
	
	final static int TOP_RANK = 13; //King
	final static int LOW_RANK = 1; //Ace
	
	private int rank;  //1 is an Ace
	private Suits suit;
	
	/**
	 * This is the default constructor for Card - we have 52 cards 
	 * that each have a rank and a suit. These cards form a deck
	 * @return Nothing, this is our default constructor
	 */
	public Card() {	
		rank = 1;
		suit = Suits.spade;
	}
	
	/**
	 * @param r is an Int holding our rank
	 * @param s is a Char holding our suit
	 * @return Nothing, this is a constructor
	 */
	public Card(int r, char s) {
		this.rank = r;
		this.suit = toSuit(s);
	}
	
	/**
	 * @param r is an Int holding our rank
	 * @param s represents a Suit from our enum
	 * @return Nothing, this is a constructor
	 */
	public Card(int r, Suits s) {
		this.rank = r;
		this.suit = s;
	}
	
	/**
	 *@param c Card that defines our rank and suit
	 */
	public Card(Card c) {
		rank = c.getRank();
		suit = c.getSuit();
	}
	
	/**
	 * @param c Char holding our suit's first letter
	 * @return The Suits object that corresponds to our char
	 */
	private Suits toSuit(char c) {
		if (c == 's') {
			return Suits.spade; //dummy
		}
		if (c == 'c') {
			return Suits.club;
		}
		if (c == 'd') {
			return Suits.diamond;
		}
		if (c == 'h') {
			return Suits.heart;
		}
		return Suits.spade;
	}
	
	/**
	 * @param s Suits object with our given suit
	 * @return String that corresponds to s
	 */
	private String suitToString(Suits s) {
		if (s == Suits.club) {
			return "c";
		}
		if (s == Suits.spade) {
			return "s";
		}
		if (s == Suits.heart) {
			return "h";
		}
		if (s == Suits.diamond) {
			return "d";
		}
		return "s";
	}
	
	/**
	 * @param r Int holding our rank
	 * @return String of r's corresponding rank
	 */
	private String rankToString(int r) {
		switch(r) {
			case 1: return "A";
			case 2: return "2";
			case 3: return "3";
			case 4: return "4";
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			default: return "A";
		}
	}
	
	/**
	 * @return Int rank of our card
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * @return Suits object of our card
	 */
	public Suits getSuit() {
		return suit;
	}
	
	/**
	 * @return String containing our rank and suit
	 */
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + suitToString(getSuit());
		
		return s;
	}
	
	/**
	 * @return String containing just our rank
	 */
	public String printRankToString() {
		String s = "";
		
		s = s + rankToString(getRank());
		
		return s;
	}
}
