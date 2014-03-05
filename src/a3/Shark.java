package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Shark extends Animals {

    private int Size = 3;

    public Shark(float x, float y, int dir, int speed) {
        this.setLocationX(x);
        this.setLocationY(y);
        this.setDirection(dir);
        this.setSpeed(speed);
    }

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
            setDeltaX((float) -Math.cos(getDirection()) * distance);

        } else if (new Random().nextInt(10000) % 100 == 2) {
            this.setSpeed(new Random().nextInt(Game.MAX_SPEED));
            distance = getSpeed() * (float) elapsedTime / 1000.0f;
            setDeltaY((float) -Math.sin(getDirection()) * distance);
        }
        setLocationX(getLocationX() + getDeltaX());
        setLocationY(getLocationY() + getDeltaY());
        // Check if the Shark moves over the bounds

        if (getLocationX() < 0 - Size * Game.OFF_SET) {
            this.setLivable(false);
        } else if (getLocationX() > Game.MAP_WIDTH) {
            this.setLivable(false);
        }
        // May cross both x and y bounds
        if (getLocationY() < 0 - Size * Game.OFF_SET) {
            this.setLivable(false);
        } else if (getLocationY() > Game.MAP_HEIGHT) {
            this.setLivable(false);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawString("(" + this.getCenter().x + "," + this.getCenter().y + ")", this.getCenter().x + Game.OFF_SET, this.getCenter().y);
        g2d.setColor(Color.blue);
        g2d.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);
        g2d.draw(this.getPolygon());
       // g2d.draw(this.getBounds());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getLocationX(), (int) this.getLocationY(), Size * Game.OFF_SET, Size * Game.OFF_SET);
    }

    public Polygon getPolygon() {
        Point p1 = new Point((int) this.getLocationX(), (int) this.getLocationY());
        Point p2 = new Point((int) this.getLocationX() + Size * Game.OFF_SET / 2, (int) this.getLocationY()+ Size * Game.OFF_SET);
        Point p3 = new Point((int) this.getLocationX()+ Size * Game.OFF_SET , (int) this.getLocationY() );
        int[] xpoints = new int[]{p1.x, p2.x, p3.x};
        int[] ypoints = new int[]{p1.y, p2.y, p3.y};
        Polygon triangle = new Polygon(xpoints, ypoints, xpoints.length);
        
        
        return triangle;
    }

    @Override
    public Point getCenter() {
        return new Point((int) this.getLocationX() + Size * Game.OFF_SET / 2, (int) this.getLocationY() + Size * Game.OFF_SET / 2);
    }
}
