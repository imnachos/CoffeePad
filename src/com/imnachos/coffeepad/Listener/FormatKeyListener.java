package com.imnachos.coffeepad.Listener;

import com.imnachos.coffeepad.Editor.TextContainer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Listens if SPACEBAR is pressed
 */
public class FormatKeyListener extends AbstractAction {

    private TextContainer canvas;

    public FormatKeyListener(TextContainer container){
        canvas = container;
    }

    /**
     * Calls the format applying method
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        canvas.applyFormat();
    }

}
