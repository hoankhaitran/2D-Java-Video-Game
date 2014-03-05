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
public class SaveMenuItem extends AbstractAction {

    public SaveMenuItem() {
        super("Save");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null, "Saved");
        System.out.println("Save");

    }
}
