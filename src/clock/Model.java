package clock;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
//import java.util.GregorianCalendar;

/**
 * Model class that handles the updating of the time values to change the clock
 * face. Uses the Calendar and Date classes to do this
 * 09/05/2021
 * @author Jonah Juliao Toral
 */
public class Model extends Observable {
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    int year = 0;
    
    // Date variables that can be called from other methods
    Date time;
    Date initialTime;
    
    /**
     * Constructor method for the model class that calls the update method to
     * get the initial time when the class is called initially
     * 
     */
    public Model() {
        update();
    }
    
    /**
     * Method that calls a Calendar class to get the current time. It compares
     * the current second to the old second and only notifies the view when a change
     * has been detected, otherwise it will send too many pings to the view. I also
     * added some more code to be able to subtract a minute from the time to handle
     * the minimum time of the spinner so that the user can not go into the past
     * with their alarms
     * 
     */
    public void update() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
        
        year = date.get(Calendar.YEAR);
        
        // Creating a calendar variable to set the Date time to so that it can be used in the view class
        Calendar alarmMin = Calendar.getInstance();
        // Taking the new Date variable that was created and setting it's time to the current time
        time = date.getTime();
        // Setting the current time to the alarmMin calendar variable created
        alarmMin.setTime(time);
        // Subtracting a minute from the current time
        alarmMin.add(Calendar.MINUTE, -1);
        // Making a new date variable and setting the new time to it
        initialTime = alarmMin.getTime();
        
    }
}