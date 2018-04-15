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

public class Critter4 extends Critter {
	private int dir;
	
	@Override
	public String toString() {return "4";}
	
	public Critter4() {
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public void doTimeStep() {
		int move = Critter.getRandomInt(4);
		if(move == 3) { //moves 12.5% of the time
			walk(dir);
		}
		if(getEnergy() > 45) { //reproduce
			Critter4 newRajatCrit2= new Critter4();
			reproduce(newRajatCrit2, Critter.getRandomInt(8));
		}
		//change direction
		dir = Critter.getRandomInt(8);
		
		// TODO Auto-generated method stub
	}

	@Override
	public boolean fight(String opponent) {
		if(!isMoved) {
			if(opponent.equals("project4.Critter4") || opponent.equals("project4.Algae")) {
				run(dir);	// put in direction
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	/*public static void runStats(List<Critter> RajatCritter2) {
		int averageEnergy = 0;
		int numCrits = 0;
		for(Object obj : RajatCritter2) {
			Critter4 crit = (Critter4) obj;
			averageEnergy += crit.getEnergy();
			numCrits++;
		}
		averageEnergy /= numCrits;
		System.out.println("Average Critter Energy: " + averageEnergy);
	} */
	
	@Override
	public CritterShape viewShape() { return CritterShape.DIAMOND; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.GOLD; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.GOLD; }

}
