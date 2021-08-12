package sk.stuba.fei.uim.oop.DialogWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogExample implements ActionListener {

    private  JDialog d;
    private String title = "";
    JButton b = new JButton("Restart");
    JButton c = new JButton("Exit");

    public DialogExample() {

        this("title");

    }

    public DialogExample(String title) {

        this.title = title;
        JFrame f = new JFrame();

        d = new JDialog(f, title, true);
        d.setLayout(new FlowLayout());

        b.addActionListener(this);
        c.addActionListener(this);

      /*  b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });*/

        //d.add(new JLabel("Click button to continue."));
        d.add(b);
        d.add(c);
        d.setSize(300, 90);
        //d.setVisible(true);
        d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
    public void setVisible(boolean visible){
        d.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(b.getText())){
            // restart

            setVisible(false);
        }
        else{
            // exit and close
            System.exit(0);
            d.dispose();
        }
    }
}
