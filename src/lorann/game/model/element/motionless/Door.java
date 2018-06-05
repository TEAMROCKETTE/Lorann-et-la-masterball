package lorann.game.model.element.motionless;

import lorann.game.model.element.Permeability;

public class Door extends Motionless {
	
	public Door(){
		super("gate_closed", 'D', Permeability.BLOCK);
	}
}
