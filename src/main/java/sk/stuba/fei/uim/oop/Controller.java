package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.Blocks.Block;
import sk.stuba.fei.uim.oop.Blocks.EmptyBlock;
import sk.stuba.fei.uim.oop.Blocks.FoodBlock;
import sk.stuba.fei.uim.oop.Blocks.SnakeBlock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Controller implements KeyListener {
    private int LENGTH = 20, HEIGHT = 20;
    Random rand = new Random();
    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList<Block> snake = new ArrayList<>();
    ArrayList<Block> board = new ArrayList<>();
    Canvas canvas = new Canvas();
    MainFrame frame = new MainFrame(canvas);
    int cas =0;
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    Direction direction = Direction.RIGHT;
    int delay = 1000;
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            System.out.println(cas++);
            canvas.setX(canvas.getX()+10);
            canvas.repaint();
            // check direction and add move head and delete last tile of snake...
            //...Perform a task...
        }
    };





    public Controller() {

        createBoard();
        cloneBoard();

        addSnake();
        addFood();
        new Timer(delay, taskPerformer).start();


    }

    void createBoard(){

        for (int y = 0; y<HEIGHT;y++){
            for(int x = 0; x < LENGTH;x++){
                blocks.add(new EmptyBlock(x,y));
            }
        }
    }
    void cloneBoard(){
        for(Block block: blocks){
            board.add(block);
        }
    }
    void addFood(){
        int rand = generateRandNum(LENGTH*HEIGHT);
        var empty = blocks.get(rand);
        while(!(empty instanceof EmptyBlock)){
            rand = generateRandNum(LENGTH*HEIGHT);
            empty = blocks.get(rand);
        }
        var food = new FoodBlock(empty.getX(),empty.getY());
        blocks.set(rand,food);
    }
    void addSnake(){
        for()
    }



    void createSnake(){
        int rand = generateRandNum(LENGTH * HEIGHT);
        var snakeHead = new SnakeBlock(calculatePositionX(rand), calculatePositionY(rand));
        snake.add(snakeHead);
    }
    int calculatePositionX(int num){
        return num % LENGTH;
    }

    int calculatePositionY(int num){
        return num / LENGTH;
    }

    int generateRandNum(int bound){
        return  rand.nextInt(bound);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT :
                direction = Direction.RIGHT;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
