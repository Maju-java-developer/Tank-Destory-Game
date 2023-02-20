package tank_game_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import processing.core.PVector;

public class Tank_Generator extends JPanel{
    int width;
    int height;

    int delay = 10;
    Timer updateTimer = null;
    
    Tank tank;
    ArrayList<TankAI> tankAIs = new ArrayList<TankAI>();

    BackgroundImage backgroundImage;
    MyTankFuel myTankFuel;
    
    GameOverDialog gameOverDialog;
    
    ImageIcon explosion = new ImageIcon();
                                
    public Tank_Generator(){}
    public Tank_Generator(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void intiialize(){
        gameSetup();
        updateGame();
    }
    
    public void gameSetup(){

        explosion = Defaults.rescaleImage(70, 70, 
                new File("").getAbsoluteFile() + "\\src\\BackGroundImages\\" + "explosion.png"
        );
        
        myTankFuel = new MyTankFuel(this);
        
        backgroundImage = new BackgroundImage(this);
        
        tank = new Tank();
        
        createEnemies();
    }
    
    boolean isCreatingEnemy = true;
    
    public void createEnemies(){
        if (isCreatingEnemy) {
            for (int i = 0; i < Constants.currentEnemies; i++) {
                TankAI tankAI = new TankAI(width, height);
                tankAIs.add(tankAI);
            }
            isCreatingEnemy = false;
        }
    }

    public void fuelUpdate(){
        
        float fueldistance = myTankFuel.postion.dist(tank.postion);
        
        if (fueldistance <= myTankFuel.widthHeight) {
            Constants.fuel = 100;

            Constants.fuelNumber = RandomRange.randomInt(0, Constants.fuelImages.length - 1);
            
            myTankFuel = new MyTankFuel(this);
            myTankFuel.fuelCreateTime = RandomRange.randomInt(600, 900);
            myTankFuel.postion = new PVector(
                    RandomRange.randomFloat(50, width - 200),
                    RandomRange.randomFloat(50, RandomRange.randomFloat(50, height - 100))
            );
            repaint();
            revalidate();
        }
    }
    
    public void updateGame(){
            updateTimer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                backgroundImage.Levelupdate();

                if (Constants.fuel < 30) {
                    myTankFuel.fuelCreateUpdate();
                }
                
                if (Constants.fuel > 0) {

                    fuelUpdate();
                    tank.update();
                    
                    createEnemies();
                    for (int i = 0; i < tankAIs.size(); i++) {
                        tankAIs.get(i).update();
                    }
                    
                    repaint();
                    revalidate();

                }else  {
                    updateTimer.stop();
                    repaint();
                    revalidate();
                }
            }
        });
        updateTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D graphics2D = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics2D.addRenderingHints(hints);

        // DrawbackGroundImage:
        graphics2D.drawImage(
                backgroundImage.backGroundIcon.getImage(), 
                (int)backgroundImage.postion.x, 
                (int)backgroundImage.postion.y, 
                backgroundImage.backgroundsize.width, 
                backgroundImage.backgroundsize.height, 
                null
        );
        // DrawbackGroundImage:

        if (Constants.fuel > 0) {
            // DrawFuel For My Tank:
            graphics2D.drawImage(
                    myTankFuel.fuelImage.getImage(), 
                    (int)myTankFuel.postion.x, 
                    (int)myTankFuel.postion.y, 
                    myTankFuel.feulsize.width, 
                    myTankFuel.feulsize.height, 
                    null
            );
            // DrawFuel For My Tank:
            
            //DrawImage For Over Tank:
            graphics2D.drawImage(
                    tank.tankImage.getImage(), 
                    (int)tank.postion.x,
                    (int)tank.postion.y,
                    tank.size.width,
                    tank.size.height,
                    null
            );
            // DrawImage For Over Tank:

            //DrawBulletImage For Over Tank:
            for (int i = 0; i < tank.bullets.size(); i++) {
                graphics2D.drawImage(
                        tank.bullets.get(i).bulletIcon.getImage(), 
                        (int)tank.bullets.get(i).postion.x+10, 
                        (int)tank.bullets.get(i).postion.y+10, 
                        tank.bullets.get(i).bulletIcon.getIconWidth(), 
                        tank.bullets.get(i).bulletIcon.getIconHeight(), 
                        null
                );

                try {
                    if (tank.bullets.get(i).postion.x < 0) {
                        tank.bullets.remove(i);
                    }

                    if (tank.bullets.get(i).postion.x > width - 10) {
                        tank.bullets.remove(i);
                    }

                    if (tank.bullets.get(i).postion.y < 0) {
                        tank.bullets.remove(i);
                    }

                    if (tank.bullets.get(i).postion.y > height - 10) {
                        tank.bullets.remove(i);
                    }

                } catch (IndexOutOfBoundsException e) {
                }

            }

            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString("Bullets: " + tank.bullets.size(), 20, 20);
            //DrawBulletImage For Over Tank:

            //DrawImage For Enemies Tanks:
            for (int i = 0; i < tankAIs.size(); i++) {
                graphics2D.drawImage(
                        tankAIs.get(i).tankIAIcon.getImage(), 
                        (int) tankAIs.get(i).postion.x,
                        (int) tankAIs.get(i).postion.y,
                        tankAIs.get(i).size.width, 
                        tankAIs.get(i).size.height, 
                        null);
            }
            //DrawImage For Enimies Tanks:

            //DrawBulletImage For Over Tank:
            for (int i = 0; i < tankAIs.size(); i++) {
                for (int j = 0; j < tankAIs.get(i).bulletsAI.size(); j++) {
                    graphics2D.drawImage(
                            tankAIs.get(i).bulletsAI.get(j).bulletAI.getImage(), 
                            (int) tankAIs.get(i).bulletsAI.get(j).postion.x,
                            (int) tankAIs.get(i).bulletsAI.get(j).postion.y,
                            tankAIs.get(i).bulletsAI.get(j).bulletAI.getIconWidth(), 
                            tankAIs.get(i).bulletsAI.get(j).bulletAI.getIconHeight(), 
                            null);
                }
            }
            //DrawBulletImage For Over Tank:

            // Check If I Hit Enemy With My Fire Bullet
            try {
                for (int i = 0; i < tankAIs.size(); i++) {
                    for (int j = 0; j < tank.bullets.size(); j++) {
                        float distance = tankAIs.get(i).postion.dist(tank.bullets.get(j).postion);
                        if (distance <= (tankAIs.get(i).size.height) || distance <= (tankAIs.get(i).size.width)) {

                            Constants.score += Constants.scoreLimit;
                            Constants.tankdis += 1;

                            graphics2D.drawImage(
                                    explosion.getImage(), 
                                    (int) tankAIs.get(i).postion.x, 
                                    (int) tankAIs.get(i).postion.y, 
                                    null
                            );
                            
                            tankAIs.remove(i);
                            tank.bullets.remove(j);
                        }
                    }
                }
                            
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Hahro Bharo Jo Message Ahya Ma Don't worry:");
            }

            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("Sogue UI Light", 0, 13));
            graphics2D.drawString("REMAIN TANKS: " + tankAIs.size(), 20, 40);

            if (tankAIs.size() == 0) {
                levelUp();
            }
            
            // Check If I Hit Enemy With My Fire Bullet

            // Check If With Enemies Bullets Hit My Tank:
            for (int i = 0; i < tankAIs.size(); i++) {
                for (int j = 0; j < tankAIs.get(i).bulletsAI.size(); j++) {
                    float distance = tank.postion.dist(tankAIs.get(i).bulletsAI.get(j).postion);
                    if (distance <= (tank.tankHeight) || distance <= (tank.tankWidth)) {

                        Constants.fuel -= RandomRange.randomInt(Constants.fuelRandMin, Constants.fuelRandMiX);
                        
                        graphics2D.drawImage(
                                explosion.getImage(), 
                                (int)tank.postion.x,
                                (int)tank.postion.y,
                                null
                        );

                        tankAIs.get(i).bulletsAI.remove(j);
                    }
                }
            }
            
            for (int i = 0; i < Constants.fuel; i++) {

                graphics2D.fillRect(((width / 2 )- 200 + i * 4), 20, 2, 25);
                graphics2D.drawString("FUEL %" +Constants.fuel, 20, 60);

                if (Constants.fuel < 100) {
                    graphics2D.setColor(Color.GREEN);
                }
                if (Constants.fuel < 50) {
                    graphics2D.setColor(Color.YELLOW);
                }
                if (Constants.fuel < 30) {
                    graphics2D.setColor(Color.RED);
                }
            }
            // Check If With Enemies Bullets Hit My Tank:
        }else {
            GameOver();
        }
        
