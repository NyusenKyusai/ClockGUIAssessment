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
    
    public String getYear() {
        return String.valueOf(date.getYear() + 1900);
    }
    
    public String getMonth() {
        if ((date.getMonth() + 1) < 10) {
            return "0" + String.valueOf(date.getMonth() + 1);
        } else {
            return String.valueOf(date.getMonth() + 1);
        }
    }
    
    public String getDay() {
        if (date.getDate() < 10) {
            return "0" + String.valueOf(date.getDate());
        } else {
            return String.valueOf(date.getDate());
        }
    }
    
    public String getHours() {
        if (date.getHours() < 10) {
            return "0" + String.valueOf(date.getHours());
        } else {
            return String.valueOf(date.getHours());
        }
    }
    
    public String getMinutes() {
        if (date.getMinutes() < 10) {
            return "0" + String.valueOf(date.getMinutes());
        } else {
            return String.valueOf(date.getMinutes());
        }
    }
    
    public String getMinutesPlus1() {
        if (date.getMinutes() < 10) {
            return "0" + String.valueOf(date.getMinutes() + 1);
        } else {
            return String.valueOf(date.getMinutes() + 1);
        }
    }
    
    public String getCalendarString(int i) {
        String calString = "BEGIN:VEVENT\r\n"
                + "UID:" + i + "00000\r\n"
                + "DTSTAMP:" + getYear() + getMonth() + getDay() + "T" + getHours() + getMinutes() + "00Z\r\n"
                + "DTSTART:" + getYear() + getMonth() + getDay() + "T" + getHours() + getMinutes() + "00Z\r\n"
                + "DTEND:" + getYear() + getMonth() + getDay() + "T" + getHours() + getMinutesPlus1() + "00Z\r\n"
                + "SUMMARY:Alarm" + (i + 1) + "\r\n"
                + "END:VEVENT\r\n";
        
        return calString;
    }
    
    public long countdown(Date officialDate) {
        long countdownTime = 0;
        
        if (date.getTime() - officialDate.getTime() < 0) {
            countdownTime = 0;
        } else {
            countdownTime = date.getTime() - officialDate.getTime();
        }
        
        return countdownTime / 1000;
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
        
        priority = Long.parseLong(String.valueOf(((Date) spinner.getValue()).getYear()) 
                + month
                + dateOfMonth
                + hours
                + minutes
        );
        
        return priority;
    }
    
    public long getPriority(Date date) {
        priority = 0;
        
        if (date.getMonth() + 1 < 10) {
            month = "0" + String.valueOf(date.getMonth() + 1);
        } else {
            month = String.valueOf(date.getMonth() + 1);
        }
        
        if (date.getDate() < 10) {
            dateOfMonth = "0" + String.valueOf(date.getDate());
        } else {
            dateOfMonth = String.valueOf(date.getDate());
        }
        
        if (date.getHours() < 10) {
            hours = "0" + String.valueOf(date.getHours());
        } else {
            hours = String.valueOf(date.getHours());
        }
        
        if (date.getMinutes() < 10) {
            minutes = "0" + String.valueOf(date.getMinutes());
        } else {
            minutes = String.valueOf(date.getMinutes());
        }
        
        priority = Long.parseLong(String.valueOf(date.getYear()) 
                + month
                + dateOfMonth
                + hours
                + minutes
        );
        
        return priority;
    }

    @Override
    public String toString() {
        return getDate().toString();
    }
}
