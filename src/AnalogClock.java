/**
 * Author: Devon
 * 9-28-15
 */
package analogclock;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AnalogClock extends JFrame {
    static int hour;
    static int min;
    static int sec;
    
    public static void main(String[] args) {
        AnalogClock clock = new AnalogClock();
        String input = JOptionPane.showInputDialog("What time do you want to display (HH:MM:SS)? (blank for current time)");
        if (input.equals("")){
            Calendar C = new GregorianCalendar();
            hour = C.get(Calendar.HOUR);
            min = C.get(Calendar.MINUTE);
            sec = C.get(Calendar.SECOND);
        }
        else{
            String[] time = input.split(":");//Split time by the colons
            hour = Integer.parseInt(time[0]);
            min = Integer.parseInt(time[1]);
            sec = Integer.parseInt(time[2]);
        }
        StillClock stillClock = new StillClock(hour,min,sec);
        clock.setTitle("Analog_Clock");
        clock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clock.setLocationRelativeTo(null);
        clock.setSize(375, 400);
        clock.add(stillClock);
        clock.setVisible(true);
        try{
            while (true){
                stillClock.setSecond(stillClock.getSecond()+1);
                TimeUnit.SECONDS.sleep(1);
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(AnalogClock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}//End class AnalogClock
