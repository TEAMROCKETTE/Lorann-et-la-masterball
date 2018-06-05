package lorann.game.model.element.motion;

import lorann.game.model.Direction;
import lorann.game.model.element.Element;
import lorann.game.model.element.Permeability;
/**
 * This class is almost the same as the Element class, it extends it
 * and is thus likely, the main difference is that the mobile class isn't stored in the Board[][]
 * but in an arrayList, so we need to have an X and an Y in order to pain them or to use them in the grid.
 * Also each Mobile have a direction he is facing ( it will mostly be used in movements )
 * the boolean alive is here for the sole purpose of deleting the "dead" elements.
 * 
 * @author Game
 *
 */
public abstract class Mobile extends Element {
	
	private int x;
	private int y;
	public Direction direction;
	public boolean alive;
	
	public Mobile(int x, int y, String imageName, char c, Direction u){
		super(imageName, c, Permeability.PASS);
		this.x=x;
		this.y=y;
		this.direction=u;
		this.alive=true;
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void move(){
		this.gameBoard.moveMobile(this);
	}
	/**
	 * @return the next X depending on the Direction.
	 */
	public int nextX(){
		if (direction == Direction.LEFT || direction == Direction.UPLEFT || direction == Direction.DOWNLEFT){
			return getX()-1;
		} else if (direction == Direction.RIGHT|| direction == Direction.UPRIGHT || direction == Direction.DOWNRIGHT){
			return getX()+1;
		}
		return getX();
	}
	
	/**
	 * @return the next Y depending on the Direction.
	 */
	public int nextY(){
		if (direction == Direction.UP ||direction == Direction.UPLEFT || direction == Direction.UPRIGHT){
			return getY()-1;
		} else if (direction == Direction.DOWN || direction == Direction.DOWNLEFT || direction == Direction.DOWNRIGHT){
			return getY()+1;
		}
		return getY();
	}
	
	/**
	 * this method is used each X milliseconds in a loop of the controller.
	 */
	public abstract void update();
	
	/**
	 * this checks collisions and interactions after the update 
	 */
	public abstract void contact();
	
}
