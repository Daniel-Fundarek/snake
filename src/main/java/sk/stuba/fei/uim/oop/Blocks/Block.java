package sk.stuba.fei.uim.oop.Blocks;

import lombok.Getter;
import lombok.Setter;

abstract public class Block {
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Setter
    @Getter
    int x,y;
}
