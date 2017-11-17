package com.imnachos.coffeepad.Editor;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    private int line;
    private int column;

    public Footer(){
        setPreferredSize(new Dimension(20,20));
    }

    public void updateCaretPosition(int newLine, int newColumn){
        this.line = newLine;
        this.column = newColumn;
        repaint();
    }

    /**
     * Method to draw the text
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        String positionString = line + ":" + column;
        g.drawString(positionString, 20, 0);

    }
}
