package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;

public class SnakeBlock extends Block {
    public SnakeBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        g.fillRect(x*length,y*height,length,height);
    }
}
