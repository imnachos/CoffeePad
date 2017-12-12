package com.imnachos.coffeepad.Listener;

import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class FooterListener implements CaretListener {

    private int lineNumber;
    private int columnNumber;
    private int caretPos;
    private TextContainer canvas;

    public void caretUpdate(CaretEvent event) {
        canvas = (TextContainer)event.getSource();

        lineNumber = 1;
        columnNumber = 1;


        try {
            caretPos = canvas.getCaretPosition();
            lineNumber = canvas.getLineOfOffset(caretPos);
            columnNumber = caretPos - canvas.getLineStartOffset(lineNumber);
            lineNumber += 1;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), Settings.ERROR_CARET_LOCATION, Settings.ERROR_WINDOW_TITLE, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        canvas.updateCaretPosition(lineNumber, columnNumber);
    }
}
