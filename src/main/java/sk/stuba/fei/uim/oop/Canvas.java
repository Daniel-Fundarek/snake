package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Blocks.Block;
import sk.stuba.fei.uim.oop.Blocks.EmptyBlock;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    int RESOLUTIONX = 1920;
    int RESOLUTIONY = 1080;
    Block[][] board;
    int length, height;
    public Canvas(Block[][] board,int length, int height) {
        this.board = board;
        this.length = RESOLUTIONX/length;
        this.height = RESOLUTIONY/height;
        setFocusable(false);
        setSize(RESOLUTIONX,RESOLUTIONY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int y = 0; y< board.length; y++){
            for(int x = 0;x < board[y].length ; x++){
               board[x][y].paintBlock(g,height, height);
            }
        }

    }

}
