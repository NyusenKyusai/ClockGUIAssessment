package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {
    
    ClockPanel panel;
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
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
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        fileMenu.add(exitItem);
        
        menuBar.add(fileMenu);
        
        JMenu alarmMenu = new JMenu("Alarm");
        alarmMenu.setMnemonic(KeyEvent.VK_A);
        
        JMenuItem addItem = new JMenuItem("Add Alarm");
        addItem.setMnemonic(KeyEvent.VK_D);
        
        JMenuItem viewItem = new JMenuItem("View Alarms");
        viewItem.setMnemonic(KeyEvent.VK_V);
        
        alarmMenu.add(addItem);
        alarmMenu.add(viewItem);
        
        menuBar.add(alarmMenu);
        
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
                
            }
        }
        
        // Handler implemented as named inner class
        class ViewItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            }
        }
        
        exitItem.addActionListener(new ExitItemListener());
        addItem.addActionListener(new AddItemListener());
        viewItem.addActionListener(new ViewItemListener());
        
        Container pane = frame.getContentPane();
        
        JButton button = new JButton("Button 1 (PAGE_START)");
        pane.add(button, BorderLayout.PAGE_START);
         
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        button = new JButton("Button 3 (LINE_START)");
        pane.add(button, BorderLayout.LINE_START);
         
        button = new JButton("Long-Named Button 4 (PAGE_END)");
        pane.add(button, BorderLayout.PAGE_END);
         
        button = new JButton("5 (LINE_END)");
        pane.add(button, BorderLayout.LINE_END);
        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
}
