package lorann.game.model.element.motion;

import lorann.game.model.Direction;
import lorann.game.model.element.motionless.CrystalBall;
import lorann.game.model.element.motionless.Door;
import lorann.game.model.element.motionless.Purse;

public class Hero extends Mobile {
	int lastimgnum=0;
	boolean haveFireball=true;
	Direction[] anim = {Direction.DOWN, Direction.DOWNLEFT, Direction.LEFT, Direction.UPLEFT, Direction.UP, Direction.UPRIGHT, Direction.RIGHT, Direction.DOWNRIGHT};
	public Hero(int x, int y){
		super(x, y, "lorann_LEFT", 'H', Direction.LEFT);
	}
	/**
	 * this hero changes its image depending on the direction he's going.
	 * @param u it is important to notice that the direction changes before any call to the super.move method ( which makes
	 * the direction system used in this game useful )
	 */
	public void move(Direction u){
		direction = u;
		this.gameBoard.getHero().setImage("lorann_"+u);
		if(this.gameBoard.movePossible(this.nextX(), this.nextY())==true){
		super.move();
		} else if (this.gameBoard.getElement(this.nextX(), this.nextY()) instanceof Purse){
			this.gameBoard.getGrille()[nextX()][nextY()]=null;
			this.getGameBoard().score(+100);
			super.move();
		} else if (this.getGameBoard().getElement(this.nextX(), this.nextY())instanceof CrystalBall){
			this.gameBoard.getGrille()[nextX()][nextY()]=null;
			this.getGameBoard().setCrystal(-1);
			super.move();
		}
	}
	
	public void update(){
		int imgnum;
		if (lastimgnum==7){
			imgnum=0;
		} else {
			imgnum = lastimgnum+1;
		}
		this.gameBoard.getHero().setImage("lorann_"+anim[imgnum]);
		lastimgnum = imgnum;
	}
	
	public void castFire(){
		if(haveFireball==true){
		Fireball fireball = new Fireball(this.getX(), this.getY(), this.direction);
		this.gameBoard.addMobile(fireball);
		haveFireball=false;
		}
	}
	
	public void contact(){
		if (this.getGameBoard().getElement(this.getX(), this.getY())instanceof Door && this.gameBoard.getCrystalsUp()==0){
			System.out.println("Well played !!");
			System.out.println("score : " + this.getGameBoard().getScore());
			System.exit(0);
		}
	}
}