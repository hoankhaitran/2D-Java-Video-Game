/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.myCommands;

import a3.Game;
import a3.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;

/**
 *
 * @author hoantran
 */
public class ActivateSound extends AbstractAction {

    GameWorld gw;

    public ActivateSound(GameWorld gw) {
        super("Sound");
        this.gw = gw;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        AbstractButton aButton = (AbstractButton) ae.getSource();
        boolean selected = aButton.getModel().isSelected();
        if (selected) {
            gw.setSound(true);
            gw.setChanged();
            if(gw.isSound()&&Game.GAME_RUNNING)
                Game.BACKGROUND_AUDIO.loop();
            
        } else {
            gw.setSound(false);
            gw.setChanged();
            Game.BACKGROUND_AUDIO.stop();
            
        }

    }
}
