package cpsc475TermProject;
import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;

import java.util.List;
import java.util.logging.*;

public class World extends Environment {

    private Logger logger = Logger.getLogger("CPSC475 Project - Davis Goulet and Riley Wium"+World.class.getName());
    
    private static final Literal moveLeft = Literal.parseLiteral("left");
    private static final Literal moveRight = Literal.parseLiteral("right");
    private static final Literal moveUp = Literal.parseLiteral("up");
    private static final Literal moveDown = Literal.parseLiteral("down");
    
    Model model;
    View view;
    
    /** Constructor that initializes the multiagent project. */
    @Override
    public void init(String[] args) {
        super.init(args);
        model = new Model();
        view = new View(model);
        model.setView(view);
    }

    /** Interprets the agents actions and calls the appropriate function in the model **/
    @Override
    public boolean executeAction(String agName, Structure action) {
    		System.out.println(agName);
    		boolean actionAccepted = false;	
    		//Interpret the agents name as a unique id. Default is -1 representing that the agent doesn't exist.
    		int agentID = -1;
    		if (agName.equals("villager1")) agentID = 0;
    		else if (agName.equals("villager2")) agentID = 1;
    		else if (agName.equals("villager3")) agentID = 2;
    		else if (agName.equals("herbivore1")) agentID = 3;
    		else if (agName.equals("herbivore2")) agentID = 4;
    		else if (agName.equals("herbivore3")) agentID = 5;
        
        //If the agentsID is still -1, then it hasn't been added so fail the action and log it.
        if(agentID == -1) {
        		logger.info("Agents name cannot be interpreted. Recieved: "+agName);
        		return false;
        }
        
        //Interpret the agents action.
	    	if (action.equals(moveLeft))
	    		actionAccepted = model.moveLeft(agentID);
	    	else if (action.equals(moveRight))
	    		actionAccepted = model.moveRight(agentID);
	    	else if (action.equals(moveUp))
	    		actionAccepted = model.moveUp(agentID);
	    	else if (action.equals(moveDown))
	    		actionAccepted = model.moveDown(agentID);
	    	else
	    		logger.info("executing: "+action+", but not implemented!");
    	
	    	if (actionAccepted) {
	    		updatePercepts();
	    		try { Thread.sleep(100); } catch (InterruptedException x) { }
	    	}
	    	
	    	return actionAccepted;
    }
	
    /** Stops the simulation **/
	public void stop() {
        super.stop();
    }
	
	/** Updates the agents perceptions from the environment **/
	private void updatePercepts()  {
    		clearAllPercepts();
		
		Location loc = model.getAgPos(0);
	    	addPercept("washer", Literal.parseLiteral("position(" + loc.x + ", " + loc.y +")"));
	    	
	    	Location locB = model.getAgPos(1);
	    	addPercept("drier", Literal.parseLiteral("position(" + locB.x + ", " + locB.y +")"));
			
	}
}