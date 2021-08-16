package sk.stuba.fei.uim.oop.Blocks;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;

abstract public class Block {
    @Getter
    private int offset = 50;
    @Setter
    @Getter
    private int x,y;
    @Getter@Setter
    private Image image;
    @Getter@Setter
    Double angle = 0.0;

    public Block(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }


    public abstract void paintBlock(Graphics g,int length, int height);
    public AffineTransform rotateGraphics(Graphics g, Double angle, int length, int height){
        Graphics2D g2d = (Graphics2D) g;
        //Make a backup so that we can reset our graphics object after using it.
        AffineTransform backup = g2d.getTransform();
        //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
        //is the angle to rotate the image. If you want to rotate around the center of an image,
        //use the image's center x and y coordinates for rx and ry.
        AffineTransform a = AffineTransform.getRotateInstance(angle, getX()*length+length/2+getOffset(), getY()*height+height/2+getOffset());
        //Set our Graphics2D object to the transform
        g2d.setTransform(a);
        //Draw our image like normal

        return backup;
    }
    public void resetGraphics(Graphics g, AffineTransform backup){
        Graphics2D g2d = (Graphics2D) g;
        //Reset our graphics object so we can draw with it again.
        g2d.setTransform(backup);
    }
    public void calculateAngle(int direction){
        setAngle(Math.PI/2 * direction);
    }
}
