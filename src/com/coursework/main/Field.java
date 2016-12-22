package com.coursework.main;

import javax.swing.*;
import java.awt.*;
/*
    Life model is running on torus surface, where left side is connected with right and bottom side is connected with top one
*/
public class Field extends JPanel{ //field of cells class
    private int	boardWidthInCells = 150; //board size in number of cells
    private int	boardHeightInCells = 100;
    private FieldElement[][] FieldArray = new FieldElement[boardWidthInCells][boardHeightInCells]; //array of cells
    private FieldElement[][] FieldArrayNext = new FieldElement[boardWidthInCells][boardHeightInCells]; //array of cells for next step calculation
    Field(){ //creation new instance of field
        for (int i=0;i<boardWidthInCells;i++) //cycle through all array elements
        {
            for (int j=0;j<boardHeightInCells;j++)
            {
                FieldArray[i][j]=new FieldElement(); //create instances of cell for both of arrays
                FieldArrayNext[i][j]=new FieldElement();
            }
        }
    }

    void stepCalculation() //next life iteration calculation
    {
        for (int i=0;i<boardWidthInCells;i++) //cycle through all array elements
        {
            for (int j = 0; j < boardHeightInCells; j++)
            {
                FieldArrayNext[i][j].setChannelRed(0); //wipe all previous data from array for next step
                FieldArrayNext[i][j].setChannelGreen(0);
                FieldArrayNext[i][j].setChannelBlue(0);
                FieldArrayNext[i][j].setAlive(false);
            }
        }
        for (int i=0;i<boardWidthInCells;i++) //cycle through all array elements
        {
            for (int j=0;j<boardHeightInCells;j++)
            {
               cellRecalculation(i,j); //calculate next cell state
            }
        }
        FieldElement[][] t = FieldArray; //swap next and current arrays using additional variable
        FieldArray = FieldArrayNext;
        FieldArrayNext = t;
        repaint(); //redraw field
    }

    private void cellRecalculation(int xIndex, int yIndex) //calculate next cell state
    {
        int red=0,green=0,blue=0,quantity=0,iIndex,jIndex; //local variable of cell colors, number of cell neighbours and cell indexes
        for (int i=(xIndex-1);i<=(xIndex+1);i++) //cycle through all neighbours of a cell
        {
            for (int j=(yIndex-1);j<=(yIndex+1);j++)
            {
                if ((i!=xIndex)||(j!=yIndex)) //if not cell itself
                {
                    iIndex=i; //transfer cell indexes to temporary ones
                    jIndex=j;
                    if (i<0) //if X coordinate is too low
                    {
                        iIndex=i+boardWidthInCells; //use cell from another side of field
                    }
                    if (i>=boardWidthInCells) //if X coordinate is too big
                    {
                        iIndex=i-boardWidthInCells; //use cell from another side of field
                    }
                    if (j<0) //if Y coordinate is too low
                    {
                        jIndex=j+boardHeightInCells; //use cell from another side of field
                    }
                    if (j>=boardHeightInCells) //if Y coordinate is too big
                    {
                        jIndex=j-boardHeightInCells; //use cell from another side of field
                    }
                    if ( FieldArray[iIndex][jIndex].isAlive()) //if neighbour is alive
                    {
                        quantity++; //increase quantity of neighbours
                        if (FieldArray[iIndex][jIndex].getChannelRed()>red) //set initial cell red channel as maximum from red channel values of cell and her neighbours
                            red=FieldArray[iIndex][jIndex].getChannelRed();
                        if (FieldArray[iIndex][jIndex].getChannelBlue()>blue) //set initial cell blue channel as maximum from blue channel values of cell and her neighbours
                            blue=FieldArray[iIndex][jIndex].getChannelBlue();
                        if (FieldArray[iIndex][jIndex].getChannelGreen()>green) //set initial cell green channel as maximum from green channel values of cell and her neighbours
                            green=FieldArray[iIndex][jIndex].getChannelGreen();
                    }
                }
            }
        }
        if (FieldArray[xIndex][yIndex].isAlive()) //if cell is alive
        {
           if ((quantity<2)||(quantity>3)) //if less than 2 or more than 3 neighbours
           {
               FieldArrayNext[xIndex][yIndex].setAlive(false); //kill cell in next step array
               FieldArrayNext[xIndex][yIndex].setChannelRed(0);
               FieldArrayNext[xIndex][yIndex].setChannelGreen(0);
               FieldArrayNext[xIndex][yIndex].setChannelBlue(0);

           }
           else //if 2 or 3 neighbours
           {
               FieldArrayNext[xIndex][yIndex].setAlive(FieldArray[xIndex][yIndex].isAlive()); //transfer cell data to next step array
               FieldArrayNext[xIndex][yIndex].setChannelRed(FieldArray[xIndex][yIndex].getChannelRed());
               FieldArrayNext[xIndex][yIndex].setChannelGreen(FieldArray[xIndex][yIndex].getChannelGreen());
               FieldArrayNext[xIndex][yIndex].setChannelBlue(FieldArray[xIndex][yIndex].getChannelBlue());
           }
        }
        else //if cell is dead
        {
            if (quantity==3) //if 3 neighbours
            {
                FieldArrayNext[xIndex][yIndex].setAlive(true); //spawn cell
                red-=1; //reduce all cell color channels by 1
                blue-=1;
                green-=1;
                if (red<0) //prevent color channels from negative values
                    red=0;
                if (green<0)
                    green=0;
                if (blue<0)
                    blue=0;
                FieldArrayNext[xIndex][yIndex].setChannelRed(red); //set cell channel colors
                FieldArrayNext[xIndex][yIndex].setChannelGreen(green);
                FieldArrayNext[xIndex][yIndex].setChannelBlue(blue);
            }
            else //if not 3 neighbours
            {
                FieldArrayNext[xIndex][yIndex].setAlive(FieldArray[xIndex][yIndex].isAlive()); //transfer cell data to next step array
                FieldArrayNext[xIndex][yIndex].setChannelRed(FieldArray[xIndex][yIndex].getChannelRed());
                FieldArrayNext[xIndex][yIndex].setChannelGreen(FieldArray[xIndex][yIndex].getChannelGreen());
                FieldArrayNext[xIndex][yIndex].setChannelBlue(FieldArray[xIndex][yIndex].getChannelBlue());
            }
        }
    }

