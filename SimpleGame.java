
package simplegame;

//import java.awt.Color;
import javax.swing.JFrame;


public class SimpleGame extends JFrame
{

      SimpleGame()
      {
         
      }
    public static void main(String[] args)  throws Exception
    {
        JFrame frame=new JFrame();
        
        gamePlay game=new gamePlay();
        frame.setVisible(true);
        frame.setBounds(350,50, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Simple Game");
        
    
        
      
     
        frame.add(game);
        
    }
    
}
