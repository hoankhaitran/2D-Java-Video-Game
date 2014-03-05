/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author FXSSD
 */
public class MapView extends JPanel implements IObserver {
    
    GameWorldProxy gwp;
    private Iterator myObjectIterator;
    MyRec rec;
    
    public MapView(GameWorldProxy gwp) {
        rec = new MyRec(100, 100, 100, 200);
        this.gwp = gwp;
        myObjectIterator = gwp.getObjectIterator();
        this.setSize(Game.MAP_WIDTH, Game.MAP_HEIGHT);
    }
    
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(WIDTH, -1);
        g2d.translate(0, -Game.MAP_HEIGHT);
        
        rec.paintComponent(g2d);

        /*
         g2d.setColor(Color.white);
         g2d.fillRect(0, 0, Game.MAX_WIDTH, Game.MAX_HEIGHT);
         g2d.setColor(Color.BLACK);
         g2d.fillRect(0, 0, Game.MAP_WIDTH + Game.OFF_SET, Game.MAP_HEIGHT + Game.OFF_SET);
         g2d.setColor(Color.RED);

         //Printing debug information

         g2d.setColor(Color.RED);
         g2d.setColor(Color.white);
         if(Game.DEBUG==true){
         g2d.drawString("Number of Fish:" + gwp.getFishCount(), 10, 60);
         g2d.drawString(getMemory(), 10, 100);
         }
         myObjectIterator = gwp.getObjectIterator();
         while (myObjectIterator.hasNext()) {
         GameObject obj = (GameObject) myObjectIterator.next();
         obj.draw(g);
         }*/
        
    }
    
    @Override
    public void update() {//Map view will repaint itself when notified
        this.repaint();
    }
//TESTING CODE DO NOT GRADE

    private int getThreadCount() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        return threadSet.size();
    }
    
    private String getMemory() {
        
        StringBuilder sb = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance();
        long totalCpu = Runtime.getRuntime().availableProcessors();
        sb.append("FPS " + 1000 / Game.DELAY_IN_MS + "  ");
        sb.append("Total CPU cores used: " + format.format(totalCpu) + "   ");
        sb.append("Max Memory: " + format.format(Runtime.getRuntime().maxMemory() / 1024) + "MB   ");
        sb.append("OS: " + System.getProperty("os.name") + "   ");
        sb.append("System Architecture: " + System.getProperty("os.arch") + "   ");
        return sb.toString();
    }
}
