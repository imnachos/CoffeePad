package com.imnachos.coffeepad.Functions;

import com.imnachos.coffeepad.Engine.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Selectall extends Function{

    public void actionPerformed(ActionEvent event){
        Editor.canvas.selectAll();
    }

    public void undoAction(ActionEvent event){

    }
}
