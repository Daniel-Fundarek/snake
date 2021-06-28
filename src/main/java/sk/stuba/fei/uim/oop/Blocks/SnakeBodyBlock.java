package sk.stuba.fei.uim.oop.Blocks;

import java.awt.*;

public class SnakeBodyBlock extends Block{
    public SnakeBodyBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintBlock(Graphics g,int length, int height) {
        g.fillRect(x*length,y*height,length,height);
    }
}
