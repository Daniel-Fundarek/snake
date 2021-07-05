package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;

public class EmptyBlock extends Block {

    public EmptyBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        g.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null);
        g.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);
    }
}
