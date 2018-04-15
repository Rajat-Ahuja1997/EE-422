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

public class Critter2 extends Critter {

	private int direction;
	private int numRun;
	private int numWalk;
	
	
	@Override
	public String toString() {
		return "2";
	}
	
	
	public Critter2() {
		direction = Critter.getRandomInt(8);
		numRun = 0;
	}
	
	@Override
	public void doTimeStep() {
		int move = Critter.getRandomInt(8);
		if((move % 3 == 2) || (move % 3 == 1)) { //moves 5/8 of the time
			run(direction);
			numRun++;
		}
		//change direction
		direction = Critter.getRandomInt(8);
		
	}

	@Override
	public boolean fight(String oponent) {
		if(oponent.equals("assignment4.Critter1") || oponent.equals("assignment4.Algae")) {//fights critter1 && algae
			return true;
		}else{ //runs away else
			walk(direction);
			numWalk++;
		}
		return false;
	}

	public static String runStats(java.util.List<Critter> critters2) {
		String a = "";
		int totalWalks = 0;
		int totalRuns = 0;
		for(Object obj : critters2) {
			Critter2 crit = (Critter2) obj;
			totalWalks += crit.numWalk;
			totalRuns += crit.numRun;
		}
		System.out.println("Total number of Critters2 alive: " + critters2.size());
		System.out.println("Number of walks taken: " + totalWalks);
		System.out.println("Number of runs taken: " + totalRuns);
		a += "Number of runs taken: " + totalRuns;
		return a;
	} 
	
	@Override
	public CritterShape viewShape() { return CritterShape.TRIANGLE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.PALEVIOLETRED; }
	
	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.PALEVIOLETRED; } 
}
