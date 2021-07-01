package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;

public class FoodBlock extends Block {
    public FoodBlock(int x, int y, Image image) {
        super(x, y,image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        g.drawRect(x*length,y*height,length,height);
        g.setColor(Color.GREEN);
        g.fillOval(x*length,y*height,length,height);
        g.setColor(Color.GRAY);
    }
}
