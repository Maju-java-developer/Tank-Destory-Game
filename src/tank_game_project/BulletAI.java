package tank_game_project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class BulletAI {
    
    PVector postion;
    int velectiy = 15;
    
    int bulletwidth = 10;
    int bulletheight = 15; 
    
    ImageIcon upIcon = new ImageIcon(""),
            downIcon = new ImageIcon(""),
            leftIcon = new ImageIcon(""),
            rightIcon = new ImageIcon("");
            
    ImageIcon bulletAI = new ImageIcon("");

    int direction;
    
    public BulletAI(TankAI tankAI){
        this.direction = tankAI.direction; 
        postion = new PVector(tankAI.postion.x+10, tankAI.postion.y+10);
        
        leftIcon = Defaults.rescaleImage(bulletwidth,bulletheight, 
                new File("").getAbsoluteFile() + "\\src\\bulletImages\\" + "bullet_left.png"
        );

        rightIcon = Defaults.rescaleImage(bulletwidth,bulletheight, 
                new File("").getAbsoluteFile() + "\\src\\bulletImages\\" + "bullet_right.png"
        );
        
        upIcon = Defaults.rescaleImage(bulletwidth,bulletheight, 
                new File("").getAbsoluteFile() + "\\src\\bulletImages\\" + "bullet_up.png"
        );
        downIcon = Defaults.rescaleImage(bulletwidth,bulletheight, 
                new File("").getAbsoluteFile() + "\\src\\bulletImages\\" + "bullet_down.png"
        );
        
    }
    
    public void update(){
        //0 is left
        if (direction == 0) {
            bulletAI = leftIcon;
            postion.x -= velectiy;
        }
        //1 is right
        if (direction == 1) {
            bulletAI = rightIcon;
            postion.x += velectiy;
        }
        //2 is up
        if (direction == 2) {
            bulletAI = upIcon;
            postion.y -= velectiy;
        }
        //3 is down
        if (direction == 3) {
            bulletAI = downIcon;
           postion.y += velectiy;
        }
    }
}
