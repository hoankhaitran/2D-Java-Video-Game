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
public class AboutMenuItem extends AbstractAction{
    public AboutMenuItem(){
        super("About");
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null, "ABOUT");
        System.out.println("About");
    }
    
}
