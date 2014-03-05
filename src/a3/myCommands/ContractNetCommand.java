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
public class ContractNetCommand extends AbstractAction {
    GameWorld gw;
    public ContractNetCommand(GameWorld gw){
        super("Contract Net");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Net myNet = gw.getNet();
        myNet.contractNet();
    }
    
}
