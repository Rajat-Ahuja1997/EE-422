package gofish_assn;

public class GoFishGame {

	public GoFishGame() {
		
		
		Player p1 = new Player("Rajat");
		Player p2 = new Player("Ahuja");
		Deck deck = new Deck();
		// TODO Auto-generated constructor stub
		
		for(int i = 0; i <= 7; i++) {
			p1.addCardToHand(deck.dealCard());
			p2.addCardToHand(deck.dealCard());
		}
		
		p1.checkHandForBook();
		p2.checkHandForBook();
		while(deck.deckSize() != 0) {			
			Card c = new Card(); 
			c = p1.chooseCardFromHand();
			System.out.println(p1.getName() +" asks, do you have a " + c.printRankToString() +"?");
			if(p2.rankInHand(c)) {
				p1.addCardToHand(c);
				p2.removeCardFromHand(c);
				continue;
			}
			else {
				p1.addCardToHand(deck.dealCard());				
			}
			Card d = new Card();
			d = p2.chooseCardFromHand();
			System.out.println(p2.getName() + " asks, do you have a " + d.printRankToString() + "?");
			if(p1.rankInHand(d)) {
				p2.addCardToHand(d);
				p1.removeCardFromHand(d);
			}
	//		else

		}
		
		if(p1.getBookSize() > p2.getBookSize()) {
			System.out.println(p1.getName() + " wins!" );
		}
		if(p1.getBookSize() < p2.getBookSize()) {
			System.out.println(p2.getName() + " wins!");
		}
		System.out.println(p1.getName() + " and " + p2.getName() + " tied!");

	}
}