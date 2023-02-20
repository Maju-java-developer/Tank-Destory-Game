package tank_game_project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class bullet {

    PVector postion;
    int velecity = 15;
    
    ImageIcon upIcon = new ImageIcon(""),
            downIcon = new ImageIcon(""),
            leftIcon = new ImageIcon(""),
            rightIcon = new ImageIcon("");
    
    ImageIcon bulletIcon = new ImageIcon("");
    
    int bulletwidth = 15;
    int bulletheight = 10;
    
    int direction;

    public bullet(Tank tank) {
        this.direction = tank.direction;
        postion = new PVector(tank.postion.x, tank.postion.y);
        
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
        // 0 is LEFT
        if (direction == 0) {
            bulletIcon = leftIcon;
            postion.x -= velecity;
        }
        // 1 is RIGHT
        if (direction == 1) {
            bulletIcon = rightIcon;
            postion.x += velecity;
            
        }
        // 2 is UP
        if (direction == 2) {
            bulletIcon = upIcon;
            postion.y -= velecity;
        }
        // 3 is DOWN
        if (direction == 3) {
            bulletIcon = downIcon;
            postion.y += velecity;
        }
    }
}
