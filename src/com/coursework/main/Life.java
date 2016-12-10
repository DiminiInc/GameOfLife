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
    JFrame frame = new JFrame();
    boolean simulationRunning=false;

    //JPanel boardCanvas = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton btnStart = new JButton("Start");
    JButton btnStep = new JButton("Next step");
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
        timer = new Timer(10, new TimerTick());
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
