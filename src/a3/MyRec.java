/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author hoantran
 */
public final class MyRec {
    AffineTransform rotation = new AffineTransform();
    AffineTransform translation = new AffineTransform();
    AffineTransform scale = new AffineTransform();
    int width;
    int height;
    int x,y;
    Body body;
    public MyRec(int x, int y,int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        body = new Body();
        this.translate(x,y);
                
                
    }
    public void rotate(double degree){
        double rad =Math.toRadians(degree);
        this.rotation.rotate(rad);
    }
    public void translate(double x , double y){
        translation.translate(x, y);
    }
    public void scale(double x, double y){
        this.scale.scale(x, y);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform savedAt = g2d.getTransform();
        g2d.transform(rotation);
        g2d.transform(translation);
        g2d.transform(scale);
        body.paintComponent(g2d);
        g2d.setTransform(savedAt);
        
    }
    public class Body {
    AffineTransform Bodyrotation = new AffineTransform();
    AffineTransform Bodytranslation = new AffineTransform();
    AffineTransform Bodyscale = new AffineTransform();
    
    public Body(){
 
    }
    public void rotate(double degree){
        double rad =Math.toRadians(degree);
        Bodyrotation.rotate(rad);
    }
    public void translate(double x , double y){
        Bodytranslation.translate(x, y);
    }
    public void scale(double x, double y){
        Bodyscale.scale(x, y);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform savedAt = g2d.getTransform();
        
        
        g2d.transform(Bodyscale);
        g2d.transform(Bodyrotation);
        g2d.transform(Bodytranslation);
        g2d.drawRect(0, 0, width, height);
        g2d.setTransform(savedAt);
        
    }
}
}
