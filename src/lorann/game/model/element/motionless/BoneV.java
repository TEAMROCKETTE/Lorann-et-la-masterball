package lorann.game.model.element.motionless;

import lorann.game.model.element.Permeability;

public class BoneV extends Motionless {
	
	public BoneV(){
		super("vertical_bone", '|', Permeability.BLOCK);
	}
}
