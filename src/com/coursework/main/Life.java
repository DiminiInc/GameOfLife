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

    //JPanel boardCanvas = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton btnStart = new JButton("Start/Stop");
    JButton btnStep = new JButton("Next step");
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
        this.setTitle("Game of Life");

        getContentPane().setBackground(new Color(200,200,200));
        setSize(600,600);
        setLayout(new BorderLayout());
        Field field = new Field();
        field.setSize(500,500);
        add(field, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStep);

//        add(btnMilan);
//        add(btnReal);
//        add(lblScore);
//        add(lblScorer);
//        add(lblResult);
        setVisible(true);

//boardCanvas.drawOval(10,10,50,50);






    }
    public static void main(String[] args)
    {
        new Life();
    }

    class InnerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
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
