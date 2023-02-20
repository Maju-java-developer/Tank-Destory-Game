package tank_game_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverDialog extends JDialog{

    String rootPath = new File("").getAbsolutePath() +"\\src\\BackGroundImages\\";

    public GameOverDialog(){}
    public GameOverDialog(Tank_Generator tank_Generator){
        
        int width = tank_Generator.width - (tank_Generator.width / 2);
        int height = tank_Generator.height - (tank_Generator.height / 2);
        
        setSize(width, height);
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        mainPanel.setBackground(new Color(255, 255, 255));

        gameOverPanel.setBounds(0, 0, width - 15, 50);
        gameOverPanel.setBackground(new Color(20, 30, 50));
        
        gameOverLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        gameOverLbl.setFont(new Font("Sougu UI Light", 0, 18));
        gameOverLbl.setForeground(Color.WHITE);
        
        scoreLbl.setIcon(Defaults.rescaleImage(30, 30, rootPath + "Score.png"));
        scoreLbl.setFont(new Font("Sogue UI Light", 0, 18));
        scoreLbl.setBounds(width / 3, height / 4, 200, 50);
        scoreLbl.setIconTextGap(20);
        
        tankdisLbl.setIcon(Defaults.rescaleImage(60, 60, rootPath + "tankDistory.png"));
        tankdisLbl.setBounds(scoreLbl.getX() - 30, scoreLbl.getY() + 100, 200, 50);
        tankdisLbl.setFont(new Font("Sogue UI Light", 0, 18));
        tankdisLbl.setForeground(Color.RED);
        tankdisLbl.setIconTextGap(20);

        btnPanel.setBounds(0, getHeight() - 80, getWidth() - 15, 40);
        
        add(mainPanel);
        
        mainPanel.add(gameOverPanel);
        gameOverPanel.add(gameOverLbl);
        
        mainPanel.add(scoreLbl);
        mainPanel.add(tankdisLbl);
        mainPanel.add(btnPanel);
        
        btnPanel.add(playAgainBtn);
        btnPanel.add(closeBtn);
    }
    
    public JPanel mainPanel = new JPanel(null);
    
    public JPanel gameOverPanel = new JPanel(new GridLayout(1, 1));
    public JLabel gameOverLbl = new JLabel("GAME OVER:");
    
    public JLabel scoreLbl = new JLabel(" "+Constants.score);
    public JLabel tankdisLbl = new JLabel(" "+Constants.tankdis);

    public JPanel btnPanel = new JPanel(new GridLayout(1, 1));
    
    public JButton playAgainBtn = new JButton("PLAY AGIAN");
    public JButton closeBtn = new JButton("CLOSE");
}
