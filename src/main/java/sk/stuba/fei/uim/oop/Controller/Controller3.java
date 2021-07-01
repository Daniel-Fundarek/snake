package sk.stuba.fei.uim.oop.Controller;

import sk.stuba.fei.uim.oop.Blocks.*;
import sk.stuba.fei.uim.oop.Canvas;
import sk.stuba.fei.uim.oop.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;

public class Controller3 {
    private int LENGTH = 10, HEIGHT = 10;
    Block[][] emptyBoard = new Block[LENGTH][HEIGHT];
    Block[][] board = new Block[LENGTH][HEIGHT];
    ArrayList<Block> snake = new ArrayList<>();
    Direction direction = Direction.RIGHT;
    Direction prevDir = direction;
    Canvas canvas = new Canvas(board,LENGTH,HEIGHT);
    MainFrame frame = new MainFrame(canvas);
    Random random = new Random();
    FoodBlock food;
    Image snakeImage;
    Image snakeHeadImage;
    Image foodImage;
    Image emptyBlockImage;

    int delay = 300;
    ActionListener taskPerformer = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

       //     System.out.println(cas++);
           direction = frame.getDirection();

           movementDecider();
           checkCollision();
           board = createDeepCopy(emptyBoard,board);
           addSnakeToBoard();
           addFood();
           canvas.repaint();
           printBoard(board);

        }
    };





    public Controller3() {
        importImage("src/main/java/sk/stuba/fei/uim/oop/Images/SnakeHead/snake.png");
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
        food = new FoodBlock(board[x][y].getX(),board[x][y].getY(), foodImage);
    }
    void checkCollision() {
        int x = snake.get(0).getX() + direction.getX();
        int y = snake.get(0).getY() + direction.getY();
        if ( x == LENGTH || y == HEIGHT){
            // out of bound end
        }
        else {
            if (board[x][y] instanceof EmptyBlock) {
                moveSnake();
                eraseSnakeEnd();
            } else if (board[x][y] instanceof SnakeBodyBlock) {
                System.out.println("rip");
                System.exit(55);
            } else if (board[x][y] instanceof FoodBlock) {
                moveSnake();
                generateFood();
            }
        }
    }

    public int generateRandom() {
        ArrayList<Block> array = new ArrayList<>();
        for (int y = 0; y < LENGTH;y++){
            for(int x =0; x < HEIGHT;x++){
                array.add(new EmptyBlock(x,y, emptyBlockImage));
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
            emptyBoard[x][y] = new EmptyBlock(x,y,emptyBlockImage);
            }
        }
    }
    void copyBoard(){
        board = emptyBoard.clone();
    }

    void addSnakeHead(){
        int x = 1; // remake for randomness
        int y = 1;
        snake.add(new SnakeBlock(x,y,snakeHeadImage));
    }

    void addSnakeToBoard(){
        for (Block snakeBlock : snake){
            board[snakeBlock.getX()][snakeBlock.getY()] = snakeBlock;
        }
    }

    void moveSnake(){
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        snake.add(0,new SnakeBlock(x+direction.getX(),y+direction.getY(), snakeHeadImage));
        snake.set(1,new SnakeBodyBlock(x,y,snakeImage));
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

    private Image importImage(String imageURL){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("snake.png "));
        } catch (IOException e) {
        }
        return img;
    }

}
