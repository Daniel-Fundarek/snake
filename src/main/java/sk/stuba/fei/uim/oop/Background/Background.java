package sk.stuba.fei.uim.oop.Background;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Background {
    @Setter@Getter
    private int score = 0;
    private Image imageOfGrass;
    private Image imageOfWood;
    private int offset = 50;
    @Setter
    private int width = 1800;
    @Setter
    private int height = 900;

    public Background(Image image, Image image2 ) {
        this.imageOfWood = image;
        this.imageOfGrass = image2;
    }

    public void paintBackground(Graphics g){
        g.drawImage(imageOfWood,0,0,1900,1020,null);
        g.drawImage(imageOfGrass,offset,offset,width,height,null);
        drawString(g);
    }

    public  void drawString(Graphics g){
// does not work
        Graphics2D g2d = (Graphics2D) g;
        String stringScore = String.valueOf(score);
        String name = "Score: ";
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        g2d.drawString(name+stringScore,10,20);
        g2d.setColor(Color.BLACK);

    }
}
