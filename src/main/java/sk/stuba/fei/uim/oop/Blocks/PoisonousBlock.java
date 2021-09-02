package sk.stuba.fei.uim.oop.Blocks;

import java.awt.*;

public class PoisonousBlock extends Block{
    public PoisonousBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null); // z tohto treba spravit funcku\
        g2d.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);
    }
}
