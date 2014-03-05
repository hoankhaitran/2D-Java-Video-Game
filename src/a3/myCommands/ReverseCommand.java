/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.Animals;
import a3.Fish;
import a3.Game;
import a3.GameObject;
import a3.GameWorldProxy;
import a3.Net;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.AbstractAction;

/**
 *
 * @author FXSSD
 */
public class ReverseCommand extends AbstractAction {

    GameWorldProxy gwp;

    public ReverseCommand(GameWorldProxy gwp) {
        super("Reverse");
        this.gwp = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Iterator itr = gwp.getObjectIterator();
       while(itr.hasNext()){
           GameObject obj = (GameObject)itr.next();
           if(obj instanceof Fish){
               Fish f = (Fish) obj;
               if(f.isSelected()){
                   f.setDeltaX(-f.getDeltaX());
                   f.setDeltaY(-f.getDeltaY());
               }
           }
       }
    }
}
