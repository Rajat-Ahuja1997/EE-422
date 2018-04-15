package assignment5;

import java.util.List;

import assignment5.Algae;
import assignment5.Critter;
import assignment5.InvalidCritterException;
import assignment5.Params;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	
	public static List<Critter> getAllCritters(){
		return population;
	}
	
	
	
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected final String look(int direction, boolean steps) {
		int numSteps = 1;
		int x = this.x_coord;
		int y = this.y_coord;
		this.energy = Params.look_energy_cost - this.energy;
		if(steps) {
			numSteps = 2;
		}
		switch(direction) {
			case 3: x -= numSteps;
					y -= numSteps;
					break;
			case 2:
					y -= numSteps;
					break;
			case 1:
					y -= numSteps;
					x += numSteps;
					break;
			case 0:
					x += numSteps;
					break;
			case 7:
					x += numSteps;
					y += numSteps;
					break;
			case 6:
					y += numSteps;
					break;
			case 5:
					x -= numSteps;
					y += numSteps;
					break;
			case 4:
					x -= numSteps;
					break;
		}
		if(x < 0) {
			x = Params.world_width - 1;
		}
		if(x >= Params.world_width) {
			x = 0;
		}
		if(y < 0) {
			y = Params.world_height - 1;
		}
		if(y >= Params.world_height) {
			y = 0;
		}
		for(int i=0; i<population.size(); i++) {
			if((population.get(i).x_coord == x) && (population.get(i).y_coord == y)) {
				return population.get(i).toString();
			}
		}
		
		return null;
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	public int getX() {
		int x = this.x_coord;
		return x;
	}
	
	public int getY() {
		int y = this.y_coord;
		return y;
	}
	
	
	protected boolean isMoved;
	/**
	 * given an integer changes the x and y coordinate accordingly and also deducts set integer from energy variable
	 * @param direction an integer between 0 to 7 inclusive
	 */
	protected final void walk(int direction) {	//written on 03/19 s
		if(isMoved == false) {	
			switch(direction) {
				case 3: x_coord--;
						y_coord--;
						break;
				case 2:
						y_coord--;
						break;
				case 1:
						y_coord--;
						x_coord++;
						break;
				case 0:
						x_coord++;
						break;
				case 7:
						x_coord++;
						y_coord++;
						break;
				case 6:
						y_coord++;
						break;
				case 5:
						x_coord--;
						y_coord++;
						break;
				case 4:
						x_coord--;
						break;
			}
			if(x_coord < 0) {
				x_coord = Params.world_width - 1;
			}
			if(x_coord >= Params.world_width) {
				x_coord = 0;
			}
			if(y_coord < 0) {
				y_coord = Params.world_height - 1;
			}
			if(y_coord >= Params.world_height) {
				y_coord = 0;
			}
			isMoved = true; 
		}
		energy -= Params.walk_energy_cost;
	}
	
	/**
	 * given an integer changes the x and y coordinate twice accordingly and also deducts set integer from energy variable 
	 * @param direction an integer between 0 to 7 inclusive
	 */
	protected final void run(int direction) {	//written on 03/19
		if(isMoved == false) {
			for(int i = 0; i < 2; i++) {
				switch(direction) {
					case 3: x_coord--;
							y_coord--;
							break;
					case 2:
							y_coord--;
							break;
					case 1:
							y_coord--;
							x_coord++;
							break;
					case 0:
							x_coord++;
							break;
					case 7:
							x_coord++;
							y_coord++;
							break;
					case 6:
							y_coord++;
							break;
					case 5:
							x_coord--;
							y_coord++;
							break;
					case 4:
							x_coord--;
							break;
				}
				if(x_coord < 0) {
					x_coord = Params.world_width - 1;
				}
				if(x_coord >= Params.world_width) {
					x_coord = 0;
				}
				if(y_coord < 0) {
					y_coord = Params.world_height - 1;
				}
				if(y_coord >= Params.world_height) {
					y_coord = 0;
				}
				isMoved = true;
			}
		}
		energy -= Params.run_energy_cost;
	}
	
	/**
	 * creates another instance of the Critter class at location according to direction and places it into list
	 * @param offspring critter class type to be copied
	 * @param direction an integer between 0 to 7 inclusive
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy < Params.min_reproduce_energy) {
			return; 
		}
		offspring.energy = (this.energy/2);
		this.energy /= 2;
		switch(direction) {
			case 3: offspring.x_coord = x_coord--;
					offspring.y_coord = y_coord--;
					break;
			case 2:
					offspring.y_coord = y_coord--;
					break;
			case 1:
					offspring.y_coord = y_coord--;
					offspring.x_coord = x_coord++;
					break;
			case 0:
					offspring.x_coord = x_coord++;
					break;
			case 7:
					offspring.x_coord = x_coord++;
					offspring.y_coord = y_coord++;
					break;
			case 6:
					offspring.y_coord = y_coord++;
					break;
			case 5:
					offspring.x_coord = x_coord--;
					offspring.y_coord = y_coord++;
					break;
			case 4:
					offspring.x_coord = x_coord--;
					break;
		}
		if(x_coord < 0) {
			x_coord = Params.world_width - 1;
		}
		if(x_coord >= Params.world_width) {
			x_coord = 0;
		}
		if(y_coord < 0) {
			y_coord = Params.world_height - 1;
		}
		if(y_coord >= Params.world_height) {
			y_coord = 0;
		}
		
		babies.add(offspring);
	}
	
	/**
	 * Function which the critter will have it's characteristics and own operations
	 */
	public abstract void doTimeStep();
	/**
	 * Function to determine if critter wants to engage with another critter
	 * @param oponent the other critter to be engaged with
	 * @return True if critter will engage with other critter, false if critter runs away from other critter
	 */
	public abstract boolean fight(String oponent);
	
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	 /**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {		
			
			try {	
				Class<?> c = Class.forName(myPackage + "." + critter_class_name);
				Critter crit = (Critter) c.newInstance();
				crit.energy = Params.start_energy;				//sets initial energy of the critter
				crit.x_coord = getRandomInt(Params.world_width);	//sets initial x and y for the critter
				crit.y_coord = getRandomInt(Params.world_height);
				
				population.add(crit);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InvalidCritterException(critter_class_name);
			}
			
			
		}


	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {	
		List<Critter> result = new java.util.ArrayList<Critter>();
		Class<?> critClass;
		try {
			critClass = Class.forName(myPackage + "." + critter_class_name);
		} catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
		for(Critter crit: population) {
			if(critClass.isInstance(crit)) {
				//if (crit.getClass().getName().equalsIgnoreCase(critter_class_name)){
				result.add(crit);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static String runStats(List<Critter> critters) {
		String a = new String();
		a = "";
		System.out.print("" + critters.size() + " critters as follows -- ");
		a = "" + critters.size() + " critters as follow -- ";
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			a += prefix + s + ":" + critter_count.get(s);
			prefix = ", ";
		}
		System.out.println();		
		return a;
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}
	
	/**
	 * Check to see if critter has same location as another critter
	 * @param c the critter to be checked
	 * @return true if critter has same location as another critter, false if critter does not occupy same location as another critter
	 */
	public static boolean notValidSpot(Critter c) {
		for(int i=0; i<population.size(); i++) {
			if(population.get(i) == c) {
				continue;
			}
			if((population.get(i).x_coord == c.x_coord) && (population.get(i).y_coord == c.y_coord)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Simulates one time step for every critter in the collection. 
	 * This also simulates encounters between critters, set energy cost, generate algae at end, 
	 * move babies into population, and clear dead critters from collection.
	 */
	public static void worldTimeStep() { 
		for(Critter crit: population) {
			crit.doTimeStep();
			crit.isMoved = false;
		}
		
		for(int i = 0; i < population.size(); i++) {
			for(int j = i + 1; j < population.size(); j++) {
				
				if((population.get(i).x_coord == population.get(j).x_coord) && (population.get(i).x_coord == population.get(j).y_coord)) {
					if((population.get(i).energy > 0) && (population.get(j).energy > 0)) {
						int aX = population.get(i).x_coord;	//a is first critter
						int aY = population.get(i).y_coord;
						boolean aFightB = population.get(i).fight(population.get(j).toString());
						if(notValidSpot(population.get(i))) {	//checks if ran away to an occupied spot
							population.get(i).x_coord = aX;
							population.get(i).y_coord = aY;
						}
						
						int bX = population.get(j).x_coord; //b is second critter
						int bY = population.get(j).y_coord;
						boolean bFightA = population.get(j).fight(population.get(i).toString());
						if(notValidSpot(population.get(j))) {	//checks if ran away to an occupied spot
							population.get(j).x_coord = bX;
							population.get(j).y_coord = bY;
						}
						
						if((population.get(i).x_coord == population.get(j).x_coord) && (population.get(i).x_coord == population.get(j).y_coord)) {
							//rechecks if critters are in same position after fight/running away
							if((population.get(i).energy > 0) && (population.get(j).energy > 0)) { //checks if critters are alive
								int aRoll = 0;
								int bRoll = 0;
								if(aFightB == true) {
									aRoll = Critter.getRandomInt(population.get(i).energy);
								}
								if(bFightA == true) {
									bRoll = Critter.getRandomInt(population.get(j).energy);
								}
								if(aRoll >= bRoll) { //setting case of both rolls are same aka first critter wins
									population.get(i).energy += ((population.get(j).energy)/2);
									population.get(j).energy = 0;
								}
								else if(bRoll > aRoll) {
									population.get(j).energy += ((population.get(i).energy)/2);
									population.get(i).energy = 0;
								}
							}
						}
					}
				}
			}
		}
		
		//rest energy
		for(Critter crit: population) {
			crit.energy -= Params.rest_energy_cost;
		}
		
		//generating algae
		for(int i=0; i<Params.refresh_algae_count; i++) {
			Algae newAlgae = new Algae();
			newAlgae.setY_coord(rand.nextInt(Params.world_height));
			newAlgae.setX_coord(rand.nextInt(Params.world_width));
			newAlgae.setEnergy(Params.start_energy);
			population.add(newAlgae);
		}
		
		//moving babies to population
		population.addAll(babies);
		babies.clear();
		
		//clearing dead critters
		for(int i=0; i<population.size(); i++) {
			if(population.get(i).energy <= 0) {
				population.remove(i);
			}
		}
		displayWorld();
		runStats(population);
	}
	
	public static void displayWorld() {
		Main.paintGrid();
	} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	
}