package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class GameObject implements IDrawable, ICollider {

    private float locationX;
    private float locationY;
    private Color objectColor;
    private boolean collided;
    private boolean livable = true;

    public GameObject() {
        locationX = 500;
        locationY = 500;
        collided = false;
        objectColor = new Color(0, 0, 0); // black

    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;

    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public Color getObjectColor() {
        return objectColor;
    }

    public void setObjectColor(Color objectColor) {
        this.objectColor = objectColor;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public boolean isLivable() {
        return livable;
    }

    public void setLivable(boolean livable) {
        this.livable = livable;
    }

    @Override
    public void handleCollision(GameWorld gw) {
        //will be implemented later in the game objects hierarachy    
    }

    @Override
    public boolean collidesWith(ICollider other) {
        return this.getBounds().intersects(other.getBounds());
    }



    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (Game.DEBUG == true) {
            g2d.setColor(Color.YELLOW);
            g2d.draw(this.getBounds());
        } else {
            g2d.drawString("GameObjects", (int) this.getLocationX(), (int) this.getLocationY());
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getLocationX(), (int) this.getLocationY(), Game.OFF_SET, Game.OFF_SET);
    }

    @Override
    public Point getCenter() {
        return new Point((int) this.getLocationX() + Game.OFF_SET / 2, (int) this.getLocationY() + Game.OFF_SET / 2);
    }
}
