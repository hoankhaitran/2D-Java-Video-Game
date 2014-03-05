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
public class UndoMenuItem extends AbstractAction {

    public UndoMenuItem() {
        super("Undo");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null, "UNDO");
        System.out.println("Undo");

    }
}
