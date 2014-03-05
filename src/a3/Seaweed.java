package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;

public class Seaweed extends Plants {

    private StringBuilder sb = new StringBuilder();//local use only
    private Formatter formatter = new Formatter(sb);

    public Seaweed(float x, float y) {
        setObjectColor(new Color(0, 255, 0)); //Plant is green by default
        this.setLocationX(x);
        this.setLocationY(y);
    }

    @Override
    public String toString() {
        sb.delete(0, sb.length());
        formatter.format("Seaweed:loc=(%3.2f,%3.2f) color=[%s,%s,%s]", this.getLocationX(), this.getLocationY(), this.getObjectColor().getRed(),
                this.getObjectColor().getGreen(), this.getObjectColor().getBlue());
        return sb.toString();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawString("(" + this.getCenter().x + "," + this.getCenter().y + ")", this.getCenter().x + Game.OFF_SET, this.getCenter().y);

        g2d.setColor(Color.GREEN);
        g2d.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);
        g2d.draw(this.getBounds());

        //g2d.drawImage(this.getIconIm(), (int) this.getLocationX(), (int) this.getLocationY(), null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getLocationX(), (int) this.getLocationY(), Game.OFF_SET, 2 * Game.OFF_SET);
    }

    @Override
    public Point getCenter() {
        return new Point((int) this.getLocationX() + Game.OFF_SET / 2, (int) this.getLocationY() + Game.OFF_SET);
    }
}
