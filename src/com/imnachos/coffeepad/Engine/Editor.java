package com.imnachos.coffeepad.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Editor extends JFrame implements ActionListener{

    private JMenuBar topBar;
    private JToolBar toolbar;
    private JScrollPane scrollbar;

    private JTextArea canvas;

    //Menu
    private JMenu MENU_FILE, MENU_EDIT;

    //Text utils
    private String clipboard;

    /*
        Initialization of the text editor.
     */

    public Editor(){
        super(Settings.DEFAULT_TITLE);
        ImageIcon img = new ImageIcon(Settings.WINDOW_ICON);
        this.setIconImage(img.getImage());

        setSize(Settings.DEFAULT_WINDOW_WIDTH, Settings.DEFAULT_WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.printLog("Unhandled exception. Todo.");
        }

        canvas = new JTextArea();
        topBar = new JMenuBar();
        scrollbar = new JScrollPane(canvas);

        canvas.setLineWrap(true);
        canvas.setWrapStyleWord(true);

        MENU_FILE = new JMenu(Settings.LABEL_FILE);
        buildFileMenu(MENU_FILE);
        MENU_EDIT = new JMenu(Settings.LABEL_EDIT);
        buildEditMenu(MENU_EDIT);

        //Create toolbars

        toolbar = new JToolBar();
        setJMenuBar(topBar);
        topBar.add(MENU_FILE);
        topBar.add(MENU_EDIT);

        pane.add(scrollbar, BorderLayout.CENTER);
        pane.add(toolbar, BorderLayout.SOUTH);

        clipboard = "";

        setVisible(true);
    }

    /*
        Construct the edit menu items.
     */
    private void buildEditMenu(JMenu menu){

        Settings.FUNCTIONS_EDIT.forEach((key, value) -> {
            JMenuItem item = new JMenuItem(key);
            item.addActionListener(this);
            item.setAccelerator(KeyStroke.getKeyStroke(value, ActionEvent.CTRL_MASK));
            item.setName(key);
            menu.add(item);
        });

    }
    /*
        Construct file menu items.
     */
    private void buildFileMenu(JMenu menu){
        Settings.FUNCTIONS_FILE.forEach((key, value) -> {
            JMenuItem item = new JMenuItem(key);
            item.addActionListener(this);
            item.setAccelerator(KeyStroke.getKeyStroke(value, ActionEvent.CTRL_MASK));
            item.setName(key);
            menu.add(item);
        });
    }

    /*
        Implementation of actionPerformed method.
     */

    public void actionPerformed(ActionEvent event) {
        JMenuItem clickedItem = (JMenuItem) event.getSource();

         switch(clickedItem.getName()){

             case Settings.LABEL_CUT:
                clipboard = canvas.getSelectedText();
                canvas.replaceRange("", canvas.getSelectionStart(), canvas.getSelectionEnd());
                break;

             case Settings.LABEL_COPY:
                 clipboard = canvas.getSelectedText();
                 break;

             case Settings.LABEL_PASTE:
                 canvas.insert(clipboard, canvas.getCaretPosition());
                 break;

             case Settings.LABEL_SELECT:
                 canvas.selectAll();
                 break;

             case Settings.LABEL_SAVE:
                 break;

             case Settings.LABEL_EXIT:
                 System.exit(0);
                 break;
        }

    }
}

