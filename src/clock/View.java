package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;
import queuemanager.SortedLinkedPriorityQueue;

public class View implements Observer {
    // Taking the model that was passed into view and making it a global variable
    Model modelGlobal;
    
    ClockPanel panel;
    //MenuBar, Menus, and MenuItems to be used
    JMenuBar menuBar;
    JMenu alarmMenu;
    JMenu fileMenu;
    JMenuItem addItem;
    JMenuItem viewItem;
    JMenuItem exitItem;
    
    //All the panels I need to create so I can use
    JPanel viewPanel;
    JPanel myPanel;
    JPanel card1;
    JPanel card2;
    JPanel cards;
    JPanel radioPanel;
    JPanel viewButtons;
    JPanel countdownPanel;
    
    //Label for the countdown
    JLabel countdown;
    
    // JFrame global variable to I can call it from different methods
    JFrame frame;
    
    // JSpinner for adding alarms
    JSpinner spinner;
    
    // Button group and radio buttons to exit the applications, add or view alarms
    ButtonGroup group;
    JRadioButton[] alarmButtons;
    
    // Date variables
    Date alarmEntry;
    Date time;
    
    // Alarm classes
    Alarm alarmPopUp;
    Alarm alarmEntryAlarm;
    Alarm alarmCountdown;
    
    // Priority queue variable
    SortedLinkedPriorityQueue q;
    
    // alarmArray variable to hold each of the alarms in the priority queue
    Object[] alarmArray;
    
    // String to create the ics file
    String icsString;
    //Arraylist that holds each alarm as it's own section of the array list
    ArrayList<String> events;
    
    // Parts of the ics file that never change
    private String version =    "VERSION:2.0\r\n";
    private String prodid =     "PRODID://JonahJuliaoToral/SoftwareConstruction//\r\n";
    private String calBegin =   "BEGIN:VCALENDAR\r\n";
    private String calEnd =     "END:VCALENDAR\r\n";
    
