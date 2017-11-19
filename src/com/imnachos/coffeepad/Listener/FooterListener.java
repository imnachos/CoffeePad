package com.imnachos.coffeepad.Listener;

import com.imnachos.coffeepad.Editor.TextContainer;
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
            e.printStackTrace();
            //TODO EXCEPTION
        }

        canvas.updateCaretPosition(lineNumber, columnNumber);
    }
}