    public void paint(Graphics g) //field draw
    {
        int	cellSize = 8;
        super.paint(g);
        for (int i=0;i<boardWidthInCells;i++) //cycle through all cells
        {
            for (int j=0;j<boardHeightInCells;j++)
            {
                g.setColor(new Color(FieldArray[i][j].getChannelRed(),FieldArray[i][j].getChannelGreen(),FieldArray[i][j].getChannelBlue())); //set brush color
                g.fillRect(i*cellSize-1,j*cellSize-1,cellSize-1,cellSize-1); //draw cell (decreased values for stroke around cell)
            }
        }
    }

//    void setCell(boolean alive, int xIndex, int yIndex) //default call to set new cell
//    {
//        FieldArray[xIndex][yIndex].setAlive(alive); //set cell alive
//        FieldArray[xIndex][yIndex].setChannelRed(255); //set default cell colors
//        FieldArray[xIndex][yIndex].setChannelGreen(255);
//        FieldArray[xIndex][yIndex].setChannelBlue(255);
//        repaint(); //redraw field
//    }

    void setCell(boolean alive, int xIndex, int yIndex, int channelRed, int channelGreen, int channelBlue) //set new cell
    {
        FieldArray[xIndex][yIndex].setAlive(alive); //set cell alive
        FieldArray[xIndex][yIndex].setChannelRed(channelRed); //set cell colors
        FieldArray[xIndex][yIndex].setChannelGreen(channelGreen);
        FieldArray[xIndex][yIndex].setChannelBlue(channelBlue);
        repaint(); //redraw field
    }

    void clearField () //clear field
    {
        for (int i=0;i<boardWidthInCells;i++) //cycle through all cells
        {
            for (int j = 0; j < boardHeightInCells; j++)
            {
                FieldArray[i][j].setChannelRed(0); //wipe all data about cells
                FieldArray[i][j].setChannelGreen(0);
                FieldArray[i][j].setChannelBlue(0);
                FieldArray[i][j].setAlive(false);
            }
        }
        repaint(); //redraw field
    }
}
