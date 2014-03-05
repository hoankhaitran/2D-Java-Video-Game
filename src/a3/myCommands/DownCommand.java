/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.Game;
import a3.GameWorldProxy;
import a3.Net;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author FXSSD
 */
public class DownCommand extends AbstractAction {

    GameWorldProxy gwp;

    public DownCommand(GameWorldProxy gwp) {
        super("Move Down");
        this.gwp = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Net player = gwp.getNet();
       player.setLocationY(player.getLocationY()+Game.NET_SPEED);
       player.move();
       System.out.println("Move down");
    }
}
