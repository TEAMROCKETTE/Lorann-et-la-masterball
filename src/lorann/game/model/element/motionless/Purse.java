package lorann.game.model.element.motionless;

import lorann.game.model.element.Permeability;
import lorann.game.model.element.motion.Hero;
import lorann.game.model.element.motion.Mobile;

public class Purse extends Motionless {
	
	public Purse(){
		super("purse", 'G', Permeability.BLOCK);
	}
	/**
	 * If the purse is touch
	 * @return if is touch by hero it earn 10 points and return true
	 * else return false
	 */
	public boolean onTouch() {
		for (Mobile mobile : this.getGameBoard().getMobileList()){
		if( mobile instanceof Hero) {
			return true;
		}
	}
		return false;
}
}
