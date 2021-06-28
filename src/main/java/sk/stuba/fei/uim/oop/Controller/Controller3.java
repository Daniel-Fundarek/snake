package sk.stuba.fei.uim.oop.Controller;

import sk.stuba.fei.uim.oop.Blocks.*;
import sk.stuba.fei.uim.oop.Canvas;
import sk.stuba.fei.uim.oop.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;

public class Controller3 {
    private int LENGTH = 20, HEIGHT = 20;
    Block[][] emptyBoard = new Block[LENGTH][HEIGHT];
    Block[][] board = new Block[LENGTH][HEIGHT];
    ArrayList<Block> snake = new ArrayList<>();
    Direction direction = Direction.RIGHT;
    Direction prevDir = direction;
    Canvas canvas = new Canvas(board,LENGTH,HEIGHT);
    MainFrame frame = new MainFrame(canvas);
    Random random = new Random();
    FoodBlock food;

    int delay = 100;
    ActionListener taskPerformer = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

       //     System.out.println(cas++);
           direction = frame.getDirection();
           canvas.setX(canvas.getX()+direction.getX()*10);
           canvas.setY(canvas.getY() + direction.getY()*10);
           board = createDeepCopy(emptyBoard,board);
           movementDecider();
           moveSnake();
           eraseSnakeEnd();
           addSnakeToBoard();
           addFood();
           canvas.repaint();
           printBoard(board);

        }
    };





    public Controller3() {
        createEmptyBoard();
        board = createDeepCopy(emptyBoard,board);
        addSnakeHead();
        generateFood();
        addFood();

        new Timer(delay, taskPerformer).start();
    }

    Block[][] createDeepCopy(Block[][] old, Block[][] curr){


        for(int y = 0; y< board.length; y++){
          curr[y] = Arrays.copyOf(old[y],old[y].length);
        }
        return curr;
    }

    void addFood(){
        board[food.getX()][food.getY()] = food;
    }
    void generateFood(){

        ArrayList<Integer> excluded = new ArrayList<>();
        for (Block block : snake){
            excluded.add(block.getY()*LENGTH + block.getX());
         }
        int rand = generateRandom();
        int x = rand % LENGTH;
        int y = rand / LENGTH;
        food = new FoodBlock(board[x][y].getX(),board[x][y].getY());
    }

    public int generateRandom() {
        ArrayList<Block> array = new ArrayList<>();
        for (int y = 0; y < LENGTH;y++){
            for(int x =0; x < HEIGHT;x++){
                array.add(new EmptyBlock(x,y));
            }
        }
        for (Block block : snake){
            for(int i = array.size()-1;i >= 0;i--){
                if(array.get(i).getX()== block.getX() && array.get(i).getY() == block.getY()){
                    array.remove(i);
                    break;
                }
            }
        }
        int rand = random.nextInt(array.size());
        return array.get(rand).getX()+ array.get(rand).getY()*LENGTH;

    }
    void printBoard(Block[][] board){

        for(int y = 0; y< board.length; y++){
            for(int x = 0;x < board[y].length; x++){
                if(board[x][y] instanceof SnakeBlock ){
                    System.out.print("S");
                }
                else if(board[x][y] instanceof SnakeBodyBlock){
                    System.out.print("s");
                }
                else{
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    void createEmptyBoard(){
        for(int y = 0; y< HEIGHT; y++){
            for(int x = 0;x < LENGTH; x++){
            emptyBoard[x][y] = new EmptyBlock(x,y);
            }
        }
    }
    void copyBoard(){
        board = emptyBoard.clone();
    }

    void addSnakeHead(){
        int x = 1; // remake for randomness
        int y = 1;
        snake.add(new SnakeBlock(x,y));
    }

    void addSnakeToBoard(){
        for (Block snakeBlock : snake){
            board[snakeBlock.getX()][snakeBlock.getY()] = snakeBlock;
        }
    }

    void moveSnake(){
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        snake.add(0,new SnakeBlock(x+direction.getX(),y+direction.getY()));
        snake.set(1,new SnakeBodyBlock(x,y));
    }
    void eraseSnakeEnd(){
        snake.remove(snake.size()-1);
    }
    void movementDecider(){
        int diffX = direction.getX() + prevDir.getX();
        int diffY  = direction.getY() + prevDir.getY();
        if(diffX == 0 && diffY == 0){
            direction = prevDir;
        }
        else{
            prevDir = direction;
        }
    }


}
