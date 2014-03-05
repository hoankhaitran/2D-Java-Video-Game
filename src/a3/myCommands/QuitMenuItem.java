/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author hoantran
 */
public class QuitMenuItem extends AbstractAction{
    public QuitMenuItem(){
        super("Quit");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        int ret = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Exit", JOptionPane.YES_NO_OPTION);
        if(ret ==JOptionPane.YES_OPTION)
        System.exit(0);
    }
    
}
