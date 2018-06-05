package lorann.game.model.element.motion;

import lorann.game.model.Direction;


public class Monster extends Mobile {

	boolean refreshUpdate;
	public Monster(int x, int y, Direction u, char c){
		super(x, y, "monster_"+c, c, u);
	}

	public void searchHero(){
		if (this.getGameBoard().getHero().getX() == this.getX()){
			if (this.getGameBoard().getHero().getY() < this.getY()){
				this.direction = Direction.UP;
			} else if (this.getGameBoard().getHero().getY() > this.getY()){
				this.direction = Direction.DOWN;
			}
		} else if (this.getGameBoard().getHero().getX() < this.getX()){
			if (this.getGameBoard().getHero().getY() < this.getY()){
				this.direction = Direction.UPLEFT;
			} else if (this.getGameBoard().getHero().getY() > this.getY()){
				this.direction = Direction.DOWNLEFT;
			} else if (this.getGameBoard().getHero().getY() == this.getY()){
				this.direction = Direction.LEFT;
			}
		} else if (this.getGameBoard().getHero().getX() > this.getX()){
			if (this.getGameBoard().getHero().getY() < this.getY()){
				this.direction = Direction.UPRIGHT;
			} else if (this.getGameBoard().getHero().getY() > this.getY()){
				this.direction = Direction.DOWNRIGHT;
			} else if (this.getGameBoard().getHero().getY() == this.getY()){
				this.direction = Direction.RIGHT;	
			}
		}
			
	}
	
	
	public void update(){
		if(refreshUpdate==true){
		searchHero();
		refreshUpdate=false;
		} else {
		if(this.gameBoard.movePossible(this.nextX(), this.nextY())==true){
			super.move();
		}
		refreshUpdate=true;
		}
	}

	@Override
	public void contact() {
		int i = 0;
		for (Mobile mobile : this.getGameBoard().getMobileList()){
			//if(mobile instanceof Monster && this.getX()!=mobile.getX() && this.getY()!=mobile.getY()){
				if(mobile instanceof Hero && this.getX()==this.gameBoard.getHero().getX() && this.getY()==this.gameBoard.getHero().getY()){
					this.gameBoard.getHero().alive=false;
					i = 1;		
		
					if (i>0){
						System.out.println("You loose");
						System.exit(0);
					}
				}
			}
		//}
	}
}
