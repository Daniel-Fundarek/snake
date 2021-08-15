package sk.stuba.fei.uim.oop.Background;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Background {
    @Setter@Getter
    int score = 0;
    Image image;

    public Background(Image image) {
        this.image = image;
    }

    public void paintBackground(Graphics g){
        g.drawImage(image,0,0,1900,1020,null);
        drawString(g);
    }
    public void addToScore(){   //uz to nieje treba
    score++;
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
