package lorann.game.controller;

import java.util.Iterator;

import lorann.game.Lorann;
import lorann.game.model.Direction;
import lorann.game.model.IGameBoard;
import lorann.game.model.element.motion.*;
import lorann.game.view.LorannFrame;

public class LorannController {
	private IGameBoard gameBoard;
	private LorannFrame frame;
	
	public LorannController(IGameBoard gameBoard, LorannFrame frame){
		this.gameBoard = gameBoard;
		this.frame = frame;
	}

	public IGameBoard getGameBoard() {
		return gameBoard;
	}

	public LorannFrame getFrame() {
		return frame;
	}
	
	
	/**
	 * this method will get the key actually pressed in the keyBoard and execute the orders related
	 * then it will call the update method in each and every mobile of the GameBoard arraylist.
	 * And then it will check interractions after update.
	 * Finally, it will repaint the Panel.
	 */
	public void updateModel(){
		getKeyboard();
		for(Mobile mobile : gameBoard.getMobileList()){
			mobile.update();
			mobile.contact();
		}
		refreshScreen();
	}
	
	/**
	 *This method uses an Iterator in order to remove mobiles from mobileList of GameBoard. ( since the mobileList is used in a loop )
	 */
	public void colideModel(){
		for( Iterator<Mobile> iter = gameBoard.getMobileList().iterator(); iter.hasNext(); ){
			Mobile mobile = iter.next();
			if(mobile.alive==false){
				iter.remove();
			}
		}
		refreshScreen();
	}
	
	
	/**
	 * this is the user's order to the hero
	 * @param dir The direction we want our hero to go to.
	 */
	public void order(Direction dir){
		if(System.currentTimeMillis()-Lorann.lastTime==150){
		this.getGameBoard().getHero().move(dir);
		refreshScreen();
		Lorann.lastTime = System.currentTimeMillis();
		}
	}
	/**
	 * @param s just some commands, actually only "fireball" is accepted
	 */
	public void order(String s){
			if (s=="fireball"){
				this.getGameBoard().getHero().castFire();
			}
	}

	public void refreshScreen(){
		this.getFrame().repaint();
	}
	
	/**
	 * gets the booleans from LorannFrame to know which keys are pressed and acts accordingly.
	 * It gives userorders.
	 */
	public void getKeyboard(){
	if(this.getFrame().pressedUp==true){
		if(this.getFrame().pressedLeft==true){
			order(Direction.UPLEFT);
		} else if(this.getFrame().pressedRight==true){
			order(Direction.UPRIGHT);
		} else {
			order(Direction.UP);
		}
	} else if (this.getFrame().pressedDown==true){
		if(this.getFrame().pressedLeft==true){
			order(Direction.DOWNLEFT);
		} else if(this.getFrame().pressedRight==true){
			order(Direction.DOWNRIGHT);
		} else {
			order(Direction.DOWN);
		}
	} else if (this.getFrame().pressedUp==false && this.getFrame().pressedDown==false){
		if(this.getFrame().pressedLeft==true){
			order(Direction.LEFT);
		} else if (this.getFrame().pressedRight==true){
			order(Direction.RIGHT);
		} else if (this.getFrame().pressedFire==true) {
			order("fireball");
		}
	}
	}
	
}
