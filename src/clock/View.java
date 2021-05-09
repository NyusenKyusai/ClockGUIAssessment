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
import java.util.logging.Level;
import java.util.logging.Logger;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;
import queuemanager.SortedLinkedPriorityQueue;

public class View implements Observer {
    
    Model modelGlobal;
    
    ClockPanel panel;
    
    JMenuBar menuBar;
    JMenu alarmMenu;
    JMenu fileMenu;
    JMenuItem addItem;
    JMenuItem viewItem;
    JMenuItem exitItem;
    
    JPanel viewPanel;
    JPanel myPanel;
    JPanel card1;
    JPanel card2;
    JPanel cards;
    JPanel radioPanel;
    JPanel viewButtons;
    JPanel countdownPanel;
    
    JLabel countdown;
    
    JFrame frame;
    
    JSpinner spinner;
    
    ButtonGroup group;
    JRadioButton[] alarmButtons;
    
    Date alarmEntry;
    Date time;
    
    Alarm alarmPopUp;
    Alarm alarmEntryAlarm;
    Alarm alarmCountdown;
    
    SortedLinkedPriorityQueue q;
    
    Object[] alarmArray;
    
    int counter;
    
    String icsString;
    ArrayList<String> events;
    
    private String version =    "VERSION:2.0\r\n";
    private String prodid =     "PRODID://JonahJuliaoToral/SoftwareConstruction//\r\n";
    private String calBegin =   "BEGIN:VCALENDAR\r\n";
    private String calEnd =     "END:VCALENDAR\r\n";
    
