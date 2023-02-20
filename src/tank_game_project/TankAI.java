package tank_game_project;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import processing.core.PVector;

public class TankAI {
    
    PVector postion;

    int width, height;
    
    int tankSize1 = 30;
    int tankSize2 = 70;
        
    Dimension size;
    
    Dimension sizeLeftRight = new Dimension(tankSize2, tankSize1);
    Dimension sizeupDown = new Dimension(tankSize1, tankSize2);
        
    int tankDirectionChange = 50;
    int tankAIVelecity = 5;
    int bulletAILimit = 2;

    int direction = 0;
    
    ImageIcon face_to_right = new ImageIcon(""),
            face_to_left = new ImageIcon(""),
            face_to_up = new ImageIcon(""),
            face_to_down = new ImageIcon("");

    ImageIcon tankIAIcon = new ImageIcon("");
    
    public TankAI(){}
    public TankAI(int width, int height){
        this.width = width;
        this.height = height;
        this.size = sizeLeftRight;

        postion = new PVector(
                RandomRange.randomFloat(0, width),
                RandomRange.randomFloat(0, height)
        );
        
        face_to_left = Defaults.rescaleImage(sizeLeftRight.height, sizeLeftRight.width, 
                new File("").getAbsolutePath() + "\\src\\tankAIImages\\"+"face_to_left.png"
        );
        
        face_to_right = Defaults.rescaleImage(sizeLeftRight.height, sizeLeftRight.width, 
                new File("").getAbsolutePath() + "\\src\\tankAIImages\\"+"face_to_right.png"
        );
        
        face_to_up = Defaults.rescaleImage(sizeupDown.width, sizeupDown.height, 
                new File("").getAbsolutePath() + "\\src\\tankAIImages\\"+"face_to_top.png"
        );
        
        face_to_down = Defaults.rescaleImage(sizeupDown.width, sizeupDown.height, 
                new File("").getAbsolutePath() + "\\src\\tankAIImages\\"+"face_to_bottom.png"
        );
        
    }
    
    public void update(){
        //0 is Left
        if (direction == 0) {
            this.size = sizeLeftRight;
            tankIAIcon = face_to_left;
        }
        //1 is Right
        if (direction == 1) {
            this.size = sizeLeftRight;
            tankIAIcon = face_to_right;
        }
        //2 is Up
        if (direction == 2) {
            this.size = sizeupDown;
            tankIAIcon = face_to_up;
        }
        //0 is Down
        if (direction == 3) {
            this.size = sizeupDown;
            tankIAIcon = face_to_down;
        }

        checkBounds();
        updateDirection();
        fireBulletAI();
    }

    public void updateDirection(){
        if (tankDirectionChange < 0) {
            direction = RandomRange.randomInt(0, 3);
            tankAIVelecity = RandomRange.randomInt(1, 5);
            tankDirectionChange = RandomRange.randomInt(30, 200);
        }
        
        tankDirectionChange--;
    }

    ArrayList<BulletAI> bulletsAI = new ArrayList<BulletAI>();

    int timeTofire = 50;
    int timeTofireReset = 200;

    public void fireBulletAI(){
        timeTofire--;
        
        if (timeTofire  < 2) {
    
            for (int i = 0; i < bulletAILimit; i++) {
                BulletAI bullet = new BulletAI(this);
                bulletsAI.add(bullet);
                
                bullet.velectiy = RandomRange.randomInt(10, 20);
                timeTofire = RandomRange.randomInt(50, timeTofireReset);
            }
        }
        
        for (int i = 0; i < bulletsAI.size(); i++) {
            bulletsAI.get(i).update();
        }
        
    }
    
    public void checkBounds(){
        //0 is left
        if (direction == 0) {
            if (postion.x > 0) {
                postion.x -= tankAIVelecity;
            }
        } 
        //1 is right
        if (direction == 1) {
            if (postion.x + size.width < width) {
                postion.x += tankAIVelecity;
            }
        }
        //2 is Up
        if (direction == 2) {
            if (postion.y > 0) {
                postion.y -= tankAIVelecity;
            }
        }
        //3 is down
        if (direction == 3) {
            if (postion.y + size.height < height) {
                postion.y += tankAIVelecity;
            }
        }
    }
}
