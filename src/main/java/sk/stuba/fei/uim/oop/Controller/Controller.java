package sk.stuba.fei.uim.oop.Controller;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Background.Background;
import sk.stuba.fei.uim.oop.Blocks.*;
import sk.stuba.fei.uim.oop.Canvas;
import sk.stuba.fei.uim.oop.DialogWindow.DialogWindow;
import sk.stuba.fei.uim.oop.MainFrame;

import javax.imageio.ImageIO;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Controller {
    private final int LENGTH = 10;
    private final int HEIGHT = 5;
    private Block[][] emptyBoard = new Block[LENGTH][HEIGHT];
    private Block[][] board = new Block[LENGTH][HEIGHT];
    private ArrayList<Block> snake = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private Direction prevDir = direction;
    private Canvas canvas;
    private MainFrame frame;
    private Random random = new Random();
    private FoodBlock food;
    private Image curvedSnakeBodyImage;
    private Image straightSnakeBodyImage;
    private Image snakeHeadImage;
    private Image foodImage;
    private Image emptyBlockImage;
    private Image backgroundImage;
    private Image grassImage;
    private Image straightTail;
    private Image curvedTail;
    private Image headTail;
    private Background background;
    private Timer timer;
    private int snakeTurn = 0;
    @Setter@Getter
    private Boolean dialogResetVar = false;
    private DialogWindow dialog = new DialogWindow("End dialog window", this);


    private int delay = 500;
    ActionListener taskPerformer = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

       //     System.out.println(cas++);
           direction = frame.getDirection();
           movementDecider();
           checkCollision();
           board = createDeepCopy(emptyBoard,board);
           addSnakeToBoard();
           addFood();
           setRotation();
           canvas.repaint();
           printBoard(board);

        }
    };





    public Controller() {


        foodImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Food/apple.png");
        snakeHeadImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/SnakeHead/snakeHeadTransparent.png");
        emptyBlockImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Empty/background.png");
        backgroundImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Background/background.jpg");
        curvedSnakeBodyImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/snakeBody/snakeBodyCurvedTransparent1.png");
        straightSnakeBodyImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/snakeBody/snakeBodyStraightTransparent.png");
        grassImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Grass/grassImage.png");
        straightTail = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Tail/StraightTail.png");
        curvedTail = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Tail/CurvedTail.png");
        headTail = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Tail/HeadTail.png");
        startFunct();
    }

    public void startFunct(){
        background = new Background(backgroundImage,grassImage);
        createEmptyBoard();
        board = createDeepCopy(emptyBoard,board);
        addSnakeHead();
        generateFood();
        addFood();
        canvas = new Canvas(board,LENGTH,HEIGHT,background);
        frame = new MainFrame(canvas);

        timer = new Timer(delay, taskPerformer);
        timer.start();

    }
    public void restartFunct(){

        createEmptyBoard();
        background.setScore(0);
        board = createDeepCopy(emptyBoard,board);
        snake.clear();
        addSnakeHead();
        generateFood();
        addFood();
        direction = Direction.RIGHT;
        prevDir = Direction.RIGHT;
        frame.setDirection(Direction.RIGHT);
        timer = new Timer(delay, taskPerformer);
        timer.start();

    }


    private Block[][] createDeepCopy(Block[][] old, Block[][] curr){


        for(int y = 0; y< board.length; y++){
          curr[y] = Arrays.copyOf(old[y],old[y].length);
        }
        return curr;
    }

    private void addFood(){
        board[food.getX()][food.getY()] = food;
    }
    private void generateFood(){

       /* ArrayList<Integer> excluded = new ArrayList<>();
        for (Block block : snake){
            excluded.add(block.getY()*LENGTH + block.getX());
         }
        int rand = generateRandom();
        int x = rand % LENGTH;
        int y = rand / LENGTH;
        System.out.println(foodImage.getClass());
        //food = new FoodBlock(board[y][x].getX(),board[y][x].getY(), foodImage);*/
        food = generateRandom();
    }
    private void checkCollision() {
        int x = snake.get(0).getX() + direction.getX();
        int y = snake.get(0).getY() + direction.getY();
        if ( x == LENGTH || y == HEIGHT || x == -1 || y == -1){
            // out of bound end
          restartProcedure();
          //  System.out.println("rip");
          //  System.exit(55);
        }
        else {
            if (board[x][y] instanceof EmptyBlock) {
                moveSnake();
                eraseSnakeEnd();
                addTail();
            } else if (board[x][y] instanceof SnakeBodyBlock) {
                restartProcedure();
            } else if (board[x][y] instanceof SnakeTailBlock) {
                restartProcedure();
              //  System.out.println("rip");
              //  System.exit(55);
            } else if (board[x][y] instanceof FoodBlock) {
                moveSnake();
                addTail();
                generateFood();
                background.setScore(background.getScore()+1);
            }
        }
    }
    private void restartProcedure(){
        timer.stop();
        dialog.setVisible(true);

    }



    public FoodBlock generateRandom() {
        ArrayList<FoodBlock> array = new ArrayList<>();
        for (int y = 0; y < HEIGHT;y++){
            for(int x =0; x < LENGTH;x++){
                array.add(new FoodBlock(x,y, foodImage));
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
        return array.get(rand);

    }

    public FoodBlock generateRandomTestUnfinished() { // i have to update generate random to be sufficient for other types of blocks (hole or danger block)
        ArrayList<FoodBlock> array = new ArrayList<>();
        for (int y = 0; y < HEIGHT;y++){
            for(int x =0; x < LENGTH;x++){
                array.add(new FoodBlock(x,y, foodImage));
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
        return array.get(rand);

    }

    private void printBoard(Block[][] board){

        for (Block[] blocks : board) {
            for (Block block : blocks) {
                if (block instanceof SnakeHeadBlock) {
                    System.out.print("S");
                } else if (block instanceof SnakeBodyBlock) {
                    System.out.print("s");
                } else if (block instanceof SnakeTailBlock) {
                    System.out.print("-");
                } else if (block instanceof FoodBlock) {
                    System.out.print("0");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private void createEmptyBoard(){
        for(int y = 0; y< HEIGHT; y++){
            for(int x = 0;x < LENGTH; x++){
            emptyBoard[x][y] = new EmptyBlock(x,y,emptyBlockImage);
            }
        }
    }
    private void copyBoard(){
        board = emptyBoard.clone();
    }

    private void addSnakeHead(){
        int x = 1; // remake for randomness
        int y = 1;
        snake.add(new SnakeHeadBlock(x,y,snakeHeadImage));
    }

    private void addSnakeToBoard(){
        for (Block snakeBlock : snake){
            board[snakeBlock.getX()][snakeBlock.getY()] = snakeBlock;
        }
    }

    private void moveSnake(){
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        Double angle = snake.get(0).getAngle();
        snake.add(0,new SnakeHeadBlock(x+direction.getX(),y+direction.getY(), snakeHeadImage));

        if (snakeTurn == 0) {
            snake.set(1, new SnakeBodyBlock(x, y, straightSnakeBodyImage));

        }
        else {
            snake.set(1, new SnakeBodyBlock(x, y, curvedSnakeBodyImage));
        }
        snake.get(1).setTurn(snakeTurn);
        if(snakeTurn == -1){
            angle = Math.PI/2 + angle;
        }
        snake.get(1).setAngle(angle);


    }
    private void addTail(){ // add  last
        Block block;
        block = snake.get(snake.size()-1);
        if (snake.size()>1) {
            var tail = new SnakeTailBlock(block.getX(), block.getY(), null);


            if (block.getImage().equals(curvedSnakeBodyImage) || block.getImage().equals(curvedTail)) {
                tail.setAngle(block.getAngle());
                if (block instanceof SnakeTailBlock && block.getTurn() == -1){

                    tail.setAngle(block.getAngle() + Math.PI/2); // malo by byt - pi/2 ale takto to funguje
                }

                tail.setImage(curvedTail);
                System.out.println("zahnuty");
               // block.setImage(curvedTail); // curved tail
            } else {
                tail.setAngle(block.getAngle());
               // block.setImage(straightTail); // straight tail
                tail.setImage(straightTail);
                System.out.println("nezahnuty");
            }
            tail.setTurn(snake.get(snake.size()-1).getTurn());

            snake.set(snake.size()-1,tail);
        }
        else{
            block.setImage(headTail); // head with tail
        }
    }
    private void eraseSnakeEnd(){
        snake.remove(snake.size()-1);
    }

    private void movementDecider(){
        // riesi aby sa nedalo ist oproti o 180 stupnov
        int diffX = direction.getX() + prevDir.getX();
        int diffY  = direction.getY() + prevDir.getY();
        if(diffX == 0 && diffY == 0){
            direction = prevDir;
            TurnDecider();
        }
        else{
            TurnDecider();
            prevDir = direction;
        }
    }
    private void TurnDecider(){
        if (direction.getEval() == prevDir.getEval()){
            snakeTurn = 0;
        }
        else{
            System.out.println(" smer " +direction.getEval()+
                    " predchadzajuci smer " + prevDir.getEval());
            if(direction.getEval() < prevDir.getEval() ){
                if (direction.getEval() == 0 && prevDir.getEval() == 3){
                    snakeTurn = 1;
                }
                else {
                    snakeTurn = -1; // dolava
                }

            }
            else if(prevDir.getEval() == 0 && direction.getEval() == 3){ // funguje
                snakeTurn = -1; // dolava
            }

            else{
                snakeTurn =1; // doprava
            }

        }
        System.out.println(snakeTurn);
    }

    private Image importImage(String imageURL){
        Image image = null;
        try
        {
             image = ImageIO.read(new File(imageURL));
        }
        catch (IOException e)
        {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
        return image;
    }

    private void setRotation(){
        SnakeHeadBlock snakeHead = (SnakeHeadBlock) snake.get(0);
        snakeHead.calculateAngle(direction.getEval());
    }

}
