package com.coursework.main;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Created by Yaskovich Dmitry on 16/11/2016.
 */
public class Life extends JFrame{

//    int countMilan;
//    int countReal;
private int	cellSize = 8;
private boolean rightMouseClick,leftMouseClick;
private int mouseChannelRed=255, mouseChannelGreen=0,mouseChannelBlue=0;
    JFrame frame = new JFrame();
    boolean simulationRunning=false;

    //JPanel boardCanvas = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton btnStart = new JButton("Start");
    JButton btnStep = new JButton("Next step");

    JButton btnColorRed = new JButton("Red");
    JButton btnColorGreen = new JButton("Green");
    JButton btnColorBlue = new JButton("Blue");

    JButton btnClear = new JButton("Clear field");
    Field field = new Field();
    Timer timer;
//
//    JButton btnMilan=new JButton("AC Milan");
//    JButton btnReal=new JButton("Real Madrid");
//    JLabel lblScore=new JLabel("Result: 0 x 0");
//    JLabel lblScorer=new JLabel("Last Scorer: N/A");
//    JLabel lblResult=new JLabel("Winner: DRAW");
//    Graphics boardCanvas;
//    public void paint(Graphics g) {
//        g.drawRect(20,10,100,60);
//    }

    public Life()
    {

        InnerListener listener = new InnerListener();
//        btnMilan.addActionListener(listener);
//        btnReal.addActionListener(listener);
        btnStep.addActionListener(listener);
        btnStart.addActionListener(listener);
        btnColorRed.addActionListener(listener);
        btnColorGreen.addActionListener(listener);
        btnColorBlue.addActionListener(listener);
        btnClear.addActionListener(listener);
        this.setTitle("Game of Life");
       // setResizable(false);
        getContentPane().setBackground(new Color(50,50,50));
        setSize(1700,900);
        setLayout(new BorderLayout());

        //field.setSize(1000,500);
        field.setBackground(new Color(32,32,32));
        add(field, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStep);
        buttonPanel.add(btnColorRed);
        buttonPanel.add(btnColorGreen);
        buttonPanel.add(btnColorBlue);
        buttonPanel.add(btnClear);
        timer = new Timer(10, new TimerTick());
        field.addMouseListener(new MouseListener()
        {
            public void mouseExited(MouseEvent a){}
            public void mouseClicked(MouseEvent a) {

            }
            public void mouseEntered(MouseEvent a) {}
            public void mouseReleased(MouseEvent a) {

            }
            public void mousePressed(MouseEvent a) {

            }

        });
        field.addMouseMotionListener(new MouseMotionListener() {
                                         @Override
                                         public void mouseDragged(MouseEvent e) {

                                             field.setCell(true,e.getX()/cellSize,e.getY()/cellSize);
                                             if (SwingUtilities.isLeftMouseButton(e)) {
                                                 field.setCell(true,e.getX()/cellSize,e.getY()/cellSize,mouseChannelRed,mouseChannelGreen,mouseChannelBlue);

                                             }
                                             if (SwingUtilities.isRightMouseButton(e)) {
                                                 field.setCell(false,e.getX()/cellSize,e.getY()/cellSize,0,0,0);
                                             }
                                         }

                                         @Override
                                         public void mouseMoved(MouseEvent e) {

                                         }
                                     }

        );
//        add(btnMilan);
//        add(btnReal);
//        add(lblScore);
//        add(lblScorer);
//        add(lblResult);
        setVisible(true);

//boardCanvas.drawOval(10,10,50,50);






    }
    class TimerTick implements ActionListener
    {


        @Override
        public void actionPerformed(ActionEvent e)
        {


            field.stepCalculation();



            if (simulationRunning==false)
            {
                timer.stop();

            }
        }

    }


    public static void main(String[] args)
    {
        new Life();
    }

    class InnerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getSource()==btnStep)
            {
                field.stepCalculation();
            }
            if (ae.getSource()==btnStart)
            {
                if (btnStart.getText()=="Start") {

                    simulationRunning=true;
                    btnStart.setText("Stop");
                    timer.start();
                }
                else
                {
                    simulationRunning=false;
                    btnStart.setText("Start");
                }

                //timer.start();
                //btnStart.setText("Stop");
                //timer.stop();

            }
            if (ae.getSource()==btnColorRed)
            {
                mouseChannelRed=255;
                mouseChannelGreen=0;
                mouseChannelBlue=0;
            }
            if (ae.getSource()==btnColorGreen)
            {
                mouseChannelRed=0;
                mouseChannelGreen=255;
                mouseChannelBlue=0;
            }
            if (ae.getSource()==btnColorBlue)
            {
                mouseChannelRed=0;
                mouseChannelGreen=0;
                mouseChannelBlue=255;
            }
            if (ae.getSource()==btnClear)
            {
                field.clearField();
            }

//            if(ae.getSource() == btnMilan)
//            {
//                countMilan++;
//                lblScore.setText("Result: "+countMilan+" x "+countReal);
//                lblScorer.setText("Last Scorer: AC Milan");
//                if (countMilan>countReal)
//                    lblResult.setText("Winner: AC Milan");
//                else
//                if (countMilan<countReal)
//                    lblResult.setText("Winner: Real Madrid");
//                else
//                    lblResult.setText("Winner: Draw");
//            }
//            else
//            {
//                countReal++;
//                lblScore.setText("Result: "+countMilan+" x "+countReal);
//                lblScorer.setText("Last Scorer: Real Madrid");
//                if (countMilan>countReal)
//                    lblResult.setText("Winner: AC Milan");
//                else
//                if (countMilan<countReal)
//                    lblResult.setText("Winner: Real Madrid");
//                else
//                    lblResult.setText("Winner: Draw");
//
//            }
        }
    }

}
