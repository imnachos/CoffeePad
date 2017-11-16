package com.imnachos.coffeepad.Listener;

import com.imnachos.coffeepad.Editor.TextContainer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FormatKeyListener extends AbstractAction {

    private TextContainer canvas;

    public FormatKeyListener(TextContainer container){
        canvas = container;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            canvas.applyFormat();
        }catch(Exception e){
            e.printStackTrace();
            //TODO Exceptions
        }
    }

}
