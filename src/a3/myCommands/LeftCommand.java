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
public class LeftCommand extends AbstractAction {

    GameWorldProxy gwp;

    public LeftCommand(GameWorldProxy gwp) {
        super("Move Left");
        this.gwp = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Net player = gwp.getNet();
       player.setLocationX(player.getLocationX()-Game.NET_SPEED);
       player.move();
       System.out.println("Move left");
    }
}
