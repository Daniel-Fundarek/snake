package sk.stuba.fei.uim.oop.Blocks;

import sk.stuba.fei.uim.oop.Blocks.Block;

import java.awt.*;

public class EmptyBlock extends Block {
    public EmptyBlock() {
        super(1,1 );
    }

    public EmptyBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        g.drawRect(x*length,y*height,length,height);
    }
}
