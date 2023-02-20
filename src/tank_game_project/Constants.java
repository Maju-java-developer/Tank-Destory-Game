package tank_game_project;

import javax.swing.ImageIcon;

public class Constants {

    //Fuel Setting:
    public static int fuel = 100;
    public static int fuelRandMin = 1;
    public static int fuelRandMiX = 5;
    //Fuel Setting:

    //Score Setting:
    public static int score = 0;
    public static int scoreLimit = RandomRange.randomInt(8, 15);
    public static int tankdis = 0;
    //Score Setting:

    public static int myTankSpeed = 10;

    // Level Systems:
    public static int currentLevel = 0;
    public static int currentEnemies = 20;
    public static ImageIcon[] backgroundImages = new ImageIcon[10];
    // Level Systems:
    
    // Feul Section:
    public static ImageIcon[] fuelImages = new ImageIcon[6];
    public static int fuelNumber = 5;
    // Feul Section:
}