package sk.stuba.fei.uim.oop.Blocks;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
// neviem ci budem potrebovat
public class SnakeBlock extends Block{
    @Setter@Getter
    int turn = 0;
    public SnakeBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {

    }
}
