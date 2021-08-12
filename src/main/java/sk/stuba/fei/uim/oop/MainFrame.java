package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.Controller.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    private Canvas canvas;
    @Getter@Setter
    private Direction direction = Direction.RIGHT;

    public MainFrame(Canvas canvas) throws HeadlessException {
        this.canvas = canvas;
        setLayout(new GridLayout(1,1));
        add(this.canvas);
        setVisible(true);
        setFocusable(true);
        addKeyListener(this);
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                direction =  Direction.UP;
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
