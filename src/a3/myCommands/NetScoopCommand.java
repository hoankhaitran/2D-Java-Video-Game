/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.Animals;
import a3.Fish;
import a3.Game;
import a3.GameObject;
import a3.GameWorld;
import a3.Net;
import a3.Shark;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.AbstractAction;

/**
 *
 * @author FXSSD
 */
public class NetScoopCommand extends AbstractAction {

    GameWorld gw;
    Net player;

    public NetScoopCommand(GameWorld gw) {
        super("Scoop Net");
        this.gw = gw;
        player = gw.getNet();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        scoopNet();
        
    }

    

    

    public void scoopNet() {

        int sharkCount;
        int fishCount;
        Iterator itr = gw.getObjectIterator();
        if (player.getScooping() == true) {
            sharkCount = 0; //reset count for new scoop
            fishCount = 0;
            if(gw.isSound()){
                Game.SCOOP_AUDIO.play();
            }

            while (itr.hasNext()) {
                GameObject temp = (GameObject) itr.next();
                if (temp instanceof Animals) { // only scoop shark or fish
                    if (player.getBounds().contains(temp.getBounds())) {

                        if (temp instanceof Fish) {
                            Fish myFish = (Fish) temp;
                            gw.setFishInNetPoint(gw.getFishInNetPoint() + myFish.getSize());
                            fishCount++;
                            myFish.setLivable(false);
                        }

                        if (temp instanceof Shark) {
                            Shark myshark = (Shark) temp;
                            myshark.setLivable(false);
                            gw.setSharkInNetPoint(gw.getSharkInNetPoint() - Game.SHARK_DAMAGE);
                            sharkCount++;

                        }

                        itr.remove();
                        itr = gw.getObjectIterator();// reset the iterator until there is no catch
                    }
                }
            }
            player.setScooping(false);
            gw.setSharkNum(gw.getSharkNum() + sharkCount);
            gw.setTotalPoint(gw.getTotalPoint()+gw.getSharkInNetPoint());
            gw.setChanged();
            System.out.println("Scooped " + fishCount + " fishes  and " + sharkCount + " sharks");
        } else {
            System.out.println("You need to deliver the fishes in the net before doing another scoop");
        }

    }
}
