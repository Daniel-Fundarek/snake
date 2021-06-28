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
        this.length = 1920/length;
        this.height = 1080/height;
        setFocusable(false);
        setSize(RESOLUTIONX,RESOLUTIONY);
    }

    @Setter@Getter
    int x=0, y=0;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int y = 0; y< board.length; y++){
            for(int x = 0;x < board[y].length ; x++){
               board[x][y].paintBlock(g,length, height);
            }
        }
        //testPaint(x,y,g);
    }
    void testPaint(int x , int y,Graphics g){
        g.fillRect(x,y,1920,1080);
    }
}
