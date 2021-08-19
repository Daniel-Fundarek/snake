package sk.stuba.fei.uim.oop.Blocks;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SnakeTailBlock extends Block{
    public SnakeTailBlock(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void paintBlock(Graphics g, int length, int height) {
        // zatila iba flipuje
        AffineTransform backup = flipVerticallyGraphics(g);
        g.drawImage(getImage(),getX()*length+getOffset(),getY()*height+getOffset(),length,height,null);
        g.drawRect(getX()*length+getOffset(),getY()*height+getOffset(),length,height);
        resetGraphics(g,backup);
    }
}
