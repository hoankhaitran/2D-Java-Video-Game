package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Net extends Catcher {

    private int Size;
    private boolean Scooping;
    private boolean Deliverable;

    public Net(float x, float y) {
        this.setLocationX(x);
        this.setLocationY(y);
        this.setSize(300);
        this.setScooping(true);
        this.setObjectColor(Color.WHITE);
        this.Deliverable = true;
    }

    @Override
    public void expandNet() {
        Size += 5;
        if (this.getSize() >= 500) {
            this.setSize(500); //MAx net size is 500
        }
        System.out.println("Net size is " + this.getSize());
    }

    @Override
    public void contractNet() {
        Size -= 5;
        if (this.getSize() <= 50) {
            this.setSize(50); //Min net size is 50
        }
        System.out.println("Net size is " + this.getSize());
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        this.Size = size;
    }

    public boolean getScooping() {
        return Scooping;
    }

    public void setScooping(boolean scooping) {
        this.Scooping = scooping;
    }

    @Override
    public void move() {
        if (getLocationX() < 0) {
            this.setLocationX(0);

        } else if (getLocationX() + this.getSize() > Game.MAP_WIDTH) {
            this.setLocationX(Game.MAP_WIDTH - this.getSize() + Game.OFF_SET);

        }
        // May cross both x and y bounds
        if (getLocationY() < 0) {
            this.setLocationY(0);

        } else if (getLocationY() + this.getSize() > Game.MAP_HEIGHT) {
            this.setLocationY(Game.MAP_HEIGHT - this.getSize() + Game.OFF_SET);
        }
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        if (Game.DEBUG == true) {
            g2d.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);
            g.drawString("(" + this.getCenter().x + "," + this.getCenter().y + ")", this.getCenter().x + Game.OFF_SET, this.getCenter().y);
            g2d.draw(this.getBounds());
            g2d.drawString("NET Deliverable= " + this.isDeliverable(), (int) this.getLocationX(), (int) this.getLocationY());
        } else {
            g2d.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);
            g2d.draw(this.getBounds());
        }


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getLocationX(), (int) this.getLocationY(), Size, Size);
    }

    @Override
    public Point getCenter() {
        return new Point((int) this.getLocationX() + Size / 2, (int) this.getLocationY() + Size / 2);
    }

    @Override
    public void handleCollision(GameWorld gw) {
        if (Deliverable) {
            this.setScooping(true);
            int deliverPoint = gw.getFishInNetPoint();
            System.out.println("Fish delivered to sushi bar. Total points for this deliver " + deliverPoint);
            gw.setTotalFishPoint(gw.getTotalFishPoint() + deliverPoint);
            gw.setTotalPoint(gw.getTotalPoint() + gw.getFishInNetPoint());
            gw.setFishInNetPoint(0); //reset points in net after delivery 
            gw.setSharkInNetPoint(0);
            Deliverable = false;// set flag to avoid multiple delivers while net still in contact with bar
        }
    }

    public boolean isDeliverable() {
        return Deliverable;
    }

    public void setDeliverable(boolean Deliverable) {
        this.Deliverable = Deliverable;
    }
}
