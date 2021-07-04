package sk.stuba.fei.uim.oop.Controller;

public enum Direction {
    UP(0,-1,0),
    DOWN(0,1,2),
    LEFT(-1,0,3),
    RIGHT(1,0,1);

    private  int x;
    private  int y;
    private  int eval;
    Direction(  int newValue,  int newValue2, int eval){
        x = newValue;
        y = newValue2;
        this.eval = eval;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEval() {
        return eval;
    }
}
