package gofish_assn;

public class Card {
	
	public enum Suits {club, diamond, heart, spade};
	
	final static int TOP_RANK = 13; //King
	final static int LOW_RANK = 1; //Ace
	
	private int rank;  //1 is an Ace
	private Suits suit;
	
	public Card() {	//default constructor
		rank = 1;
		suit = Suits.spade;
	}
	
	public Card(int r, char s) {
		this.rank = r;
		this.suit = toSuit(s);
	}
	
	public Card(int r, Suits s) {
		this.rank = r;
		this.suit = s;
	}
	
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
	
	public int getRank() {
		return rank;
	}
	
	public Suits getSuit() {
		return suit;
	}
	
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + suitToString(getSuit());
		
		return s;
	}
	
	public String printRankToString() {
		String s = "";
		
		s = s + rankToString(getRank());
		
		return s;
	}
}