    public View(Model model) {
        // Initialising the sorted priority queue variable
        q = new SortedLinkedPriorityQueue<>();
        
        // Calling the method that creates a dialogue box asking the user
        // if they want to load their old alarm file that was saved
        int answer = showWarningMessageLoad();
        // Switch case that deals with the users selection
        switch (answer) {
            case JOptionPane.YES_OPTION:
                // Loads the file if the user chose to load the file
                loadFile();
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("Don't load");
                break;
        }
        
        // Takes the model variable passed when view was initialised and makes 
        // it global
        modelGlobal = model;
        
        // Initialises the JFrame and makes it global
        frame = new JFrame();;
         
        // Adding a window listener to when the window closes
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // This creates a dialogue box and then saved the answer
                int answer = showWarningMessageSave();
                
                // Switch case to handle the answer from the dialogue box
                switch (answer) {
                    case JOptionPane.YES_OPTION:
                        // Calling the save file method in the case the user
                        // chose to save the alarms
                        saveFile();
                        break;
                    case JOptionPane.NO_OPTION:
                        System.out.println("Don't Save and Quit");
                        break;
                }
            }
        });
        
        // Initialising the ClockPanel variable and passing the model into it
        panel = new ClockPanel(model);
        // Creating a new panel for the countdown to the next alarm
        countdownPanel = new JPanel();
        //frame.setContentPane(panel);
        // Setting the title and setting what the app does on close
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        // Initialising the menu bar and adding it to the main frame
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        // Initialising the first menu in the menu bar and adding it's mnemonic
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // Initialising the item of the file menu and adding it's mnemonic
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        // Adding the exit item to the file menu
        fileMenu.add(exitItem);
        //Adding the file menu to the menu bar
        menuBar.add(fileMenu);
        
        // Initialising the second menu Alarm and adding it's mnemonic
        alarmMenu = new JMenu("Alarm");
        alarmMenu.setMnemonic(KeyEvent.VK_A);
        
        // Initilising the add alarm item to the alarm menu and adding it's mnemonic
        addItem = new JMenuItem("Add Alarm");
        addItem.setMnemonic(KeyEvent.VK_D);
        // Initilising the view alarm item to the alarm menu and adding it's mnemonic
        viewItem = new JMenuItem("View Alarms");
        viewItem.setMnemonic(KeyEvent.VK_V);
        // Adding the two items to the alarm menu
        alarmMenu.add(addItem);
        alarmMenu.add(viewItem);
        //Adding the alarm menu to the menu bar
        menuBar.add(alarmMenu);
        
        // Initialising the panel that holds the spinner
        myPanel = new JPanel();
        // Creating a spinner date model that starts at the current time, has a minimum time of 1 minute less than the current time, and increments at 1
        SpinnerDateModel dateModel = new SpinnerDateModel(modelGlobal.time, modelGlobal.initialTime, null, 1);
        
        // initialising the spinner using the date model we created
        spinner = new JSpinner(dateModel);
        // Getting the prefered size of the spinner
        Dimension prefSize = spinner.getPreferredSize();
        // Creating a new dimension that has the same spinner height but a different width
        prefSize = new Dimension(90, prefSize.height);
        // Getting the editor of the spinner into a JComponent
        JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
        // Using the component to set the new size of the spinner
        field.setPreferredSize(prefSize);
        // Adding the spinner to the panel we created for it
        myPanel.add(spinner, BorderLayout.CENTER);
        
        // Initialising the button group
        group = new ButtonGroup();
        // Initialising the panel for the radio buttons and using a grid layout
        // to format it for editing the alarms
        radioPanel = new JPanel(new GridLayout(0, 1));
        
        // Method to close the application when the exit button in the menu is clicked
        class ExitItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        }
        
        // Method to handle when the add alarm button is clicked
        class AddItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Sets the spinner value to the current time
                spinner.setValue(modelGlobal.time);
                // Adds the spinner to the panel that was created to hold it
                myPanel.add(spinner);
                // Object array to hold the values of each of the buttons
                Object[] options = {"Save", "Cancel"};
                // Option integer to hold the answer to a dialog box 
                // Sets the button that needs to be clicked, the panel where it appears
                // the title, the type of dialogue, options and the option that is the 
                // cancel
                int option = JOptionPane.showOptionDialog(addItem, myPanel, "Select Date and Time for Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                
                // If statement to handle the option dialog answer
                if (option == JOptionPane.OK_OPTION) {
                    // Creates an alarm value by passing the spinner value that is
                    // casted as a Date object
                    Alarm alarm = new Alarm((Date) spinner.getValue());
                    // Calling the priority method from the alarm class using a spinner
                    // to get a custom priority for the priority queue
                    long priority = alarm.getPriority(spinner);
                    // Try method to add the alarm using the priority
                    try {
                        // Calling the add method to add the alarm class instance
                        q.add(alarm, priority);
                    } catch (QueueOverflowException e) {
                        System.out.println("Add operation failed: " + e);
                    }
                    
                    //System.out.println(q.toString());
                }
            }
        }
        
        // Method to handle when the view button is selected
        class ViewItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Creating a card layout variable that handles showing the cards
                // for the frame
                CardLayout cl = (CardLayout)(cards.getLayout());
                // Showing the ViewAlarms card instead of creating a new frame
                cl.show(cards, "ViewAlarms");
                // Initialising the alarmArray variable and populating it
                // with the new method that was created
                alarmArray = (Object[]) q.returnArray();
                
                // Initialising the alarmButtons array of JRadioButton with the
                // the length of the object array previously created
                alarmButtons = new JRadioButton[alarmArray.length];
                
                // For loop to iterate through the object array
                for (int i = 0; i < alarmArray.length; i++) {
                    // Initialising the the alarm buttons using the time turned
                    // into a string
                    alarmButtons[i] = new JRadioButton(alarmArray[i].toString());
                    // Setting the action command equal to the priority that was selected
                    // This is important because later we are gonna use the priority
                    // to find the alarm that needs to be edited
                    alarmButtons[i].setActionCommand(String.valueOf(((Alarm) alarmArray[i]).getPriority(spinner)));
                    
                    //System.out.println(alarmButtons[i]);
                    
                    // Adding the button to the panel and adding it to the button
                    // group
                    radioPanel.add(alarmButtons[i]);
                    group.add(alarmButtons[i]);
                }
                // Adding the radioPanel panel to the view panel that was created for the view button
                // card
                viewPanel.add(radioPanel, BorderLayout.CENTER);
            }
        }
        
        // Method to handle cancel button to edit the alarm
        class CancelButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Creating a card layout variable that handles showing the cards
                // for the frame
                CardLayout cl = (CardLayout)(cards.getLayout());
                // Showing the MainPanel card instead of creating a new frame
                cl.show(cards, "MainPanel");
                // Removing everything from the radio panel
                // Otherwise the panel doubles in size every time we switch to
                // the view card
                radioPanel.removeAll();
                
            }
        }
        
        
        // Method to handle edit button to edit the alarm
        class EditButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Creating a card layout variable that handles showing the cards
                // for the frame
                CardLayout cl = (CardLayout)(cards.getLayout());
                // Initialising the priority button
                long priority = 0;
                // For loop to find the selected button and save it's priority
                // Since they were all in the same button group, only one can be
                // selected at a time
                for (int i = 0; i < alarmArray.length; i++) {
                    // Seeing if the button is selected
                    if (alarmButtons[i].isSelected()) {
                        // Saving the priority
                        priority = Long.valueOf(alarmButtons[i].getActionCommand());
                    }
                }
                
                // Initialising the alarm info variable
                Alarm info = null;
                // Setting the values of the dialog buttons
                Object[] options = {"Save", "Cancel"};
                // Showing the user the same dialog as before, this time to edit the 
                // alarm
                int option = JOptionPane.showOptionDialog(addItem, myPanel, "Select Date and Time for Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                // Using the answer from the dialog to run code
                if (option == JOptionPane.OK_OPTION) {
                    // Trying to delete the alarm using it's priority 
                    try {
                        info = (Alarm) q.deleteUsingPriority(priority);
                    } catch (QueueUnderflowException e) {
                        System.out.println("Add operation failed: " + e);
                    }
                    
                    // Taking the value from the spinner and adding it to an alarm
                    // class
                    Alarm alarm = new Alarm((Date) spinner.getValue());
                    
                    // Getting the new priority by calling the method in the alarm class
                    long newPriority = alarm.getPriority(spinner);
                    // Trying to add the new alarm to the priority queue with 
                    // the new priority
                    try {
                        q.add(alarm, newPriority);
                    } catch (QueueOverflowException e) {
                        System.out.println("Add operation failed: " + e);
                    }
                    
                    //System.out.println(q.toString());
                    // Showing the Main Panel card after the alarm has been edited
                    cl.show(cards, "MainPanel");
                }
                
                // Removing everything from the radio panel
                // Otherwise the panel doubles in size every time we switch to
                // the view card
                radioPanel.removeAll();
            }
        }
        
        // Adding action listeners to the menu items and setting them to the 
        // inner classes that were created
        exitItem.addActionListener(new ExitItemListener());
        addItem.addActionListener(new AddItemListener());
        viewItem.addActionListener(new ViewItemListener());
        
        // Trying to find the first alarm in the queue and setting it to an 
        // initialised alarm variable
        try {
            alarmCountdown = (Alarm) q.head();
        } catch (QueueUnderflowException ex) {
            //Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Initialising the countdown label with string the countdown method
        // from the alarm class
        countdown = new JLabel(String.valueOf(alarmCountdown.countdown(model.time)));
        
        // Initialising the card with a border layout
        card1 = new JPanel(new BorderLayout());
        // Adding the countdown label to the countdownPanel
        countdownPanel.add(countdown);
         
        // Setting the prefered dimension of the clock
        panel.setPreferredSize(new Dimension(200, 200));
        // Adding the panel to the card as well as the countdown
        card1.add(panel, BorderLayout.CENTER);
        card1.add(countdownPanel, BorderLayout.PAGE_END);
        
        // Initialising the edit and cancel button to the view alarms card
        // with the values
        JButton editButton = new JButton("Edit");
        JButton cancelButton = new JButton("Cancel");
        
        // Initialising the panel with a grid layout that will put them side by
        // side and adding the buttons
        viewButtons = new JPanel(new GridLayout(1,0));
        viewButtons.add(editButton);
        viewButtons.add(cancelButton);
        
        // Adding action listeners to the buttons with the inner classes
        editButton.addActionListener(new EditButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        
        // Initialising the view alarms panel
        viewPanel = new JPanel();
        // Initialising the second card with a border layout
        card2 = new JPanel(new BorderLayout());
        // Adding the view panel and the view buttons
        card2.add(viewPanel, BorderLayout.CENTER);
        card2.add(viewButtons, BorderLayout.PAGE_END);
        
        // Initialising the cards JPanel with a card layout
        cards = new JPanel(new CardLayout());
        // Adding the cards to the card layout panel
        cards.add(card1, "MainPanel");
        cards.add(card2, "ViewAlarms");
        // Adding the card layout panel to the frame
        frame.add(cards);
        
        // End of borderlayout code
        // Packing the frame and making it visible
        frame.pack();
        frame.setVisible(true);
    }
    
    // Method that handles the parts of the view method that  need to be updated
    @Override
    public void update(Observable o, Object arg) {
        // Repainting the clock
        panel.repaint();
        
        // Trying to get the head of the priority queue
        try {
            alarmCountdown = (Alarm) q.head();
        } catch (QueueUnderflowException ex) {
            //Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Setting the text of the countdown equal to the string of what the countdown
        // method passes back
        // If there are no alarms, it just sends 0
        countdown.setText(String.valueOf(alarmCountdown.countdown(modelGlobal.time)));
        // Repaints the coundown panel to show the change
        countdownPanel.repaint();
        
        // Try catch method to show when the alarm is meant to go off
        try {
            // Initialising the alarm variable with the head of the queue
            alarmPopUp = (Alarm) q.head();
            
            //System.out.println(modelGlobal.time.getTime());
            //System.out.println(alarmSound.getDate().getTime());
            
            // If statement to see if the current time is equal to or ahead of the
            // alarm time of the head
            // Can't do equal, because the milliseconds for the two times are
            // always different
            if (modelGlobal.time.getTime() >= alarmPopUp.getDate().getTime()) {
                // Creates an option pane saying your alarm has gone off
                JOptionPane pane = new JOptionPane("Your Alarm has gone off", JOptionPane.INFORMATION_MESSAGE);
                // adding the pane to the dialog
                final JDialog dialog = pane.createDialog(null, "Alarm");
                // Making the dialog visible
                dialog.setModal(false);
                dialog.setVisible(true);
                // Creating a timer for six seconds with an action listener
                // attached to it
                new Timer(6000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Turning off the dialog in case the user does not stop the alarm themselves
                        dialog.setVisible(false);
                    }
                // Starting the timer
                }).start();
                // Removing the head of the queue when the alarm has gone off
                q.remove();
            }
        } catch (QueueUnderflowException e) {
            //System.out.println("Add operation failed: " + e);
        }  
    }
    
    // Method that creates a dialog box and returns the result
    private int showWarningMessageSave() {
        String[] buttonLabels = new String[] {"Yes", "No"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;
        
        // Creating the option dialog and returning the result
        return JOptionPane.showOptionDialog(frame,
                "There's still something unsaved.\n" +
                "Do you want to save before exiting?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);    
    }
    
    // Method that creates a dialog box and returns the result
    private int showWarningMessageLoad() {
        String[] buttonLabels = new String[] {"Yes", "No"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;
        
        // Creating the option dialog and returning the result
        return JOptionPane.showOptionDialog(frame,
                "Do you want to load iCal file?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);    
    }
    
    // Method to read the ICS File from the project folder
    private ArrayList<String> readICSFile() throws FileNotFoundException, IOException {
        // Initialising an array list of strings / Array list so that the size 
        // can be variable
        ArrayList<String> events = new ArrayList<String>();
        
        // Trying to read the file in the case that it doesn't exist
        // Creating a BufferedReader variable and initialising it with a file
        // reader with the name of the file in it
        try (BufferedReader br = new BufferedReader(new FileReader("myAlarms.ics"))) {
            // Creating a string variable that holds the line that's been read
            String line;
            // While loop that reads each line of the file in the case where it is not null
            while ((line = br.readLine()) != null) {
                //System.out.println(line.substring(0, 8));
                
                // Finds the part of the document that has DTSTAMP in it
                // because that will have the date
                if (line.substring(0, 8).equals("DTSTAMP:")) {
                    // Saving the date and time to the events array list
                    events.add(line.substring(8, 23));
                }

                //events.add(line.substring(9, 24));
            }
        }
        // Returning the array list
        return events;
    }
    
    // Method to load the file
    private void loadFile() {
        // Trying to read the file
        try {
            // Tries to read the file by calling the other method
            events = readICSFile();
            // For loop that iterates over the ArrayList that was passed from
            // the previous method
            for (int i = 0; i < events.size(); i++) {
                // Initialising the date to the variable to add time to it later
                alarmEntry = new Date();
                
                // Creating a year variable by parting the int from the array list
                // and subtracting 1900 from it, as date.getyear() subtracts 1900
                // from the current year
                int year = Integer.parseInt(events.get(i).substring(0, 4));
                year = year - 1900;
                // Creating a month variable by parting the int from the array list
                // and subtracting 1 from it, as date.getMonth() starts at 0 for
                // January
                int month = Integer.parseInt(events.get(i).substring(4, 6));
                month = month - 1;
                // Creating a day variable by parting the int from the array list
                int day = Integer.parseInt(events.get(i).substring(6, 8));
                // Creating a hour variable by parting the int from the array list
                int hour = Integer.parseInt(events.get(i).substring(9, 11));
                // Creating a minute variable by parting the int from the array list
                int minute = Integer.parseInt(events.get(i).substring(11, 13));
                
                //System.out.println(year);
                
                //System.out.println(Integer.parseInt(events.get(i).substring(0, 4)));
                //System.out.println(Integer.parseInt(events.get(i).substring(4, 6)));
                //System.out.println(Integer.parseInt(events.get(i).substring(6, 8)));
                //System.out.println(Integer.parseInt(events.get(i).substring(9, 11)));
                //System.out.println(Integer.parseInt(events.get(i).substring(11, 13)));
                
                // Taking the parsed ints and using them to set the values of the 
                // Date variable
                alarmEntry.setYear(year);
                alarmEntry.setMonth(month);
                alarmEntry.setDate(day);
                alarmEntry.setHours(hour);
                alarmEntry.setMinutes(minute);
                // Setting the seconds to 0
                alarmEntry.setSeconds(0);
                
                //System.out.println(alarmEntry.getYear());

                // Initialising a new alarm variable with the Date variable
                // alarmEntry that was created
                alarmEntryAlarm = new Alarm(alarmEntry);

                // Try and catch method to add the current alarm in the for loop to the priority queue
                try {
                    q.add(alarmEntryAlarm, alarmEntryAlarm.getPriority(alarmEntry));
                } catch (QueueOverflowException ex) {

                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method to save events to the file
    private void saveFile() {
        System.out.println("Save and Quit");
                        
        // Creating an array of objects by calling the method
        alarmArray = (Object[]) q.returnArray();

        // String builder to append the name and the file type to the string
        StringBuilder builder = new StringBuilder();
        builder.append("myAlarms");
        builder.append(".ics");

        // Initialising the ICS string
        icsString = "";

        // For loop to add the VEVENT part of the document per alarm in the 
        // alarm Array
        for (int i = 0; i < alarmArray.length; i++) {
            // Adding the event to the string using the method from the 
            // Alarm class
            icsString = icsString + ((Alarm) alarmArray[i]).getCalendarString(i);
        }

        //System.out.println(icsString);

        // Try and catch method to write to the file 
        try {
            // Creating a new file using the string builder as it's name
            File file = new File(builder.toString());

            // Creating the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Getting the absolute path of the file that was created previously
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            // Creating a buffered writer of the file writer 
            BufferedWriter bw = new BufferedWriter(fw);
            // Writing the strings to the file
            bw.write(calBegin);
            bw.write(version);
            bw.write(prodid);
            bw.write(icsString);
            bw.write(calEnd);
            // Closing the buffered writer to apply the changes
            bw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
