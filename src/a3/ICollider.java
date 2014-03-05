/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author FXSSD
 */
public interface ICollider {
    public void handleCollision(GameWorld gw);
    public boolean collidesWith(ICollider another);
    public Rectangle getBounds();
    public Point getCenter();
}
