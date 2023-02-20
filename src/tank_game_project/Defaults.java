package tank_game_project;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Defaults {
    
    public static ImageIcon rescaleImage(int posX, int posY, String  path){
        ImageIcon icon = new ImageIcon(
                new ImageIcon(path).
                        getImage().
                        getScaledInstance(
                                posY,
                                posY, 
                                Image.SCALE_SMOOTH)
        );
        
        return icon;
        
    }

}
