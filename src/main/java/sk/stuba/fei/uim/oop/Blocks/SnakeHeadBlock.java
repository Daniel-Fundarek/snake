package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SnakeHeadBlock extends Block {
    public SnakeHeadBlock(int x, int y, Image image) {
        super(x, y, image);

    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        AffineTransform backup = rotateGraphics(g,getAngle(),length,height);
        g.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null);
        g.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);
        resetGraphics(g,backup);

    }




}
