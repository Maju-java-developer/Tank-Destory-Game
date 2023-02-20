package tank_game_project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class MainApp extends JFrame{
    public MainApp(){
        intiComponends();
    }
        
    public void intiComponends(){
        setTitle("CURRENT LEVEL: "+Constants.currentLevel);
        setSize(1280, 720);
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        tank_Generator = new Tank_Generator(getWidth(), getHeight());
        tank_Generator.setBackground(new Color(0, 0, 0));
        tank_Generator.intiialize();
        
        addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                //LeftAction:
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    tank_Generator.tank.direction = 0;
                    if (tank_Generator.tank.postion.x > 0) {
                        tank_Generator.tank.postion.x -= Constants.myTankSpeed;
                    }
                }
                //LeftAction:

                //RightAction:
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    tank_Generator.tank.direction = 1;
                    if ((tank_Generator.tank.postion.x + tank_Generator.tank.size.width) < tank_Generator.width) {
                        tank_Generator.tank.postion.x += Constants.myTankSpeed;
                    }
                }
                //RightAction:

                //upAction:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    tank_Generator.tank.direction = 2;
                    if (tank_Generator.tank.postion.y > 0) {
                        tank_Generator.tank.postion.y -= Constants.myTankSpeed;
                    }
                }
                //upAction:

                //DownAction:
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    tank_Generator.tank.direction = 3;
                    if ((tank_Generator.tank.postion.y + tank_Generator.tank.size.height) < tank_Generator.height) {
                        tank_Generator.tank.postion.y += Constants.myTankSpeed;
                    }
                }
                //DownAction:
                
                //SpaceAction:
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    tank_Generator.tank.fireBullet();
                 }
                //SpaceAction:
                repaint();
                revalidate();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        add(tank_Generator);
    }
    
    Tank_Generator tank_Generator = null;
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        new MainApp().setVisible(true);
    }
}
