package assignment5;


/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * <Rajat Ahuja>
 * <RA29697>
 * <15465>
 * <Shane Zhao>
 * <SSZ255>
 * <15465>
 * Slip days used: <0>
 * Spring 2018
 */

public class Critter1 extends Critter {

	private int prevDirection;
	private int numWalk;
	
	
	@Override
	public String toString() {
		return "1";
	}
	
	
	public Critter1() {
		prevDirection = Critter.getRandomInt(8);
		numWalk = 0;
	}
	
	@Override
	public void doTimeStep() {
		int move = Critter.getRandomInt(4);
		if(move == 2) { //moves 25% of the time
			walk(prevDirection);
			numWalk++;
		}
		if(getEnergy() > 80) { //reproduce
			Critter1 crit1 = new Critter1();
			reproduce(crit1, Critter.getRandomInt(8));
		}
		//change direction
		prevDirection = Critter.getRandomInt(8);
		
		}

	@Override
	
	public boolean fight(String oponent) {
		if(!isMoved) {//fights when moved
			if(oponent.equals("assignment4.Critter1")) {//does not fight with same kind
				walk(prevDirection);
				numWalk++;
				return false;
			}
		}
		return true;
	}

	
/*	public static void runStats(java.util.List<Critter> critters1) {
		int totalWalks = 0;
		for(Object obj : critters1) {
			Critter1 crit = (Critter1) obj;
			totalWalks += crit.numWalk;
		}
		System.out.println("Total number of Critters1 alive: " + critters1.size());
		System.out.println("Number of walks taken: " + totalWalks);
	} */
	
	@Override
	public CritterShape viewShape() { return CritterShape.CIRCLE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.CORAL; }
	
	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.CORAL; } 

}
