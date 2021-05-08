package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;
import queuemanager.SortedLinkedPriorityQueue;

public class View implements Observer {
    
    ClockPanel panel;
    JPanel viewPanel;
    JMenuItem addItem;
    JMenuItem viewItem;
    JMenu alarmMenu;
    JMenuItem exitItem;
    JMenu fileMenu;
    JMenuBar menuBar;
    JSpinner spinner;
    JPanel myPanel;
    Date time;
    SortedLinkedPriorityQueue q;
    JPanel card1;
    JPanel card2;
    JPanel cards;
    ButtonGroup group;
    JPanel radioPanel;
    JRadioButton[] alarmButtons;
    Object[] alarmArray;
    JPanel viewButtons;
    int counter;
    Model modelGlobal;
    Alarm alarmSound;
    JFrame frame;
    String icsString;
    
    private String version =    "VERSION:2.0\r\n";
    private String prodid =     "PRODID://JonahJuliaoToral/SoftwareConstruction//\r\n";
    private String calBegin =   "BEGIN:VCALENDAR\r\n";
    private String calEnd =     "END:VCALENDAR\r\n";
    
    public View(Model model) {
        q = new SortedLinkedPriorityQueue<>();
        
        modelGlobal = model;
        
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int answer = showWarningMessage();
                
                switch (answer) {
                    case JOptionPane.YES_OPTION:
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
                        
                        break;
                    case JOptionPane.NO_OPTION:
                        System.out.println("Don't Save and Quit");
                        break;
                }
            }
        });
        
        panel = new ClockPanel(model);
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
        
        card1 = new JPanel(new BorderLayout());
         
        panel.setPreferredSize(new Dimension(200, 200));
        card1.add(panel, BorderLayout.CENTER);
        
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
        
        try {
            alarmSound = (Alarm) q.head();
            
            //System.out.println(modelGlobal.time.getTime());
            //System.out.println(alarmSound.getDate().getTime());
            
            if (modelGlobal.time.getTime() >= alarmSound.getDate().getTime()) {
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
    
    private int showWarningMessage() {
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
}
