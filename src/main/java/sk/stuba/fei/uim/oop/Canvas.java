package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Background.Background;
import sk.stuba.fei.uim.oop.Blocks.Block;
import sk.stuba.fei.uim.oop.Blocks.EmptyBlock;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    int RESOLUTIONX = 1800;//1800
    int RESOLUTIONY = 900;
    Block[][] board;
    int length, height;
    Background bg;
    public Canvas(Block[][] board, int length, int height, Background bg) { // odstranit zbytocny length a height
        this.board = board;
        this.length = RESOLUTIONX/length;
        this.height = RESOLUTIONY/height ;
        this.bg = bg;
        bg.setWidth(board.length*this.height);
        bg.setHeight(board[0].length*this.height);
        setFocusable(false);
        setSize(RESOLUTIONX,RESOLUTIONY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintBackground(g);
        for (Block[] blocks : board) {
            for (Block block : blocks) {
                block.paintBlock(g, height, height);// originally length
            }
        }

    }

}
