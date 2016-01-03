package analogclock;

import java.awt.*;
import javax.swing.*;

class StillClock extends JPanel{
    private int widthX = getSize().width;
    private int heightY = getSize().height;
    private static int radius;
    private static double angle = (2*Math.PI/12); //Angles for Math.sin/cos are in rad.
    public static int hour = 12;
    public static int min = 0;
    public static int sec = 0;
    
    //Constructors
    public StillClock(){
        this(12, 0, 0);
    }
    
    public StillClock(int hour, int min, int sec){
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }
    
    //Functionality of the clock
    public void changeTime(int hour, int min, int sec){
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        repaint();
    }
    
    public int getHour(){
        return this.hour;
    }
    
    public int getMinute(){
        return this.min;
    }
    
    public int getSecond(){
        return this.sec;
    }
    
    public void setHour(int hour){
        this.hour = hour;
        repaint();
    }
    
    public void setMinute(int min){
        this.min = min;
        repaint();
    }
    
    public void setSecond(int sec){
        if (sec < 60){
            this.sec = sec;
        }
        else{
            this.sec = 0;
            this.min++;
            if (this.min == 60){
                this.min = 0;
                this.hour++;
            }
        }
        repaint();
    }
    
    //Actually draw the clock
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //Draw the clock
        radius = (int) Math.min(getSize().width, getSize().height)/2;
        g.drawOval(0, 0, radius*2, radius*2);
        //the center of the clock is at (radius, radius)
        
        FontMetrics fm = g.getFontMetrics();
        for (int i = 1; i<13; i++){
            g.drawString(Integer.toString(i),
                    (int) (0.9*radius*Math.cos(angle*i-3*angle) + radius - fm.stringWidth(Integer.toString(i))/2),
                    (int) (0.9*radius*Math.sin(angle*i-3*angle) + radius + fm.stringWidth(Integer.toString(i))/2));
        }
        
        //Make the second hand
        g.drawLine(radius, radius,
                (int) (radius + 0.8*radius*Math.cos((2*Math.PI/60)*(-sec + 15))),
                (int) (radius - 0.8*radius*Math.sin((2*Math.PI/60)*(-sec + 15))));
        
        //Make the minute hand
        g.drawLine(radius, radius,
                (int) (radius + 0.7*radius*Math.cos((2*Math.PI/60)*(-min + 15))),
                (int) (radius - 0.7*radius*Math.sin((2*Math.PI/60)*(-min + 15))));
        
        //Make the hour hand
        g.drawLine(radius, radius,
                (int) (radius + 0.5*radius*Math.cos((2*Math.PI/12)*(-hour + 3))),
                (int) (radius - 0.5*radius*Math.sin((2*Math.PI/12)*(-hour + 3))));
        
    }//End paintComponent
    
}//End class StillClock
