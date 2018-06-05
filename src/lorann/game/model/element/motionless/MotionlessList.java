package lorann.game.model.element.motionless;


/**
 * this class serves the purpose of instantiating a new motionless element depending on the char  it's method recieves.
 * @author Game
 *
 */
	public abstract class MotionlessList {
		public static final Motionless	BONE = new Bone();
		public static final Motionless	BONEV = new BoneV();
		public static final Motionless	BONEH = new BoneH();
		public static final Motionless CRYSTALBALL = new CrystalBall();
		public static final Motionless PURSE = new Purse();
		

		private static Motionless	motionlessList[]	= { BONE, BONEV, BONEH, CRYSTALBALL, PURSE };

		
		/**
		 * for each Motionless of our motionlessList[], it tries to find a match between fileSymbol and the char of each element.
		 * @param fileSymbol is a character that each element has.
		 * @return the instantiated new Element.
		 */
		public static Motionless getFromSymbol(char fileSymbol) {
			for (final Motionless motionless : motionlessList) {
				if (motionless.getC() == fileSymbol) {
					return motionless;
				}
			}
			return null;
		}
	}