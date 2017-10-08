package com.imnachos.coffeepad.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/*
    Abstract class that represents a command.
 */
public abstract class Command extends AbstractAction{

    public abstract void actionPerformed(ActionEvent event);

    public abstract void undoAction(ActionEvent event);

}
