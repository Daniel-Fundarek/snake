package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Background.Background;
import sk.stuba.fei.uim.oop.Blocks.Block;
import sk.stuba.fei.uim.oop.Blocks.EmptyBlock;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    int RESOLUTIONX = 1800;
    int RESOLUTIONY = 900;
    Block[][] board;
    int length, height;
    Background bg;
    public Canvas(Block[][] board, int length, int height, Background bg) {
        this.board = board;
        this.length = RESOLUTIONX/length;
        this.height = RESOLUTIONY/height ;
        this.bg = bg;
        System.out.println(this.length);
        setFocusable(false);
        setSize(RESOLUTIONX,RESOLUTIONY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintBackground(g);
        for(int x = 0; x< board.length; x++){
            for(int y = 0;y < board[x].length ; y++){
               board[x][y].paintBlock(g,height, height);// originally length
            }
        }

    }

}
