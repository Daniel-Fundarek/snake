package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;

public class FoodBlock extends Block {

    public FoodBlock(int x, int y, Image image) {
        super(x, y,image);

    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
     // g.drawRect(x*length,y*height,length,height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null);
        g2d.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);

    }

}
