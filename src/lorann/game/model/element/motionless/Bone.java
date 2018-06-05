package lorann.game.model.element.motionless;

import lorann.game.model.element.Permeability;

public class Bone extends Motionless{
	
	public Bone(){
		super("bone", '*', Permeability.BLOCK);
	}
}
