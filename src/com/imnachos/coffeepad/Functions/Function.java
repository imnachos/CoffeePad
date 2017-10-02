package com.imnachos.coffeepad.Functions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class Function extends AbstractAction {

    public abstract void actionPerformed(ActionEvent event);

    public abstract void undoAction(ActionEvent event);
}
