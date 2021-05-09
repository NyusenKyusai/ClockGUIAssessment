/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.Date;
import javax.swing.JSpinner;

/**
 * Alarm class that holds the data that is inserted into the PriorityQueue
 * 09/05/2021
 * @author Jonah Juliao Toral
 */
public class Alarm {
    /**
     * Creating global variables to be used
     */
    protected Date date;
    // Long variable to hold the priority that is created
    long priority;
    // String variables to hold strings othe sections of the time
    String month;
    String dateOfMonth;
    String hours;
    String minutes;
    String seconds;

    /**
     * Constructor for the data class
     * 
     * @param date  Variable that is thrown is set equal to the global date
     */
    public Alarm(Date date) {
        // Takes the date that was put into the constructor and set to the global date variable
        this.date = date;
    }

    /**
     * Method to return the date variable
     * 
     * @return date Return the global date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method to return string version of the year
     * 
     * @return String   Returns the year + 1900 converted as a string
     */
    public String getYear() {
        // Taking the year value from the date variable and adding 1900 since this method subtracts 1900 from the current year
        return String.valueOf(date.getYear() + 1900);
    }

    /**
     * Method to return string version of the month
     * 
     * @return String   Returns the month + 1 by converting it to a string and adding
     *                  a 0 at the beginning if it is less than 0
     */
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
    
    /**
     * Method to return string version of the date
     * 
     * @return String   Returns the date by converting it to a string and adding
     *                  a 0 at the beginning if it is less than 0
     */
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
    
    /**
     * Method to return string version of the hours
     * 
     * @return String   Returns the hours by converting it to a string and adding
     *                  a 0 at the beginning if it is less than 0
     */
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
    
    /**
     * Method to return string version of the minutes
     * 
     * @return String   Returns the minutes by converting it to a string and adding
     *                  a 0 at the beginning if it is less than 0
     */
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
    
    /**
     * Method to return string version of the minutes + 1
     * 
     * @return String   Returns the minutes plus 1
     */
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
    
    /**
     * Takes individual values of the time and puts them into a string for the ICal file
     * using string methods to add 0's when necessary.
     * Has internal formatting to comply with ical
     *
     * @param i Int that determines the UID as well as the alarm number
     * @return calString holds the event data for each alarm to be added to an
     *                   ical file
     */
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

    /**
     * Creates a countdown until the next alarm in seconds by subtracting the alarm time
     * by the current time. If it less than zero, it is zero
     * 
     * @param officialDate the date that the alarm time is taken out of
     * @return countdownTime    long that holds the seconds until the alarm goes
     *                          off
     */
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
    
    // 

    /**
     * Method to take the spinner and extract the time from it. Using this time 
     * to create a custom priority for the PriorityQueue
     * 
     * @param spinner   spinner that the value is taken out of and formatted to 
     *                  make a priority out of
     * @return priority long that holds the formatted priority that serves to order
     *                  the alarms in the priority queue
     */
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
    
    /**
     * Same method as the one above, but it takes in a date instead of a spinner
     *
     * @param date  date that the value is taken out of and formatted to 
     *              make a priority out of
     * @return priority a long variable that is parsed from a string specifically
     *                  to keep zeros in front of numbers less that 10 to keep the
     *                  formating of dates
     */
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
