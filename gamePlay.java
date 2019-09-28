
package simplegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javafx.scene.shape.Rectangle;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;


public class gamePlay extends JPanel implements KeyListener,ActionListener
{
    SimpleGame sg=new SimpleGame();
   private boolean play;
   private int score=0;
       int score_clone=0;
   
   private Timer timer;
   private int delay=8;
   
   private int playerX=310;
   private int intruder=310;
   
   private boolean check=true;
   private boolean lock=true;
    private  boolean repaintCheck=true;
   private int game_result=-1;
   
   private int ballposX=ballpos();
   private int ballposY=ballpos();
   private int ballXdir=-2;
   private int ballYdir=-4;

    public gamePlay()
    {
   // System.out.println(ballposX+"   "+ballposY);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer=new Timer(delay, this);
       timer.start();
       
        
    }

   public void paint(Graphics g)
   {
     
   g.setColor(Color.black);
      g.fillRect(1, 1,692, 592);
      
      g.setColor(Color.yellow);
      g.fillRect(0, 0, 3, 592);
      g.fillRect(0,0,692,3);
      g.fillRect(692, 0, 3, 592);
      
      g.fillRect(50, 50, 250,10);
       g.fillRect(400, 50, 250,10);
       g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
       g.drawString("Score:"+score, 500, 30);
        if(game_result==0)
      {
          g.setColor(Color.BLUE);
          g.setFont(new Font("serif",Font.BOLD,72));
          g.drawString("Game Over", 180, 280);
           g.setColor(Color.magenta);
          g.setFont(new Font("serif",Font.BOLD,36));
          g.drawString("Your Score:"+score,260,380);
           g.setColor(Color.red);
          g.setFont(new Font("serif",Font.BOLD,48));
          g.drawString("Amit Gauatam",220,500);
            g.setColor(Color.pink);
              g.setColor(Color.white);
          g.fillOval(227,444,20,20);
          g.setFont(new Font("serif",Font.BOLD,20));
          g.drawString("@developer",220,530);
      }
     
      if(game_result==0)
          g.setColor(Color.BLACK);
      else
      g.setColor(Color.white);
      g.fillOval(ballposX, ballposY, 20,20);
      
        g.setColor(Color.GREEN);
        if(game_result==0)
              g.fillRect(310, 550, 100,10);
        else
      g.fillRect(playerX, 550, 100,10);
       g.fillRect(intruder, 65, 100,10);
     
      
      g.dispose();
   }
   @Override
    public void actionPerformed(ActionEvent e)
    {
        if(lock){
       timer.start();
      
       if(play)
       {
       
          score_clone++;
            
            if(score_clone==100)
            {
               score=score+1;
               score_clone=0;
            }
            
          if(check)
        {
         intruder=intruder-2;
         if(intruder==10)
             check=false;
       }
       else
       {
           intruder=intruder+2;
           if(intruder==600)
               check=true;
       }
          
          
          
       
           Rectangle rect=new Rectangle(ballposX,ballposY,20,20);
         
             
           if(rect.intersects(new Rectangle(50,50,250,10))
                 || rect.intersects(new Rectangle(400,50,250,10)) ||
             rect.intersects(new Rectangle(intruder,70,100,10)  ) || rect.intersects(new Rectangle(playerX,550,100,10)  ) )
           {
             
               
              ballYdir=-ballYdir;
           }
            
          ballposX=ballposX+ballXdir;
          ballposY=ballposY+ballYdir;
          if(ballposY<0)
          {
              
              score=score+5;
             ballposX=ballpos();
             ballposY=ballpos();
              ballXdir=-ballXdir;
          }
          else if(ballposY>550)
                {
                    game_result=0;
                    lock=false;
                }
          else
          {
              if(ballposX<0 || ballposX>=680)
              {
                 ballXdir=-ballXdir;
              }
           }
       }
          
       if(repaintCheck){
       repaint();
          if(game_result==0 )
              repaintCheck=false;
       }
       
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println("Key pressed");
       if(e.getKeyCode()==KeyEvent.VK_RIGHT)
       {
           System.out.println("Right Clicked");
          if(playerX>=600)
          {
            playerX=600;
          }
          else
          {
            MoveRight();
          }
       }
       
       else if(e.getKeyCode()==KeyEvent.VK_LEFT)
       {
       if(playerX<=10)
          {
            playerX=10;
          }
          else
          {
            MoveLeft();
          }
       }
    /*   else if(e.getKeyCode()==KeyEvent.VK_ENTER)
       {
           lock=true;
           game_result=-1;
           repaintCheck=true;
           play=true;
           timer.start();
           repaint();
       }*/
       else
       {
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    

    private void MoveLeft() {
        play=true;
        playerX=playerX-20;
        System.out.println("move Left");
    }

    private void MoveRight()
    {
        play=true;
        playerX=playerX+20;
        System.out.println("Move Right");
    }

    private int ballpos()
    {
         Random ran=new Random();
         return ran.nextInt(200)+200;
    }
       
}
