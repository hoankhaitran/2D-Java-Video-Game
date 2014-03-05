/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.PointsView;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;

/**
 *
 * @author FXSSD
 */
public class TestAction extends AbstractAction {
    
    public TestAction(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.gc();
        System.out.println("Garbage collection running");
    }
    
}
