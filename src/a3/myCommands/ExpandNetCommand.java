/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.GameWorld;
import a3.GameWorldProxy;
import a3.Net;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author hoantran
 */
public class ExpandNetCommand extends AbstractAction{
GameWorld gw;
    public ExpandNetCommand(GameWorld gw){
        super("Expand Net");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Net myNet = gw.getNet();
        myNet.expandNet();
    }
    
}
