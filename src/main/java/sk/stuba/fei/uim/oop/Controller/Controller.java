package sk.stuba.fei.uim.oop.Controller;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Background.Background;
import sk.stuba.fei.uim.oop.Blocks.*;
import sk.stuba.fei.uim.oop.Canvas;
import sk.stuba.fei.uim.oop.DialogWindow.DialogExample;
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
    Block[][] emptyBoard = new Block[LENGTH][HEIGHT];
    Block[][] board = new Block[LENGTH][HEIGHT];
    ArrayList<Block> snake = new ArrayList<>();
    Direction direction = Direction.RIGHT;
    Direction prevDir = direction;
    Canvas canvas;
    MainFrame frame;
    Random random = new Random();
    FoodBlock food;
    Image curvedSnakeBodyImage;
    Image straightSnakeBodyImage;
    Image snakeHeadImage;
    Image foodImage;
    Image emptyBlockImage;
    Image backgroundImage;
    Image grassImage;
    Image straightTail;
    Image curvedTail;
    Image headTail;
    Background background;
    Timer timer;
    int snakeTurn = 0;
    @Setter@Getter
    Boolean dialogResetVar = false;
    DialogExample dialog = new DialogExample("nazov", this);


    int delay = 1000;
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
          // printBoard(board);

        }
    };





    public Controller() {


        foodImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Food/apple.png");
        snakeHeadImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/SnakeHead/snakeHeadTransparent.png");
        emptyBlockImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Empty/background.png");
        backgroundImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Background/background.jpg");
        curvedSnakeBodyImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/snakeBody/snakeBodyCurvedTransparent1.png");
        straightSnakeBodyImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/snakeBody/snakeBodyStraightTransparent.png");
        grassImage = importImage("src/main/java/sk/stuba/fei/uim/oop/Images/Grass/grassImage.jpg");
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

       /* ArrayList<Integer> excluded = new ArrayList<>();
        for (Block block : snake){
            excluded.add(block.getY()*LENGTH + block.getX());
         }
        int rand = generateRandom();
        int x = rand % LENGTH;
        int y = rand / LENGTH;
        System.out.println(foodImage.getClass());
        //food = new FoodBlock(board[y][x].getX(),board[y][x].getY(), foodImage);*/
        food = generateRandom1();
    }
    void checkCollision() {
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

    public int generateRandom() { // blbost asi
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

    public FoodBlock generateRandom1() {
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
    void printBoard(Block[][] board){

        for(int y = 0; y< board.length; y++){
            for(int x = 0;x < board[y].length; x++){
                if(board[y][x] instanceof SnakeHeadBlock){
                    System.out.print("S");
                }
                else if(board[y][x] instanceof SnakeBodyBlock){
                    System.out.print("s");
                }
                else if (board[y][x] instanceof FoodBlock){
                    System.out.print("0");
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
        snake.add(new SnakeHeadBlock(x,y,snakeHeadImage));
    }

    void addSnakeToBoard(){
        for (Block snakeBlock : snake){
            board[snakeBlock.getX()][snakeBlock.getY()] = snakeBlock;
        }
    }

    void moveSnake(){
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
        if(snakeTurn == -1){
            angle = Math.PI/2 + angle;
        }
        snake.get(1).setAngle(angle);


    }
    void addTail(){ // add one before last
        Block block;
        block = snake.get(snake.size()-1);
        if (snake.size()>1) {
            if (block.getImage().equals(curvedSnakeBodyImage)) {
                block.setImage(curvedTail); // curved tail
            } else {
                block.setImage(straightTail); // straight tail
            }
        }
        else{
            block.setImage(headTail); // head with tail
        }
    }
    void eraseSnakeEnd(){
        snake.remove(snake.size()-1);
    }

    void movementDecider(){
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
    void TurnDecider(){
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
