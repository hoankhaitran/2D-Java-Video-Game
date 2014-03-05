package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class SushiBar extends Bar {

    private int Size;

    public SushiBar(float x, float y, int size) {
        this.setLocationX(x);
        this.setLocationY(y);
        this.Size = size;
        this.setObjectColor(Color.yellow);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);

        g.drawString("(" + this.getCenter().x + "," + this.getCenter().y + ")", this.getCenter().x + Game.OFF_SET, this.getCenter().y);
        g.drawLine(this.getCenter().x, this.getCenter().y, this.getCenter().x, this.getCenter().y);

        g.setColor(Color.ORANGE);
        g.drawRect((int) this.getLocationX(), (int) this.getLocationY(), Size * 3, Size * 2);
        g.drawString("SUSHI BAR" + this.getLocationX() + " " + this.getLocationY(), (int) this.getLocationX(), (int) this.getLocationY());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getLocationX(), (int) this.getLocationY(), Size * 3, 2 * Size);
    }

    public Point getCenter() {
        return new Point((int) this.getLocationX() + Size * 3 / 2, (int) this.getLocationY() + Size);
    }
}
