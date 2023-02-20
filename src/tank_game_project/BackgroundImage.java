package tank_game_project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class BackgroundImage {
    int width; 
    int height;

    PVector postion;
    
    Dimension backgroundsize;
    
    ImageIcon backGroundIcon = new ImageIcon("");
    
    public BackgroundImage(Tank_Generator tank_Generator){
        
        postion = new PVector(0, 0);
        
        this.width = tank_Generator.width;
        this.height = tank_Generator.height;
        
        backgroundsize = new Dimension(this.width, this.height);

        String rootpath = new File("").getAbsolutePath() +"\\src\\BackGroundImages\\";
                    
        for (int i = 0; i < Constants.backgroundImages.length; i++) {
            Constants.backgroundImages[i] = Defaults.rescaleImage(this.width, this.height, rootpath +"level_" + i + ".jpg");
        }
        
    }
    
    public void Levelupdate(){
        //Level Zero Section:
        if (Constants.currentLevel == 0) {
            backGroundIcon = Constants.backgroundImages[0];
        }
        
        //Level One Section:
        if (Constants.currentLevel == 1) {
            backGroundIcon = Constants.backgroundImages[1];
        }
        
        //Level Two Section:
        if (Constants.currentLevel == 2) {
            backGroundIcon = Constants.backgroundImages[2];
        }
        
        //Level Three Section:
        if (Constants.currentLevel == 3) {
            backGroundIcon = Constants.backgroundImages[3];
        }
        
        //Level Four Section:
        if (Constants.currentLevel == 4) {
            backGroundIcon = Constants.backgroundImages[4];
        }
        
        //Level Five Section:
        if (Constants.currentLevel == 5) {
            backGroundIcon = Constants.backgroundImages[5];
        }
        
        //Level Six Section:
        if (Constants.currentLevel == 6) {
            backGroundIcon = Constants.backgroundImages[6];
        }

        //Level Seven Section:
        if (Constants.currentLevel == 7) {
            backGroundIcon = Constants.backgroundImages[7];
        }

        //Level Eight Section:
        if (Constants.currentLevel == 8) {
            backGroundIcon = Constants.backgroundImages[8];
        }
        
        //Level Nine Section:
        if (Constants.currentLevel == 9) {
            backGroundIcon = Constants.backgroundImages[9];
        }
        
    }
}
