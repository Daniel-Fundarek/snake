package sk.stuba.fei.uim.oop.Blocks;

import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SnakeTailBlock extends Block{

    public SnakeTailBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        // zatila iba flipuje


        AffineTransform backup;
        if (getTurn() == -1){
            backup = turnLeft(g,length,height);

            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDOLAVA");

        }
        else{
            backup = rotateGraphics(g,getAngle(),length,height);
            g.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null);

        }
        g.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);
        resetGraphics(g,backup);
    }
    private AffineTransform turnLeft(Graphics g,int length, int height) {
        setAngle(getAngle() - Math.PI/2 );
        AffineTransform backup = rotateGraphics(g,getAngle(),length,height);
        g.drawImage(getImage(), getX() * length + getOffset() + length, getY() * height + getOffset(), -length, height, null);
        return backup;
    }



}
