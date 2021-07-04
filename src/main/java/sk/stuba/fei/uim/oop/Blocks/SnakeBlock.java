package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SnakeBlock extends Block {
    Double angle = 0.0;
    public SnakeBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        Graphics2D g2d = (Graphics2D) g;
        //Make a backup so that we can reset our graphics object after using it.
        AffineTransform backup = g2d.getTransform();
        //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
        //is the angle to rotate the image. If you want to rotate around the center of an image,
        //use the image's center x and y coordinates for rx and ry.
        AffineTransform a = AffineTransform.getRotateInstance(angle, getX()*length+length/2, getY()*height+height/2);
        //Set our Graphics2D object to the transform
        g2d.setTransform(a);
        //Draw our image like normal
        g.drawImage(getImage(),getX()*length,getY()*height,length,height,null);
        g.drawRect(getX()*length,getY()*height,length,height);
        //Reset our graphics object so we can draw with it again.
        g2d.setTransform(backup);

    }
    public void rotateImage(int direction){
        angle = Math.PI/2 * direction;
    }
}
