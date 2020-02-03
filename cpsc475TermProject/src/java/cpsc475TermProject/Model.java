package cpsc475TermProject;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model extends GridWorldModel {

	public static final int PLANT = 8;
	public static final int VILLAGE = 16;
	private static final int GRID_WIDTH = 31;
	private static final int GRID_HEIGHT = 31;
	
	private static final Location villageLoc = new Location(15,15);
	
	private Random r = new Random();
	
	public Model() {
		super(GRID_WIDTH, GRID_HEIGHT, 6);
		//Set the villager locations to the village.
		setAgPos(0, villageLoc);
		setAgPos(1, villageLoc);
		setAgPos(2, villageLoc);
		
		//These are here to temporarily set their positions to somewhere else. REMOVE AT LATER DATE.
		setAgPos(0, 5,5);
		setAgPos(1, 6,5);
		setAgPos(2, 7,5);
		
		//Randomly place the herbivore's in the environment as long as its not the village.
		for(int i=3;i<6;i++) {
			int x = r.nextInt(31);
			int y = r.nextInt(31);
			//If a herbivore is placed on the village, try again.
			if(villageLoc.x==x && villageLoc.y==y) {
				i--;
				continue;
			}
			else {
				setAgPos(i,x,y);
			}
		}
		
		set(VILLAGE,villageLoc.x, villageLoc.y);
		set(PLANT,10,10);
	}

	boolean moveLeft(int agentId) {
		Location l = getAgPos(agentId);
		if (l.x == 0)
			return false;
		l.x--;
		setAgPos(agentId, l);
		return true;
	}
	
	boolean moveRight(int agentId) {
		Location l = getAgPos(agentId);
		if (l.x >= GRID_WIDTH - 1)
			return false;
		l.x++;
		setAgPos(agentId, l);
		return true;
	}
	
	boolean moveUp(int agentId) {
		Location l = getAgPos(agentId);
		if (l.y == 0)
			return false;
		l.y--;
		setAgPos(agentId, l);
		return true;
	}
	
	boolean moveDown(int agentId) {
		Location l = getAgPos(agentId);
		if (l.y >= GRID_HEIGHT - 1)
			return false;
		l.y++;
		setAgPos(agentId, l);
		return true;
	}
}