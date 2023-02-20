package tank_game_project;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class Tank {

    PVector postion;
    
    Dimension size;
    
    ImageIcon tankImage = new ImageIcon("");
    
    int tankWidth = 70;
    int tankHeight = 30; 
    
    Dimension sizeUpDown = new Dimension(tankHeight, tankWidth);
    Dimension sizeLeftRight = new Dimension(tankWidth, tankHeight);
    
    String faceToRight, faceToLeft, faceToUp, faceToDown;
    
    int direction = 1;

    public Tank(){
        this.size = sizeLeftRight;
        postion = new PVector(
                RandomRange.randomFloat(0, size.width), 
                RandomRange.randomFloat(0, size.height)
        );
        
        String rootPath = new File("").getAbsolutePath() + "\\src\\TankImages\\";
        
        faceToUp = rootPath + "face_to_top.png";
        faceToDown = rootPath + "face_to_bottom.png";
        faceToLeft = rootPath + "face_to_left.png";
        faceToRight = rootPath + "face_to_right.png";
    }
    public void update(){
        //0 is Left:
        if (direction == 0) {
            this.size = sizeLeftRight;
            tankImage = Defaults.rescaleImage(sizeLeftRight.height, sizeLeftRight.width, faceToLeft);
        }
        
        //1 is Right:
        if (direction == 1) {
            this.size = sizeLeftRight;
            tankImage = Defaults.rescaleImage(sizeLeftRight.height, sizeLeftRight.width, faceToRight);
        }
        
        //2 is Up:
        if (direction == 2) {
            this.size = sizeUpDown;
            tankImage = Defaults.rescaleImage(sizeUpDown.height, sizeLeftRight.width, faceToUp);
        }
        
        //3 is Down:
        if (direction == 3) {
            this.size = sizeUpDown;
            tankImage = Defaults.rescaleImage(sizeUpDown.width, sizeUpDown.height, faceToDown);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }
        
    }
    
    ArrayList<bullet>  bullets = new ArrayList<bullet>();
    
    public void fireBullet(){
        bullet bullet = new bullet(this);
        bullets.add(bullet);

    }
}
