package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;

public class SnakeBlock extends Block {
    public SnakeBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
         Graphics2D g2d = (Graphics2D) g;
        g.fillRect(x*length,y*height,length,height);
        //g2d.drawImage(image,x,y,null);
    }
}