    public View(Model model) {
        q = new SortedLinkedPriorityQueue<>();
        
        int answer = showWarningMessageLoad();
                
        switch (answer) {
            case JOptionPane.YES_OPTION:
                loadFile();
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("Don't load");
                break;
        }
        
        modelGlobal = model;
        
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int answer = showWarningMessageSave();
                
                switch (answer) {
                    case JOptionPane.YES_OPTION:
                        saveFile();
                        break;
                    case JOptionPane.NO_OPTION:
                        System.out.println("Don't Save and Quit");
                        break;
                }
            }
        });
        
        panel = new ClockPanel(model);
        countdownPanel = new JPanel();
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String[] labels = {"Day: ", "Month: ", "Year: ", "Another Date: "};
        int numPairs = labels.length;
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        fileMenu.add(exitItem);
        
        menuBar.add(fileMenu);
        
        alarmMenu = new JMenu("Alarm");
        alarmMenu.setMnemonic(KeyEvent.VK_A);
        
        addItem = new JMenuItem("Add Alarm");
        addItem.setMnemonic(KeyEvent.VK_D);
        
        viewItem = new JMenuItem("View Alarms");
        viewItem.setMnemonic(KeyEvent.VK_V);
        
        alarmMenu.add(addItem);
        alarmMenu.add(viewItem);
        
        menuBar.add(alarmMenu);
        
        myPanel = new JPanel();
        
        SpinnerDateModel dateModel = new SpinnerDateModel(modelGlobal.time, modelGlobal.initialTime, null, 1);
        
        
        spinner = new JSpinner(dateModel);
        Dimension prefSize = spinner.getPreferredSize();
        prefSize = new Dimension(90, prefSize.height);
        
        JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
        field.setPreferredSize(prefSize);
        
        myPanel.add(spinner, BorderLayout.CENTER);
        
        group = new ButtonGroup();
        radioPanel = new JPanel(new GridLayout(0, 1));
        
        // Handler implemented as named inner class
        class ExitItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        }
        
        // Handler implemented as named inner class
        class AddItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                spinner.setValue(modelGlobal.time);
                
                myPanel.add(spinner);
                
                Object[] options = {"Save", "Cancel"};
                
                int option = JOptionPane.showOptionDialog(addItem, myPanel, "Select Date and Time for Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                
                if (option == JOptionPane.OK_OPTION) {
                    
                    Alarm alarm = new Alarm((Date) spinner.getValue());
                    
                    long priority = alarm.getPriority(spinner);

                    try {
                        q.add(alarm, priority);
                    } catch (QueueOverflowException e) {
                        System.out.println("Add operation failed: " + e);
                    }
                    
                    //System.out.println(q.toString());
                }
            }
        }
        
        // Handler implemented as named inner class
        class ViewItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "ViewAlarms");
                
                alarmArray = (Object[]) q.returnArray();
                
                

                alarmButtons = new JRadioButton[alarmArray.length];

                for (int i = 0; i < alarmArray.length; i++) {
                    alarmButtons[i] = new JRadioButton(alarmArray[i].toString());
                    alarmButtons[i].setActionCommand(String.valueOf(((Alarm) alarmArray[i]).getPriority(spinner)));
                    
                    //System.out.println(alarmButtons[i]);

                    radioPanel.add(alarmButtons[i]);
                    group.add(alarmButtons[i]);
                    
                    counter = i;
                }
                
                viewPanel.add(radioPanel, BorderLayout.CENTER);
            }
        }
        
        // Handler implemented as named inner class
        class CancelButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "MainPanel");
                
                radioPanel.removeAll();
                
            }
        }
        
        
        // Handler implemented as named inner class
        class EditButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                
                long priority = 0;
                
                for (int i = 0; i < alarmArray.length; i++) {
                    if (alarmButtons[i].isSelected()) {
                        priority = Long.valueOf(alarmButtons[i].getActionCommand());
                    }
                }
                
                Alarm info = null;
                
                
                
                Object[] options = {"Save", "Cancel"};
                
                int option = JOptionPane.showOptionDialog(addItem, myPanel, "Select Date and Time for Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        info = (Alarm) q.deleteUsingPriority(priority);
                    } catch (QueueUnderflowException e) {
                        System.out.println("Add operation failed: " + e);
                    }
                    
                    Alarm alarm = new Alarm((Date) spinner.getValue());
                    
                    long newPriority = alarm.getPriority(spinner);

                    try {
                        q.add(alarm, newPriority);
                    } catch (QueueOverflowException e) {
                        System.out.println("Add operation failed: " + e);
                    }
                    
                    System.out.println(q.toString());
                    
                    cl.show(cards, "MainPanel");
                }
                
                radioPanel.removeAll();
            }
        }
        
        exitItem.addActionListener(new ExitItemListener());
        addItem.addActionListener(new AddItemListener());
        viewItem.addActionListener(new ViewItemListener());
        
        
        
        if (!q.isEmpty()) {
            try {
                alarmCountdown = (Alarm) q.head();
            } catch (QueueUnderflowException ex) {
                //Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        countdown = new JLabel(String.valueOf(alarmCountdown.countdown(model.time)));
        
        card1 = new JPanel(new BorderLayout());
        countdownPanel.add(countdown);
         
        panel.setPreferredSize(new Dimension(200, 200));
        card1.add(panel, BorderLayout.CENTER);
        card1.add(countdownPanel, BorderLayout.PAGE_END);
        
        JButton editButton = new JButton("Edit");
        JButton cancelButton = new JButton("Cancel");
        
        viewButtons = new JPanel(new GridLayout(1,0));
        viewButtons.add(editButton);
        viewButtons.add(cancelButton);
        
        editButton.addActionListener(new EditButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        
        viewPanel = new JPanel();
        
        card2 = new JPanel(new BorderLayout());
        card2.add(viewPanel, BorderLayout.CENTER);
        card2.add(viewButtons, BorderLayout.PAGE_END);
        
        cards = new JPanel(new CardLayout());
        cards.add(card1, "MainPanel");
        cards.add(card2, "ViewAlarms");
        
        frame.add(cards);
        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
        
        if (!q.isEmpty()) {
            try {
                alarmCountdown = (Alarm) q.head();
            } catch (QueueUnderflowException ex) {
                //Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        countdown.setText(String.valueOf(alarmCountdown.countdown(modelGlobal.time)));
        countdownPanel.repaint();
        
        try {
            alarmPopUp = (Alarm) q.head();
            
            //System.out.println(modelGlobal.time.getTime());
            //System.out.println(alarmSound.getDate().getTime());
            
            if (modelGlobal.time.getTime() >= alarmPopUp.getDate().getTime()) {
                JOptionPane pane = new JOptionPane("Your Alarm has gone off", JOptionPane.INFORMATION_MESSAGE);
                final JDialog dialog = pane.createDialog(null, "Alarm");
                
                dialog.setModal(false);
                dialog.setVisible(true);
                
                new Timer(6000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                    }
                }).start();
                
                q.remove();
            }
        } catch (QueueUnderflowException e) {
            //System.out.println("Add operation failed: " + e);
        }  
    }
    
    private int showWarningMessageSave() {
        String[] buttonLabels = new String[] {"Yes", "No"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;
         
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
    
    private int showWarningMessageLoad() {
        String[] buttonLabels = new String[] {"Yes", "No"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;
         
        return JOptionPane.showOptionDialog(frame,
                "Do you want to load iCal file?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);    
    }
    
    private ArrayList<String> readICSFile() throws FileNotFoundException, IOException {
        ArrayList<String> events = new ArrayList<String>();
        
        int i = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("myAlarms.ics"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line.substring(0, 8));
                
                if (line.substring(0, 8).equals("DTSTAMP:")) {
                    events.add(line.substring(8, 23));
                }

                //events.add(line.substring(9, 24));
            }
        }
        
        return events;
    }
    
    private void loadFile() {
        try {
            events = readICSFile();
            for (int i = 0; i < events.size(); i++) {
                alarmEntry = new Date();
                
                int year = Integer.parseInt(events.get(i).substring(0, 4));
                year = year - 1900;
                int month = Integer.parseInt(events.get(i).substring(4, 6));
                month = month - 1;
                int day = Integer.parseInt(events.get(i).substring(6, 8));
                int hour = Integer.parseInt(events.get(i).substring(9, 11));
                int minute = Integer.parseInt(events.get(i).substring(11, 13));
                
                //System.out.println(year);
                
                //System.out.println(Integer.parseInt(events.get(i).substring(0, 4)));
                //System.out.println(Integer.parseInt(events.get(i).substring(4, 6)));
                //System.out.println(Integer.parseInt(events.get(i).substring(6, 8)));
                //System.out.println(Integer.parseInt(events.get(i).substring(9, 11)));
                //System.out.println(Integer.parseInt(events.get(i).substring(11, 13)));
                
                alarmEntry.setYear(year);
                alarmEntry.setMonth(month);
                alarmEntry.setDate(day);
                alarmEntry.setHours(hour);
                alarmEntry.setMinutes(minute);
                alarmEntry.setSeconds(0);
                
                //System.out.println(alarmEntry.getYear());

                alarmEntryAlarm = new Alarm(alarmEntry);

                try {
                    q.add(alarmEntryAlarm, alarmEntryAlarm.getPriority(alarmEntry));
                } catch (QueueOverflowException ex) {

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveFile() {
        System.out.println("Save and Quit");
                        
        alarmArray = (Object[]) q.returnArray();

        StringBuilder builder = new StringBuilder();
        builder.append("myAlarms");
        builder.append(".ics");

        icsString = "";

        for (int i = 0; i < alarmArray.length; i++) {
            icsString = icsString + ((Alarm) alarmArray[i]).getCalendarString(i);
        }

        System.out.println(icsString);

        try {
            File file = new File(builder.toString());

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(calBegin);
            bw.write(version);
            bw.write(prodid);
            bw.write(icsString);
            bw.write(calEnd);
            bw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
