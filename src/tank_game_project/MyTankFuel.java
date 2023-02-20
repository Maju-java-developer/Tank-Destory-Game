package tank_game_project;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import processing.core.PVector;

public class MyTankFuel {
    
    PVector postion;
    
    int widthHeight = 50;
    
    Dimension feulsize = new Dimension(widthHeight, widthHeight);
    
    ImageIcon fuelImage = new ImageIcon("");

    public MyTankFuel(Tank_Generator tank_Generator) {
        postion = new PVector(
                RandomRange.randomFloat(50, tank_Generator.width - 300), 
                RandomRange.randomFloat(50, tank_Generator.height - 200));
        String rootpath = new File("").getAbsolutePath() +"\\src\\fuelImages\\" ;

        for (int i = 0; i < Constants.fuelImages.length; i++) {
            Constants.fuelImages[i] = Defaults.rescaleImage(widthHeight, widthHeight, rootpath + "PetrolPump_"+ i +".png");
        }
    }
     
    int fuelCreateTime = 200;
    
    public void fuelCreateUpdate(){
        fuelCreateTime--;

        if (fuelCreateTime < 0) {
            
            if (Constants.fuelNumber == 0) {
                fuelImage = Constants.fuelImages[0];
            }
            
            if (Constants.fuelNumber == 1) {
                fuelImage = Constants.fuelImages[1];
            }
            
            if (Constants.fuelNumber == 2) {
                fuelImage = Constants.fuelImages[2];
            }
            
            if (Constants.fuelNumber == 3) {
                fuelImage = Constants.fuelImages[3];
            }
            
            if (Constants.fuelNumber == 4) {
                fuelImage = Constants.fuelImages[4];
            }
            
            if (Constants.fuelNumber == 5) {
                fuelImage = Constants.fuelImages[5];
            }
        }
    }
}
