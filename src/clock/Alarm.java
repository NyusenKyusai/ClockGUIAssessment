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
    
    // Date variable that takes the date when the class is initialised
    protected Date date;
    // Long variable to hold the priority that is created
    long priority;
    // String variables to hold strings othe sections of the time
    String month;
    String dateOfMonth;
    String hours;
    String minutes;
    String seconds;

    // Constructor for the data class
    public Alarm(Date date) {
        // Takes the date that was put into the constructor and set to the global date variable
        this.date = date;
    }

    // Method to return the date variable
    public Date getDate() {
        return date;
    }
    
    // Method to return string version of the year
    public String getYear() {
        // Taking the year value from the date variable and adding 1900 since this method subtracts 1900 from the current year
        return String.valueOf(date.getYear() + 1900);
    }
    
    // Method to return string version of the month
    public String getMonth() {
        // If statement to add a 0 to the beginning of a string if the value is less than 0
        if ((date.getMonth() + 1) < 10) {
            // Adding a 0 to the begging of the string and adding a 1 to the month, since it starts at 0 for January
            return "0" + String.valueOf(date.getMonth() + 1);
        } else {
            // Returning a string of the month
            return String.valueOf(date.getMonth() + 1);
        }
    }
    
    // Method to return string version of the date
    public String getDay() {
        // If statement to add a 0 to the beginning of a string if the value is less than 0
        if (date.getDate() < 10) {
            // Adding a 0 to the begging of the string
            return "0" + String.valueOf(date.getDate());
        } else {
            // Returning a string of the date
            return String.valueOf(date.getDate());
        }
    }
    
    // Method to return string version of the hours
    public String getHours() {
        // If statement to add a 0 to the beginning of a string if the value is less than 0
        if (date.getHours() < 10) {
            // Adding a 0 to the begging of the string
            return "0" + String.valueOf(date.getHours());
        } else {
            // Returning a string of the hours
            return String.valueOf(date.getHours());
        }
    }
    
    // Method to return string version of the minutes
    public String getMinutes() {
        // If statement to add a 0 to the beginning of a string if the value is less than 0
        if (date.getMinutes() < 10) {
             // Adding a 0 to the begging of the string
            return "0" + String.valueOf(date.getMinutes());
        } else {
            // Returning a string of the minutes
            return String.valueOf(date.getMinutes());
        }
    }
    
    // Method to return string version of the minutes + 1
    public String getMinutesPlus1() {
        // If statement to add a 0 to the beginning of a string if the value is less than 0
        if (date.getMinutes() < 10) {
            // Adding a 0 to the begging of the string
            return "0" + String.valueOf(date.getMinutes() + 1);
        } else {
            // Returning a string of the minutes + 1
            return String.valueOf(date.getMinutes() + 1);
        }
    }
    
    // Method to take the individual values of the time and putting it into a string for the ICal file
    public String getCalendarString(int i) {
        String calString = "BEGIN:VEVENT\r\n"
                + "UID:" + i + "00000\r\n"
                // Gets the strings created with the previous methods
                + "DTSTAMP:" + getYear() + getMonth() + getDay() + "T" + getHours() + getMinutes() + "00Z\r\n"
                + "DTSTART:" + getYear() + getMonth() + getDay() + "T" + getHours() + getMinutes() + "00Z\r\n"
                + "DTEND:" + getYear() + getMonth() + getDay() + "T" + getHours() + getMinutesPlus1() + "00Z\r\n"
                + "SUMMARY:Alarm" + (i + 1) + "\r\n"
                + "END:VEVENT\r\n";
        
        return calString;
    }
    
    // Method to create a countdown until the next alarm
    public long countdown(Date officialDate) {
        long countdownTime = 0;
        
        // If statement to make sure the countdown never goes negative
        if (date.getTime() - officialDate.getTime() < 0) {
            countdownTime = 0;
        } else {
            // Subtracting the amarl time from the current time
            countdownTime = date.getTime() - officialDate.getTime();
        }
        
        // Dividing the countdown by 1000 to get rid of milliseconds
        return countdownTime / 1000;
    }
    
    // Method to take the spinner and extract the time from it. Using this time to create a custom priority
    // for the PriorityQueue
    public long getPriority(JSpinner spinner) {
        priority = 0;
        
        // If statement to get the month from the spinner and add a 0 to the string if it is below 10
        if (((Date) spinner.getValue()).getMonth() + 1 < 10) {
            // Adding a 0 since it is below 10 and adding a 1 to the month since it starts at 0
            month = "0" + String.valueOf(((Date) spinner.getValue()).getMonth() + 1);
        } else {
            // Returning a string of the month
            month = String.valueOf(((Date) spinner.getValue()).getMonth() + 1);
        }
        
        // If statement to get the date from the spinner and add a 0 to the string if it is below 10
        if (((Date) spinner.getValue()).getDate() < 10) {
            // Adding a 0 since it is below 10
            dateOfMonth = "0" + String.valueOf(((Date) spinner.getValue()).getDate());
        } else {
            // Returning a string of the date
            dateOfMonth = String.valueOf(((Date) spinner.getValue()).getDate());
        }
        
        // If statement to get the hours from the spinner and add a 0 to the string if it is below 10
        if (((Date) spinner.getValue()).getHours() < 10) {
            // Adding a 0 since it is below 10
            hours = "0" + String.valueOf(((Date) spinner.getValue()).getHours());
        } else {
            // Returning a string of the hours
            hours = String.valueOf(((Date) spinner.getValue()).getHours());
        }
        
        // If statement to get the hours from the spinner and add a 0 to the string if it is below 10
        if (((Date) spinner.getValue()).getMinutes() < 10) {
            // Adding a 0 since it is below 10
            minutes = "0" + String.valueOf(((Date) spinner.getValue()).getMinutes());
        } else {
            // Returning a string of the date
            minutes = String.valueOf(((Date) spinner.getValue()).getMinutes());
        }
        
        // Taking all the strings and converting it to long
        // If I didnt do this, the 0 and the beginning of some of the variables 
        // would dissapear
        priority = Long.parseLong(String.valueOf(((Date) spinner.getValue()).getYear()) 
                + month
                + dateOfMonth
                + hours
                + minutes
        );
        
        return priority;
    }
    
    // Same method as the one above, but it takes in a date instead of a spinner
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
