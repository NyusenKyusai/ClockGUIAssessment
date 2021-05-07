package clock;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    int year = 0;
    Date time;
    Date initialTime;
    
    public Model() {
        update();
    }
    
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
        
        Calendar alarmMin = Calendar.getInstance();
        time = date.getTime();
        alarmMin.setTime(time);
        
        alarmMin.add(Calendar.MINUTE, -1);
        initialTime = alarmMin.getTime();
        
    }
}