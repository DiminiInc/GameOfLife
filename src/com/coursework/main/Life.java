package com.coursework.main;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Life extends JFrame{ //application frame class
    private int	cellSize = 8; //cell size set
    private int timerDelay = 10;//set timer delay
    private int mouseChannelRed=255, mouseChannelGreen=255,mouseChannelBlue=255; //default mouse brush color
    private boolean simulationRunning=false;//set game is not started
    private JButton btnStart = new JButton("Start"); //buttons of simulation run/stop and proceeding to next step
    private JButton btnStep = new JButton("Next step");
    private JButton btnColorWhite = new JButton("White"); //buttons to choose mouse brush color
    private JButton btnColorRed = new JButton("Red");
    private JButton btnColorGreen = new JButton("Green");
    private JButton btnColorBlue = new JButton("Blue");
    private JButton btnClear = new JButton("Clear field"); //button to clear all field
    private JButton btnSlower = new JButton("Slower"); //button to change animation speed
    private JButton btnFaster = new JButton("Faster");
    private JButton btnAbout = new JButton("About"); //button to get info about the program
    private Field field = new Field(); //create new field
    private Timer timer; //create timer

    private Life() //create instance of program
    {
        JPanel buttonPanel = new JPanel(); //panel for buttons
        InnerListener listener = new InnerListener(); //listener for button events
        btnStep.addActionListener(listener); //add listener for all buttons
        btnStart.addActionListener(listener);
        btnColorWhite.addActionListener(listener);
        btnColorRed.addActionListener(listener);
        btnColorGreen.addActionListener(listener);
        btnColorBlue.addActionListener(listener);
        btnClear.addActionListener(listener);
        btnSlower.addActionListener(listener);
        btnFaster.addActionListener(listener);
        btnAbout.addActionListener(listener);
        this.setTitle("Game of Life"); //set frame title
        setResizable(false); //disallow resize of window
        getContentPane().setBackground(new Color(50,50,50)); //set background color
        setSize(805,665); //set app size
        setLayout(new BorderLayout()); //set border layout
        field.setBackground(new Color(32,32,32)); //set field background color
        add(field, BorderLayout.CENTER); //add field to center
        add(buttonPanel, BorderLayout.SOUTH); //add button panel to bottom
        buttonPanel.add(btnStart); //add all buttons to button panel
        buttonPanel.add(btnStep);
        buttonPanel.add(btnColorWhite);
        buttonPanel.add(btnColorRed);
        buttonPanel.add(btnColorGreen);
        buttonPanel.add(btnColorBlue);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnSlower);
        buttonPanel.add(btnFaster);
        buttonPanel.add(btnAbout);
        timer = new Timer(timerDelay, new TimerTick()); //create new timer
        field.addMouseListener(new MouseListener() //add mouse listener to field
        {
            public void mouseExited(MouseEvent a){}
            public void mouseClicked(MouseEvent a) //if mouse clicked on field
            {
                if (a.getButton() == MouseEvent.BUTTON1) //if right-clicked
                {
                    field.setCell(true,a.getX()/cellSize,a.getY()/cellSize,mouseChannelRed,mouseChannelGreen,mouseChannelBlue); //set cell
                }
                if (a.getButton() == MouseEvent.BUTTON3) //if left-clicked
                {
                    field.setCell(false,a.getX()/cellSize,a.getY()/cellSize,0,0,0); //remove cell
                }
            }
            public void mouseEntered(MouseEvent a) {}
            public void mouseReleased(MouseEvent a) {}
            public void mousePressed(MouseEvent a) {}
        });
        field.addMouseMotionListener(new MouseMotionListener() //add mouse motion listener
        {
             @Override
             public void mouseDragged(MouseEvent e) //if mouse dragged on field
             {
                 if (SwingUtilities.isLeftMouseButton(e))  //if dragged with left click
                 {
                     field.setCell(true,e.getX()/cellSize,e.getY()/cellSize,mouseChannelRed,mouseChannelGreen,mouseChannelBlue); //set cell
                 }
                 if (SwingUtilities.isRightMouseButton(e)) //if dragged with right click
                 {
                     field.setCell(false,e.getX()/cellSize,e.getY()/cellSize,0,0,0); //remove cell
                 }
             }
             @Override
             public void mouseMoved(MouseEvent e) {}
        });
        setVisible(true); //set app visible
    }

    class TimerTick implements ActionListener //timer listener
    {
        @Override
        public void actionPerformed(ActionEvent e) //listener of timer actions
        {
            field.stepCalculation(); //calculate next state of field
            if (!simulationRunning) //if simulation was stopped
            {
                timer.stop(); //stop timer
            }
        }
    }

    public static void main(String[] args)
    {
        new Life();
    } //main function, creation of program

    class InnerListener implements ActionListener //listener of buttons
    {
        public void actionPerformed(ActionEvent ae) //button actions
        {
            if (ae.getSource()==btnStep)//if button was Next step
            {
                field.stepCalculation(); //calculate next state of field
            }
            if (ae.getSource()==btnStart) //if button was Start/Stop
            {
                if (!simulationRunning) //if button was in stop-mode
                {
                    simulationRunning=true; //enter simulation mode
                    btnStart.setText("Stop"); //change button title
                    timer.start(); //start timer
                }
                else //if button was in start-mode
                {
                    simulationRunning=false; //exit simulation mode
                    btnStart.setText("Start"); //change button title
                }
            }
            if (ae.getSource()==btnColorWhite) //if button was white
            {
                mouseChannelRed=255; //set white brush color
                mouseChannelGreen=255;
                mouseChannelBlue=255;
            }
            if (ae.getSource()==btnColorRed) //if button was red
            {
                mouseChannelRed=255; //set red brush color
                mouseChannelGreen=0;
                mouseChannelBlue=0;
            }
            if (ae.getSource()==btnColorGreen) //if button was green
            {
                mouseChannelRed=0; //set green brush color
                mouseChannelGreen=255;
                mouseChannelBlue=0;
            }
            if (ae.getSource()==btnColorBlue) //if button was Blue
            {
                mouseChannelRed=0; //set blue brush color
                mouseChannelGreen=0;
                mouseChannelBlue=255;
            }
            if (ae.getSource()==btnClear) //if button was Clear
            {
                field.clearField(); //clear field
            }
            if (ae.getSource()==btnSlower) //if button was Slower
            {
                timerDelay+=10; //increase delay
                timer.setDelay(timerDelay); //change timer delay
            }
            if (ae.getSource()==btnFaster) //if button was Faster
            {
                timerDelay-=10; //decrease delay
                if (timerDelay<0) //prevent negative values
                    timerDelay=0;
                timer.setDelay(timerDelay); //change timer delay
            }
            if (ae.getSource()==btnAbout) //if button was About show info about program
            {
                JOptionPane.showMessageDialog(null, "Game of Life\nversion 0.0.1.0(alpha)\n\nDevelopment: Yaskovich Dmitry\nDesign: Yaskovich Dmitry\nQuality assuarance: Yaskovich Dmitry\n\n© Dimini Inc., 2016");
            }
        }
    }
}
