/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.Fish;
import a3.Game;
import a3.GameObject;
import a3.GameWorld;
import a3.Net;
import a3.Shark;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author FXSSD
 */
public class TickCommand extends AbstractAction {

    private GameWorld gw; // this for adding random shark and food because proxy does not support any modification

    public TickCommand(GameWorld gw) {
        super("Pause");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int gameClock = gw.getGameClock();
        Iterator myObjectIterator = gw.getObjectIterator();
        while (myObjectIterator.hasNext()) {
            GameObject temp = (GameObject) myObjectIterator.next();

            if (temp instanceof Net) {
                ((Net) temp).move(); 
            }
            if (temp instanceof Fish) { // move fish

                ((Fish) temp).move(Game.DELAY_IN_MS);
            }
            if (temp instanceof Shark) {// move shark
                ((Shark) temp).move(Game.DELAY_IN_MS);

            }
        }
        gw.randomShark();
        gw.randomFood();
        gameClock -= Game.DELAY_IN_MS;
        gw.setGameClock(gameClock);
        if (gameClock <= 0) {
		  		Game.BACKGROUND_AUDIO.stop();
            Game.gameTimer.stop();
            JOptionPane.showMessageDialog(null, "GAME OVER !");
            System.exit(0);
        }

        gw.checkFishCollision();
        gw.checkFishAndSharkCollision();
        gw.checkFishAndFoodCollision();
        gw.checkNetAndBarCollision();
        gw.checkFishSharkAndBar();
        gw.cleanUp();

        if (gw.getFishCount() == 0) {
		  		Game.BACKGROUND_AUDIO.stop();
            Game.gameTimer.stop();
            JOptionPane.showMessageDialog(null, "No more fish game over. Your total point is " + gw.getTotalPoint());
            System.exit(0);

        }
        gw.setChanged();

    }

   
}
