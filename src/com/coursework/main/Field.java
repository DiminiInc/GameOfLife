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
                FieldArray[i][j].setAlive(true);
                FieldArray[i][j].setChannelRed(i);
                FieldArray[i][j].setChannelGreen(j);
                FieldArray[i][j].setChannelBlue(i/2+j);
            }
//
        }
    }

    public void stepCalculation()
    {
        for (int i=0;i<boardWidthInCells;i++)
        {
            for (int j=0;j<boardHeightInCells;j++)
            {
                FieldArrayNext[i][j].setChannelRed(255);
                FieldArrayNext[i][j].setChannelGreen(255);
                FieldArrayNext[i][j].setChannelBlue(255);
               cellRecalculation(i,j);
            }
//
        }
        FieldArray=FieldArrayNext;
        super.repaint();
        for (int i=0;i<boardWidthInCells;i++) {
            for (int j = 0; j < boardHeightInCells; j++) {
                FieldArrayNext[i][j].setChannelRed(255);
                FieldArrayNext[i][j].setChannelGreen(255);
                FieldArrayNext[i][j].setChannelBlue(255);
            }
        }

    }
    private void cellRecalculation(int xIndex, int yIndex)
    {
        int red=0,green=0,blue=0,quantity=0,iIndex,jIndex;

        for (int i=(xIndex-1);i<=(xIndex+1);i++)
        {
            for (int j=(yIndex-1);j<=(yIndex+1);j++)
            {
                if (i!=j)
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
                        red+=FieldArray[iIndex][jIndex].getChannelRed();
                        green+=FieldArray[iIndex][jIndex].getChannelGreen();
                        blue+=FieldArray[iIndex][jIndex].getChannelBlue();
                    }
                }
            }
//
        }

        if (FieldArray[xIndex][yIndex].isAlive())
        {
           if (quantity>3)
           {
               FieldArrayNext[xIndex][yIndex].setAlive(false);
               FieldArrayNext[xIndex][yIndex].setChannelRed(0);
               FieldArrayNext[xIndex][yIndex].setChannelGreen(0);
               FieldArrayNext[xIndex][yIndex].setChannelBlue(0);

           }
        }
        else
        {
            if (quantity==3)
            {
                FieldArrayNext[xIndex][yIndex].setAlive(true);
                FieldArrayNext[xIndex][yIndex].setChannelRed(red/3);
                FieldArrayNext[xIndex][yIndex].setChannelGreen(green/3);
                FieldArrayNext[xIndex][yIndex].setChannelBlue(blue/3);
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
