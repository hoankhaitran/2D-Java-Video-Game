package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;

public class Fish extends Animals implements ISelectable {

    private int Size;
    private int waitTime;
    private boolean selected;
    private int diameter;
    //private ImageIcon iic;
   // private Image img;

    public Fish(float x, float y, int dir, int speed) {
        this.setLocationX(x);
        this.setLocationY(y);
        this.setSize(1);
        waitTime = 0;
        diameter = Size * Game.OFF_SET;
        this.setDirection(dir);
        this.setSpeed(speed);
       // iic = new ImageIcon(this.getClass().getResource("images/smallfish.png"));
       // img = iic.getImage();


    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        if (size <= Game.MAX_FISH_SIZE) {
            this.Size = size;
        } else {
            this.Size = Game.MAX_FISH_SIZE;
        }
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public void move(int elapsedTime) {

        float distance = getSpeed() * (float) elapsedTime / 1000.0f;
        double rad = Math.toRadians(this.getDirection() + 90);//offset java coord to make 0 degree is NORTH
        float dx = (float) -Math.cos(rad) * distance;//offset java coord to make 90 degree is EAST
        float dy = (float) -Math.sin(rad) * distance;//offset java coord to make 180 degree is SOUTH
        if (this.getDeltaX() == 0 && this.getDeltaY() == 0) { //If fish speed is not set then calculate new speed using elapsed time
            this.setDeltaX(dx);
            this.setDeltaY(dy);
        }
//Randomly change speed and direction of the moving object

        if (new Random().nextInt(10000) % 100 == 1) {
            this.setSpeed(new Random().nextInt(Game.MAX_SPEED));
            distance = getSpeed() * (float) elapsedTime / 1000.0f;
            setDirection((int) new Random().nextInt(360));// in degree
            rad = Math.toRadians(this.getDirection() + 90);//to radian
            setDeltaX((float) -Math.cos(rad * distance));

        } else if (new Random().nextInt(10000) % 100 == 2) {
            this.setSpeed(new Random().nextInt(Game.MAX_SPEED));
            rad = Math.toRadians(this.getDirection() + 90);
            distance = getSpeed() * (float) elapsedTime / 1000.0f;
            setDeltaY((float) -Math.sin(rad * distance));
        }

        waitTime++;
        if (waitTime > Game.WAIT_TIME_IN_MS) {
            waitTime = 0;
            this.setCollided(false);
        }

        // Check if the fish moves over the bounds
        // If so, adjust the position and speed.
        if (getLocationX() < 0) {
            setDeltaX(-getDeltaX()); // Reflect along normal
            this.setLocationX(0);

        }

        if (getLocationX() + Game.OFF_SET * this.getSize() - Game.OFF_SET > Game.MAP_WIDTH) {
            setDeltaX(-getDeltaX());
            this.setLocationX(Game.MAP_WIDTH - Game.OFF_SET * this.getSize() + Game.OFF_SET);

        }
        // May cross both x and y bounds

        if (getLocationY() < 0) {
            setDeltaY(-getDeltaY());
            this.setLocationY(0);

        }
        if (getLocationY() + Game.OFF_SET * this.getSize() - Game.OFF_SET > Game.MAP_HEIGHT) {
            setDeltaY(-getDeltaY());
            this.setLocationY(Game.MAP_HEIGHT - Game.OFF_SET * this.getSize() + Game.OFF_SET);
        }
        setLocationX(getLocationX() + getDeltaX());
        setLocationY(getLocationY() + getDeltaY());
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        diameter = Size * Game.OFF_SET;// recalculate diameter for any fish that increases in size 
        if (this.isSelected() == true) {
            g2d.drawString("Radius= " + this.getDiameter() / 2.0f, this.getCenter().x - 3 * Game.OFF_SET, this.getCenter().y + this.getDiameter());
            g2d.drawString("(" + this.getCenter().x + "," + this.getCenter().y + ")", this.getCenter().x + this.getDiameter(), this.getCenter().y);
           // g2d.drawString("Impotent? " + this.isCollided(), (int) this.getLocationX(), (int) this.getLocationY());
            g2d.setColor(Color.lightGray);
            g2d.fillOval((int) this.getLocationX(), (int) this.getLocationY(), diameter, diameter);//Fish are circles
            //g2d.draw(this.getBounds());// for collision usage
            g2d.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);
           // g2d.draw(this.getBounds());
            //BufferedImage bi = ((ToolkitImage) img).getBufferedImage();
           // g2d.drawImage(bi.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH), (int) this.getLocationX() - Game.OFF_SET, (int) this.getLocationY() - Game.OFF_SET, null);


        } else {
            g2d.drawString("Radius= " + this.getDiameter() / 2.0f, this.getCenter().x - 3 * Game.OFF_SET, this.getCenter().y + this.getDiameter());
           // g2d.drawString("Impotent? " + this.isCollided(), (int) this.getLocationX(), (int) this.getLocationY());
            g2d.drawString("(" + this.getCenter().x + "," + this.getCenter().y + ")", this.getCenter().x + this.getDiameter(), this.getCenter().y);
            g2d.setColor(Color.cyan);
            g2d.drawOval((int) this.getLocationX(), (int) this.getLocationY(), diameter, diameter);
            g2d.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);
           // g2d.draw(this.getBounds());

        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getLocationX(), (int) this.getLocationY(), Game.OFF_SET * this.getSize(), Game.OFF_SET * this.getSize());
    }

    @Override
    public Point getCenter() {
        return new Point((int) this.getLocationX() + Game.OFF_SET * this.getSize() / 2, (int) this.getLocationY() + Game.OFF_SET * this.getSize() / 2);
    }

    @Override
    public boolean contains(Point p) {
        if (this.getBounds().contains(p)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void handleCollision(GameWorld gw) {
        int dir = (int) gw.generateRandomNum(Game.MAX_POLAR_DEGREE);
        int speed = (int) gw.generateRandomNum(Game.MAX_SPEED);
        Fish fish = new Fish((int) this.getLocationX() + Game.OFF_SET, (int) this.getLocationY() + Game.OFF_SET, dir, speed);
        gw.addGameObject(fish);
        this.setCollided(true);
    }

    @Override
    public boolean collidesWith(ICollider another) {
        float x1 = this.getCenter().x;
        float y1 = this.getCenter().y;
        float r1 = this.getDiameter() / 2.0f;
        float r1r2;
        double distanceSquared, c2c1x, c2c1y;

        float x2 = another.getCenter().x;
        float y2 = another.getCenter().y;

        Fish myfish;
        if (another instanceof Fish) {
            myfish = (Fish) another;
            float r2 = myfish.getDiameter() / 2.0f;
            r1r2 = r1 + r2;
            c2c1x = (x2 - x1);
            c2c1y = (y2 - y1);
            distanceSquared = Math.pow(c2c1x, 2) + Math.pow(c2c1y, 2);
            if (distanceSquared <= Math.pow(r1r2, 2)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
