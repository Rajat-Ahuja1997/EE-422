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
import java.util.List;

public class Critter3 extends Critter {
	private int numWalks;
	private int numRuns;
	private int dir;
	
	@Override
	public String toString() {return "3";}
	
	public Critter3() {
		numWalks = 0;
		numRuns = 0;
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public void doTimeStep() {
		int move = Critter.getRandomInt(8);
		if(move == 3) { //moves 12.5% of the time
			walk(dir);
			numWalks++;
		}
		if(getEnergy() > 35) { //reproduce
			Critter3 newRajatCrit1= new Critter3();
			reproduce(newRajatCrit1, Critter.getRandomInt(8));
		}
		//change direction
		dir = Critter.getRandomInt(8);

		// TODO Auto-generated method stub
	}

	@Override
	public boolean fight(String opponent) {
		if(!isMoved) {
			if(opponent.equals("project4.Critter3")) {
				run(dir);	// put in direction
				numRuns++;
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	/*public static void runStats(List<Critter> RajatCritter1) {
		int totalWalks = 0;
		int totalRuns = 0;
		for(Object obj : RajatCritter1) {
			Critter3 crit = (Critter3) obj;
			totalWalks += crit.numWalks;
			totalRuns += crit.numRuns;
		}
		System.out.println("Number of runs taken:: " + totalRuns);
		System.out.println("Number of walks taken: " + totalWalks);
	} */
	
	@Override
	public CritterShape viewShape() { return CritterShape.DIAMOND; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.DARKSEAGREEN; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.DARKSEAGREEN; } 


}
