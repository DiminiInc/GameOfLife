package com.coursework.main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yaskovich Dmitry on 17/11/2016.
 */
public class Field extends JPanel{
    FieldElement[][] FieldArray = new FieldElement[100][100];
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawLine(0, 0, 100, 100);
    }
}
