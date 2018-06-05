package lorann.game.model;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import lorann.game.model.element.Element;
import lorann.game.model.element.Permeability;
import lorann.game.model.element.motion.Hero;
import lorann.game.model.element.motion.Mobile;
import lorann.game.model.element.motion.MotionList;
import lorann.game.model.element.motionless.CrystalBall;
import lorann.game.model.element.motionless.Door;
import lorann.game.model.element.motionless.MotionlessList;


/**
 * This is the heart of our Model.
 * For the variables we have a 2D array of Elements defined by nbCol and nbLine
 * One ArrayList named mobileList containing all the mobile elements. 
 * The hero and the door are special elements limited to one by level, they're referenced here for a specific use in other classes.
 * crystalsUp stands for the remaining crystals in the level when they reach 0, the door will open.
 * 
 * @author Deqth
 *
 */
public class GameBoard implements IGameBoard {
	
	private int nbCol; //nombre de colonnes de notre grille de jeu.
	private int nbLine; // nombre de lignes de notre grille de jeu.
	public Element board[][]; // La grille contenant les éléments du jeu.
	private ArrayList<Mobile> mobileList; //Liste de tout les éléments mobiles du jeu.
	private Hero hero;
	private Door door;
	private int crystalsUp;
	int score;
	
/**
 * This is the first constructor of this GameBoard class, using ints to set up.
 * 
 * 
 * @param nbLine number of lines for our grid
 * @param nbCol number of columns for our grid
 * @param xHero the initial x for the hero
 * @param yHero the initial y for the hero
 * @param xGate the x for the gate
 * @param yGate the y for the gate
 */
	public GameBoard(int nbLine, int nbCol, int xHero, int yHero, final int xGate, final int yGate){
		
		this.nbCol = nbCol;//
		this.nbLine = nbLine;//
		this.board = new Element[nbCol][nbLine];
		this.mobileList = new ArrayList<Mobile>();
		this.hero = new Hero(xHero, yHero);
		this.door = new Door();
		board[xGate][yGate] = door;
		this.addMobile(hero);
	}
	
/**
 * Second constructor of our GameBoard, this one takes a formatted .txt file. Using or not a DB as means to get it. 
 * @param fileName
 * @throws IOException
 */
	public GameBoard(String fileName) throws IOException{
		int xTemp;
		int yTemp;
		final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("res/niveau/"+fileName+".txt")));
		String line;
		int numLine = 0;
		
		line = buffer.readLine(); 
		nbCol = Integer.parseInt(line);
		line = buffer.readLine();
		nbLine = Integer.parseInt(line);
		line = buffer.readLine();
		xTemp = Integer.parseInt(line);
		line = buffer.readLine();
		yTemp = Integer.parseInt(line);
		
		this.board = new Element[nbCol][nbLine];
		this.mobileList = new ArrayList<Mobile>();

		this.hero = new Hero(xTemp,yTemp);
		this.addMobile(hero);
		
		line = buffer.readLine();
		xTemp = Integer.parseInt(line);
		line = buffer.readLine();
		yTemp = Integer.parseInt(line);
		
		this.door = new Door();
		board[xTemp][yTemp] = door;
		
		while ((line = buffer.readLine()) != null) {
			for (int i = 0; i < nbCol; i++) {
				if(MotionlessList.getFromSymbol(line.toCharArray()[i])!=null){
					if(MotionlessList.getFromSymbol(line.toCharArray()[i])instanceof CrystalBall){
						this.setCrystal(+1);
					}
				this.insertElement(i, numLine, MotionlessList.getFromSymbol(line.toCharArray()[i])); 
				} else if (MotionlessList.getFromSymbol(line.toCharArray()[i])==null && MotionList.getFromSymbol(line.toCharArray()[i])!= null){
					this.addMobile(MotionList.getFromSymbol(line.toCharArray()[i]));
					MotionList.getFromSymbol(line.toCharArray()[i]).setX(i);
					MotionList.getFromSymbol(line.toCharArray()[i]).setY(numLine);
				}
			}
			numLine++;
		}
		buffer.close();
	}
	
	/**
	 * this is the method that let us insert Elements into our Elements[][] GameBoard.
	 */
	public void insertElement(int x, int y, Element element){
		
		if(y>this.nbLine || x> this.nbCol || x<0 || y<0){
			System.out.println("Erreur de placement");
			return;
		} else {
			if(board[x][y] == null){
				element.setGameBoard(this);
				board[x][y] = element;
			} else {
				System.out.println("Zone occupée");
			}
		}
	}
	
	public int getCrystalsUp() {
		return crystalsUp;
	}

	public Element getElement(int x, int y){
		return board[x][y];		
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public int getNbCol() {
		return nbCol;
	}
	public int getNbLine() {
		return nbLine;
	}
	
	public Element[][] getGrille(){
		return this.board;
	}
	
	//////Gestion des Mobiles
	///Ajout d'un mobile à la liste de mobiles.
	public void addMobile(Mobile mobile){
		this.mobileList.add(mobile);
		mobile.setGameBoard(this);
	}
	
	///Suppression d'un mobile de la liste.
	public void deleteMobile(Mobile mobile){
		this.mobileList.
		remove(mobile);
	}
	
	///Renvois la liste de mobiles
	public ArrayList<Mobile> getMobileList(){
		return this.mobileList;
	}
	
	
/**
 * This method will check if an x, y coordinate on the board is possible to move to.
 * First by checking if it's outside of our grid. Second it will check the Element's Permeability.
 */
	public boolean movePossible(int x, int y){
		if((x>this.nbCol || y>this.nbLine || x<0 || y<0)){
			return false;
		} else if (this.getElement(x, y)!=null && this.getElement(x, y).getPerm()==Permeability.BLOCK) {
			return false;
		} else {
			return true;
		}
	}
	
	
/**
 * Quiet self explanatory, this moves mobiles.
 */
	public void moveMobile(Mobile mobile){
		mobile.setX(mobile.nextX());
		mobile.setY(mobile.nextY());
	}

	/**
	 * Method that let us modify the score.
	 */
	public void score(int i) {
		score += i;
	}
	public int getScore(){
		return score;
	}
	
	/**
	 * This method will trigger the door opening at each level.
	 * At the instantiation of each crystal the crystalsUp method gets incremented and each time a hero gets a Crystal, it decrements
	 * once it reaches 0 the door opens.
	 */
	public void setCrystal(int i){
		this.crystalsUp += i;
		if (this.crystalsUp==0){
			door.setImage("gate_open");
			door.setPerm(Permeability.PASS);
		}
	}
}
