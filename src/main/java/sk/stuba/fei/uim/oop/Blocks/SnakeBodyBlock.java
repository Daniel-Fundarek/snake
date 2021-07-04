package sk.stuba.fei.uim.oop.Blocks;

import java.awt.*;

public class SnakeBodyBlock extends Block{
    public SnakeBodyBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g,int length, int height) {
        g.fillRect(getX()*length,getY()*height,length,height);
    }
}
