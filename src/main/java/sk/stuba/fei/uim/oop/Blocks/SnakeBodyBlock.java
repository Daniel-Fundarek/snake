package sk.stuba.fei.uim.oop.Blocks;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SnakeBodyBlock extends Block{
    public SnakeBodyBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g,int length, int height) {

      //  g.fillRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);

        AffineTransform backup = rotateGraphics(g,getAngle(),length,height);
        g.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null);
        g.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);
        resetGraphics(g,backup);

    }
}
