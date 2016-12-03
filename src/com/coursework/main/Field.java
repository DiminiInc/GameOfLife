package com.coursework.main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yaskovich Dmitry on 17/11/2016.
 */
public class Field extends JPanel{
    private int	boardWidthInCells = 200;
    private int	boardHeightInCells = 100;
    FieldElement[][] FieldArray = new FieldElement[boardWidthInCells][boardHeightInCells];
    FieldElement[][] FieldArrayNext = new FieldElement[boardWidthInCells][boardHeightInCells];
    private int	cellSize = 8;
    public Field(){
        for (int i=0;i<boardWidthInCells;i++)
        {
            for (int j=0;j<boardHeightInCells;j++)
            {
                FieldArray[i][j]=new FieldElement();
                FieldArrayNext[i][j]=new FieldElement();
                if ((i==0)|| (i==49)|| (i==99)|| (i==149) || (i==199) ||(j==0)|| (j==49)|| (j==99)) {

                    FieldArray[i][j].setAlive(true);
                    FieldArray[i][j].setChannelRed(i);
                    FieldArray[i][j].setChannelGreen(j);
                    FieldArray[i][j].setChannelBlue(i / 2 + j);

                    if (i==149)
                    {
                        FieldArray[i][j].setChannelRed(0);
                        FieldArray[i][j].setChannelGreen(255);
                        FieldArray[i][j].setChannelBlue(0);
                    }
                    if (i==99)
                    {
                        FieldArray[i][j].setChannelRed(255);
                        FieldArray[i][j].setChannelGreen(0);
                        FieldArray[i][j].setChannelBlue(0);
                    }
                    if (i==49)
                    {
                        FieldArray[i][j].setChannelRed(0);
                        FieldArray[i][j].setChannelGreen(0);
                        FieldArray[i][j].setChannelBlue(255);
                    }


                }
            }
//
        }
    }

    public void stepCalculation()
    {
        for (int i=0;i<boardWidthInCells;i++) {
            for (int j = 0; j < boardHeightInCells; j++) {
                FieldArrayNext[i][j].setChannelRed(0);
                FieldArrayNext[i][j].setChannelGreen(0);
                FieldArrayNext[i][j].setChannelBlue(0);
                FieldArrayNext[i][j].setAlive(false);
            }
        }

        for (int i=0;i<boardWidthInCells;i++)
        {
            for (int j=0;j<boardHeightInCells;j++)
            {


               cellRecalculation(i,j);
            }
//
        }
        FieldElement[][] t = FieldArray;
        FieldArray = FieldArrayNext;
        FieldArrayNext = t;
        //FieldArray=FieldArrayNext;
        repaint();
//        for (int i=0;i<boardWidthInCells;i++) {
//            for (int j = 0; j < boardHeightInCells; j++) {
//                FieldArrayNext[i][j].setChannelRed(255);
//                FieldArrayNext[i][j].setChannelGreen(255);
//                FieldArrayNext[i][j].setChannelBlue(255);
//            }
//        }

    }
    private void cellRecalculation(int xIndex, int yIndex)
    {
        int red=0,green=0,blue=0,quantity=0,iIndex,jIndex;

        for (int i=(xIndex-1);i<=(xIndex+1);i++)
        {
            for (int j=(yIndex-1);j<=(yIndex+1);j++)
            {
                if ((i!=xIndex)||(j!=yIndex))
                {
                    iIndex=i;
                    jIndex=j;
                    if (i<0)
                    {
                        iIndex=i+boardWidthInCells;
                    }
                    if (i>=boardWidthInCells)
                    {
                        iIndex=i-boardWidthInCells;
                    }
                    if (j<0)
                    {
                        jIndex=j+boardHeightInCells;
                    }
                    if (j>=boardHeightInCells)
                    {
                        jIndex=j-boardHeightInCells;
                    }
                    if ( FieldArray[iIndex][jIndex].isAlive())
                    {
                        quantity++;
                        if (FieldArray[iIndex][jIndex].getChannelRed()>red)
                            red=FieldArray[iIndex][jIndex].getChannelRed();
                        if (FieldArray[iIndex][jIndex].getChannelBlue()>blue)
                            blue=FieldArray[iIndex][jIndex].getChannelBlue();
                        if (FieldArray[iIndex][jIndex].getChannelGreen()>green)
                            green=FieldArray[iIndex][jIndex].getChannelGreen();
//                        red+=FieldArray[iIndex][jIndex].getChannelRed();
//                        green+=FieldArray[iIndex][jIndex].getChannelGreen();
//                        blue+=FieldArray[iIndex][jIndex].getChannelBlue();
                    }
                }
            }
//
        }

        if (FieldArray[xIndex][yIndex].isAlive())
        {
           if ((quantity<2)||(quantity>3))
           {
               FieldArrayNext[xIndex][yIndex].setAlive(false);
               FieldArrayNext[xIndex][yIndex].setChannelRed(0);
               FieldArrayNext[xIndex][yIndex].setChannelGreen(0);
               FieldArrayNext[xIndex][yIndex].setChannelBlue(0);

           }
           else
           {
               FieldArrayNext[xIndex][yIndex].setAlive(FieldArray[xIndex][yIndex].isAlive());
               FieldArrayNext[xIndex][yIndex].setChannelRed(FieldArray[xIndex][yIndex].getChannelRed());
               FieldArrayNext[xIndex][yIndex].setChannelGreen(FieldArray[xIndex][yIndex].getChannelGreen());
               FieldArrayNext[xIndex][yIndex].setChannelBlue(FieldArray[xIndex][yIndex].getChannelBlue());
           }
        }
        else
        {
            if (quantity==3)
            {
                FieldArrayNext[xIndex][yIndex].setAlive(true);
                red-=1;
                blue-=1;
                green-=1;
                if (red<0)
                    red=0;
                if (green<0)
                    green=0;
                if (blue<0)
                    blue=0;
                if (red>255)
                    red=255;
                if (green>255)
                    green=255;
                if (blue>255)
                    blue=255;
                FieldArrayNext[xIndex][yIndex].setChannelRed(red);
                FieldArrayNext[xIndex][yIndex].setChannelGreen(green);
                FieldArrayNext[xIndex][yIndex].setChannelBlue(blue);
            }
            else
            {
                FieldArrayNext[xIndex][yIndex].setAlive(FieldArray[xIndex][yIndex].isAlive());
                FieldArrayNext[xIndex][yIndex].setChannelRed(FieldArray[xIndex][yIndex].getChannelRed());
                FieldArrayNext[xIndex][yIndex].setChannelGreen(FieldArray[xIndex][yIndex].getChannelGreen());
                FieldArrayNext[xIndex][yIndex].setChannelBlue(FieldArray[xIndex][yIndex].getChannelBlue());
            }
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        for (int i=0;i<boardWidthInCells;i++)
        {
            for (int j=0;j<boardHeightInCells;j++)
            {
                g.setColor(new Color(FieldArray[i][j].getChannelRed(),FieldArray[i][j].getChannelGreen(),FieldArray[i][j].getChannelBlue()));
                g.fillRect(i*cellSize-1,j*cellSize-1,cellSize-1,cellSize-1);
            }
//
        }
//        g.drawLine(0, 0, 1000, 1000);
//        g.drawLine(800, 0, 0, 800);
    }
}