//        graphics2D.setColor(Color.WHITE);
//        graphics2D.setFont(new Font("Sogue UI Light", 0, 20));
//        graphics2D.drawString("SCORE " +Constants.score, (width - 150), 30);
//        
//        graphics2D.setColor(Color.WHITE);
//        graphics2D.setFont(new Font("Sogue UI Light", 0, 20));
//        graphics2D.drawString("DISTORY " +Constants.tankdis, (width - 150), 60);
    }
    
    
    boolean isGameOver = true;

    public void GameOver(){
        if (isGameOver) {
            gameOverDialog = new GameOverDialog(this);
            gameOverDialog.setTitle("YOU KILLED LEVEL: " +Constants.currentLevel);
            gameOverDialog.setVisible(true);

            gameOverDialog.closeBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            gameOverDialog.playAgainBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    Constants.fuel = 100;
                    Constants.score = 0;
                    Constants.tankdis = 0;
                    
                    Constants.currentLevel = 0;
                    Constants.currentEnemies = 5;

                    updateTimer.start();

                    isGameOver = true;
                    gameOverDialog.dispose();
                    
                    repaint();
                    revalidate();
                }
            });
            isGameOver = false;
        }
    }

    boolean isLevelUp = true;
    JDialog nextLevel = new JDialog();

    int resetingLevel = 999999999;
    public void levelUp() {
        if (isLevelUp) {
            nextLevel.setTitle("Congrats");
            nextLevel.setSize(width-(width/2), height-(height/2));
            nextLevel.setLayout(null);
            nextLevel.setLocationRelativeTo(null);

            JPanel btnPanel = new JPanel(new GridLayout(1, 1));
            
            JButton PreviousLvlBtn = new JButton("PREVIOUS LEVEL");
            btnPanel.add(PreviousLvlBtn);
            
            JPanel LvlCmpltTitle = new JPanel(new GridLayout(1, 1));
            JLabel lvlCmpltTitleLbl = new JLabel("COMPLETE LEVEL "+Constants.currentLevel);
            
            JLabel scoreLbl = new JLabel("  "+Constants.score);
            JLabel tankDis = new JLabel("" + Constants.tankdis);
            
            btnPanel.setBounds(0, height - (height / 2) - 75, width - (width /2) - 15, 35);

            //Action For NextLevel:
            JButton nextLvlBtn = new JButton("NEXT LEVEL");
            nextLvlBtn.setBounds(20, 20, 200, 40);
            nextLvlBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetingLevel = 999999999;
                    if (Constants.currentLevel > Constants.backgroundImages.length) {
                        JOptionPane.showMessageDialog(null, "No more levels to continue.");
                        System.exit(0);
                    }else{
                        Constants.currentEnemies += 5;
                        Constants.currentLevel += 1;
                        
                        isCreatingEnemy = true;
                        
                        lvlCmpltTitleLbl.setText("COMPLETE LEVEL " + Constants.currentLevel);
                        isLevelUp = true;
                        
                        while (resetingLevel > 0) {
                            resetingLevel--;
                        }
                    }

                    nextLevel.dispose();
                }
            });
            // Action For NextLevel:
            PreviousLvlBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (Constants.currentLevel == -0) {
                        JOptionPane.showMessageDialog(null, "No more levels to continue Prevoius Level.");
                        System.exit(0);
                    }else{
                        Constants.currentEnemies -= 5;
                        Constants.currentLevel -= 1;
                        
                        isCreatingEnemy = true;
                        
                        lvlCmpltTitleLbl.setText("COMPLETE LEVEL: "+Constants.currentLevel);
                        isLevelUp = true;
                    }
                    
                    nextLevel.dispose();
                }
            });
            
            String rootPath = new File("").getAbsolutePath() + "\\src\\BackGroundImages\\";
            
            // headerPanel 
            LvlCmpltTitle.setBounds(0, 0, width - (width / 2), 50);
            LvlCmpltTitle.setBackground(new Color(20, 30, 50));
            
            lvlCmpltTitleLbl.setFont(new Font("Sogue UI Light", 0, 18));
            lvlCmpltTitleLbl.setForeground(Color.WHITE);
            lvlCmpltTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
            // headerPanel 

            // Socre And TankKill:
            scoreLbl.setBounds(200, 100, 150, 50);
            scoreLbl.setIcon(Defaults.rescaleImage(30, 30, rootPath + "Score.png"));
            scoreLbl.setIconTextGap(20);
            
            tankDis.setBounds(180, 150, 150, 50);
            tankDis.setIcon(Defaults.rescaleImage(70, 70, rootPath + "tankDistory.png"));
            tankDis.setIconTextGap(20);
            // Socre And TankKill:
            
            nextLevel.add(btnPanel);
            btnPanel.add(nextLvlBtn);
            
            nextLevel.add(LvlCmpltTitle);
            LvlCmpltTitle.add(lvlCmpltTitleLbl);

            nextLevel.add(scoreLbl);
            nextLevel.add(tankDis);
            
            isLevelUp = false;
            
            nextLevel.setVisible(true);
        }
    }    
}
