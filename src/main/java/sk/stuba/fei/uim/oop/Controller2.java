package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.Blocks.Block;
import sk.stuba.fei.uim.oop.Blocks.EmptyBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Controller2 {
    private int LENGTH = 20, HEIGHT = 20;
    Random rand = new Random();

    Canvas canvas = new Canvas();
    MainFrame frame = new MainFrame(canvas);
    int cas =0;
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    Controller.Direction direction = Controller.Direction.RIGHT;
    int delay = 1000;

    HashMap<Dimension, Block> board = new HashMap<Dimension,Block>();

    HashMap<Dimension, Block> emptyBoard = new HashMap<Dimension, Block>();

    HashMap<Dimension, Block> snake = new HashMap<Dimension, Block>();


    public Controller2() {
        createEmptyBoard();
        stupidCopy();
        test();
        if(board.containsKey(new Dimension(10,12)))
            System.out.println("funguje");;
    }

    void createEmptyBoard() {
        for (int y = 0; y < HEIGHT;y++) {
            for (int x = 0; x < LENGTH; x++) {
                emptyBoard.put(new Dimension(x, y), new EmptyBlock());
            }
        }

        snake.put(new Dimension(12, 12), new EmptyBlock());
        snake.put(new Dimension(14, 12), new EmptyBlock());
        snake.put(new Dimension(10, 12), new EmptyBlock());
        snake.put(new Dimension(1, 1), new EmptyBlock());

    }
    void stupidCopy(){
        board = emptyBoard;
    }


    void test(){

        for(Iterator<Dimension> it = snake.keySet().iterator(); it.hasNext();){
            Dimension dim = it.next();
            board.remove(dim);
        }


        if (board.containsValue(snake.values())){
            board.remove(snake.values());
        };

    }


}
