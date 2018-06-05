package lorann.game.model.element;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lorann.game.model.GameBoard;
import lorann.game.model.IGameBoard;

/**
 * The Element class. They are similar to the pieces of a ChessBoard.
 * We will put them inside the GameBoard's gameBoard[][] to create a level.
 * 
 * Each Element of this game has an Image, a permeability and a GameBoard he is in.
 * as well as a character that will help us use a fileText to be used by this code in order to generate a level
 * (instantiating different Elements depending on the character)
 * 
 *  
 * @author Game
 */
public abstract class Element {

	protected IGameBoard gameBoard;
	private BufferedImage image;
	protected Permeability perm;
	protected char c;
	protected int habs = 0;
	
	public Element(String imageName, char c, Permeability perm){
		this.c=c;
		this.perm = perm;
		setImage(imageName);
	}
	
	public Permeability getPerm(){
		return this.perm;
	}
	
	public BufferedImage getImage(){
		return this.image;
	}
	
	public void setImage(String imageName) {
		try {
			this.image = ImageIO.read(new File("res/image/" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPerm(Permeability perm) {
		this.perm = perm;
	}

	public IGameBoard getGameBoard(){
		return this.gameBoard;
	}
	
	public void setGameBoard(GameBoard gameBoard){
		this.gameBoard = gameBoard;
	}

	public char getC() {
		return c;
	}
}
