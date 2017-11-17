package com.imnachos.coffeepad.Listener;

import com.imnachos.coffeepad.Editor.TextContainer;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class FooterListener implements CaretListener {

    private int linenum;
    private int columnnum;
    private int caretPos;
    private TextContainer canvas;

    public void caretUpdate(CaretEvent event) {
        canvas = (TextContainer)event.getSource();

        linenum = 1;
        columnnum = 1;


        try {
            caretPos = canvas.getCaretPosition();
            linenum = canvas.getLineOfOffset(caretPos);
            columnnum = caretPos - canvas.getLineStartOffset(linenum);
            linenum += 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            //TODO EXCEPTION
        }

        canvas.updateCaretPosition(linenum, columnnum);
    }
}
