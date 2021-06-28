package sk.stuba.fei.uim.oop.Controller;

public enum Direction {
    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0);

    private  int x;
    private  int y;
    Direction(  int newValue,  int newValue2){
        x = newValue;
        y = newValue2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
