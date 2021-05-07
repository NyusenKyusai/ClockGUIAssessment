/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.Date;
import javax.swing.JSpinner;

/**
 *
 * @author Jonah
 */
public class Alarm {

    protected Date date;
    long priority;
    String month;
    String dateOfMonth;
    String hours;
    String minutes;
    String seconds;

    public Alarm(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    
    public long getPriority(JSpinner spinner) {
        priority = 0;
        
        if (((Date) spinner.getValue()).getMonth() + 1 < 10) {
            month = "0" + String.valueOf(((Date) spinner.getValue()).getMonth() + 1);
        } else {
            month = String.valueOf(((Date) spinner.getValue()).getMonth() + 1);
        }
        
        if (((Date) spinner.getValue()).getDate() < 10) {
            dateOfMonth = "0" + String.valueOf(((Date) spinner.getValue()).getDate());
        } else {
            dateOfMonth = String.valueOf(((Date) spinner.getValue()).getDate());
        }
        
        if (((Date) spinner.getValue()).getHours() < 10) {
            hours = "0" + String.valueOf(((Date) spinner.getValue()).getHours());
        } else {
            hours = String.valueOf(((Date) spinner.getValue()).getHours());
        }
        
        if (((Date) spinner.getValue()).getMinutes() < 10) {
            minutes = "0" + String.valueOf(((Date) spinner.getValue()).getMinutes());
        } else {
            minutes = String.valueOf(((Date) spinner.getValue()).getMinutes());
        }
        
        if (((Date) spinner.getValue()).getSeconds() < 10) {
            seconds = "0" + String.valueOf(((Date) spinner.getValue()).getSeconds());
        } else {
            seconds = String.valueOf(((Date) spinner.getValue()).getSeconds());
        }
        
        priority = Long.parseLong(String.valueOf(((Date) spinner.getValue()).getYear()) 
                + month
                + dateOfMonth
                + hours
                + minutes
                + seconds
        );
        
        return priority;
    }

    @Override
    public String toString() {
        return getDate().toString();
    }
}
