package clock;

/**
 * Clock class that starts the MVC classes and connects them to each other
 * 09/05/2021
 * @author Jonah Juliao Toral
 */
public class Clock {
    
    /**
     * Calls the Model, View and Controller classes and passes them to each
     * other so that they can communicate with each other
     * Also makes view the observer of the model class
     *
     * @param args variable that starts with every main java class
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
    }
}
