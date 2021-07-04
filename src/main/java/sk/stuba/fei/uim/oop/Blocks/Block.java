package sk.stuba.fei.uim.oop.Blocks;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

abstract public class Block {
    @Setter
    @Getter
    private int x,y;
    @Getter@Setter
    private Image image;

    public Block(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public abstract void paintBlock(Graphics g,int length, int height);
}
