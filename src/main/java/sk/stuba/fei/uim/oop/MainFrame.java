package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    Canvas canvas;

    public MainFrame(Canvas canvas) throws HeadlessException {
        this.canvas = canvas;
        add(this.canvas);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
    }
}
