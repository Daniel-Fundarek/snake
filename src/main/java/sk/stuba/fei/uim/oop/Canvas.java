package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    @Setter@Getter
    int x=0, y=0;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        testPaint(x,y,g);
    }
    void testPaint(int x , int y,Graphics g){
        g.fillRect(x,y,100,100);
    }
}
