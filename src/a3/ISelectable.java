/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author FXSSD
 */
public interface ISelectable {
    public void setSelected(boolean input);
    public boolean isSelected();
    public boolean contains(Point p);
    public void draw(Graphics g);
    public Rectangle getBounds();
}
