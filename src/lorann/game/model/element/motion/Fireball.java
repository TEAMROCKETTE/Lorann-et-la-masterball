package lorann.game.model.element.motion;

import lorann.game.model.Direction;


public class Fireball extends Mobile {
	int lastimgnum=1;
	public Fireball(int x, int y, Direction u){
		super(x, y, "fireball_1", 'F', u);
	}
	/**
	 * Handles the animation and the movements of the Fireball
	 */
	public void update(){
	
		/**/////////////////Animation/////////////////////////
		/**/	int imgnum;									//
		/**/	if (lastimgnum==5){							//
		/**/		imgnum=1;								//
		/**/	} else {									//
		/**/		imgnum = lastimgnum+1;					//
		/**/	}											//
		/**/	this.setImage("fireball_"+imgnum);			//
		/**/	lastimgnum = imgnum;						//
		//////////////////////////////////////////////////////
		
		
		if(this.getGameBoard().getElement(this.nextX(), this.nextY())==null){
		super.move();
		} else if(this.getGameBoard().getElement(this.nextX(), this.nextY())!=null) {
			switch(direction){
				case UP:
					direction = Direction.DOWN;
					break;
				case UPLEFT:
					diagCheck(Direction.DOWNRIGHT, Direction.DOWNLEFT, Direction.UPRIGHT);
					break;
				case LEFT:
					direction = Direction.RIGHT;
					break;
				case DOWNLEFT:
					diagCheck(Direction.UPRIGHT, Direction.UPLEFT, Direction.DOWNRIGHT);
					break;
				case DOWN:
					direction = Direction.UP;
					break;
				case DOWNRIGHT:
					diagCheck(Direction.UPLEFT, Direction.UPRIGHT, Direction.DOWNLEFT);
					break;
				case RIGHT:
					direction = Direction.LEFT;
					break;
				case UPRIGHT:
					diagCheck(Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.UPLEFT);
					break;
			}
			super.move();
		}
	}
	
	/**
	 * this method will handle diagonal rebounces of the fireball
	 * @param noXnoY the direction of the Fireball if there is an obstacle in her nextX and next Y
	 * @param noY the direction of the Fireball if there is an obstacle in her next Y only
	 * @param noX the direction of the Fireball if there is an obstacle in her next X only
	 */
	public void diagCheck(Direction noXnoY, Direction noY, Direction noX){
		if(this.getGameBoard().movePossible(this.getX(), this.nextY())==false 
				&& this.getGameBoard().movePossible(this.nextX(), this.getY())==false){
			direction = noXnoY;
		}
		else if(this.getGameBoard().movePossible(this.getX(), this.nextY())==false) {
			direction = noY;
		}
		else if(this.getGameBoard().movePossible(this.nextX(), this.getY())==false) {
			direction = noX;
		}
	}
	
	/**
	 * The Fireball will check it is in contact with a mobile and act in consequence
	 * if it's a hero give him back the ability to shoot a Fireball
	 * if it's a monster, gives back the ability to shoot a Fireball and kills the monster.
	 */
	public void contact(){
			for (Mobile mobile : this.getGameBoard().getMobileList()){
				if((mobile instanceof Hero && this.getX()==mobile.getX() && this.getY()==mobile.getY()) || (mobile instanceof Hero && this.nextX()==mobile.getX() && this.nextY()==mobile.getY())){
					this.getGameBoard().getHero().haveFireball = true;
					this.alive=false;
				}
				else if ((mobile instanceof Monster && this.getX()==mobile.getX() && this.getY()==mobile.getY())||(mobile instanceof Monster && this.nextX()==mobile.getX() && this.nextY()==mobile.getY())){
					mobile.alive=false;
					this.getGameBoard().getHero().haveFireball = true;
					this.alive=false;
				}
			}
		
	}
}

