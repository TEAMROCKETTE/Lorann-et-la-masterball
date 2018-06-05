package lorann.game;

import lorann.game.controller.LorannController;
import lorann.game.model.GameBoard;
import lorann.game.model.IGameBoard;
import lorann.game.view.LorannFrame;

public class Lorann{
    public static long lastTime;
    IGameBoard gameBoard;
    LorannFrame frame;
    
    public Lorann(int map) throws Exception{
        if(map == 0 || map >7){
            System.out.println(map);
            throw new Exception("error level");
        }
        else{
            IGameBoard gameBoard = new GameBoard("lvl" + map);
            LorannFrame frame = new LorannFrame(gameBoard);
            LorannController controller = new LorannController(gameBoard, frame);
            frame.setController(controller);
            
            while (true){
                if(System.currentTimeMillis()-lastTime>=150){
                        controller.updateModel();
                        controller.colideModel();
                        lastTime = System.currentTimeMillis();
                }
            }
        }
    }
}