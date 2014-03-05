/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hoantran
 */
public class PointsView extends JPanel implements IObserver {

    JLabel soundLb;
    GameWorldProxy gwp;
    JLabel dummyLb;
    JLabel totalPoints;
    JLabel fishPoints;
    JLabel sharkLb;
    JLabel fishInNetPoints,timeLb;

    public PointsView(GameWorldProxy gwp) {
        soundLb = new JLabel("Sound: OFF");
        this.gwp = gwp;
        dummyLb = new JLabel("");
        totalPoints = new JLabel("Total Points:" + gwp.getTotalPoint());
        fishPoints = new JLabel("Fish Points:" + gwp.getTotalFishPoint());
        sharkLb = new JLabel("Sharks caught:" + gwp.getSharkNum());
        fishInNetPoints = new JLabel("Points for fish in net:" + gwp.getFishInNetPoint());
        timeLb =new JLabel("Time:"+gwp.getGameClock()/1000);
        this.setLayout(new GridLayout(1, 6, 30, 0));//six colunms for six labels with 30px horizontal gap
        this.add(dummyLb);// dummy label for alignment purpose
        this.add(totalPoints);
        this.add(fishPoints);
        this.add(sharkLb);
        this.add(fishInNetPoints);
        this.add(soundLb);
        this.add(timeLb);
    }

    @Override
    public void update() {//PointsView will update itself when notified by GameWorld
        if(gwp.isSound()){
           soundLb.setText("Sound: ON"); 
        }else soundLb.setText("Sound: OFF"); 
        
        totalPoints.setText("Total Points:" + gwp.getTotalPoint());
        fishPoints.setText("Fish Points:" + gwp.getTotalFishPoint());
        sharkLb.setText("Sharks caught:" + gwp.getSharkNum());
        fishInNetPoints.setText("Points for fish in net:" + gwp.getFishInNetPoint());
        timeLb.setText("Time:"+gwp.getGameClock()/1000); //ms to second
        
    }
}
