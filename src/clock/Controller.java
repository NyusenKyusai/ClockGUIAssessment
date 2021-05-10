package clock;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * Controller class that handles the interaction between the view and model class
 * by setting the timer of when the model updates and since the view is an observer
 * of the model, it also updates the view
 * 09/05/2021
 * @author Jonah Juliao Toral
 */
public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    /**
     * Class takes in the model and view classes. It creates an action listener
     * that calls the model update method. This method refreshes the hours, minutes
     * and second variables to update the clock face. This listener is set to a timer
     * that shoots off every 100 milliseconds so the model updates frequently
     *
     * @param m variable that holds the model and is used to call the update method
     * @param v variable that holds the view
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
}