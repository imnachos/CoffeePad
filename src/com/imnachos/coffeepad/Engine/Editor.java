package com.imnachos.coffeepad.Engine;

import com.imnachos.coffeepad.Commands.Command;
import com.imnachos.coffeepad.Util.CommandManager;
import com.imnachos.coffeepad.Util.LogManager;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class Editor extends JFrame implements ActionListener{

    private JMenuBar topBar;
    private JToolBar toolbar;
    private JScrollPane scrollbar;

    //Menu
    private JMenu MENU_FILE;
    private JMenu MENU_EDIT;

    //Text utils
    public String clipboard;
    public JTextArea canvas;
    public CommandManager commandManager;
    public UndoManager undoManager;

    /*
        Initialization of the text editor.
     */
    public Editor(){
        super(Settings.DEFAULT_TITLE);
        ImageIcon img = new ImageIcon(Settings.WINDOW_ICON);
        this.setIconImage(img.getImage());
        commandManager = new CommandManager();
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LogManager.printLog("Unhandled exception. Todo.");
            e.printStackTrace();
        }

        canvas = new JTextArea();
        topBar = new JMenuBar();

        scrollbar = new JScrollPane(canvas);
        scrollbar.setBorder(null);


        canvas.setLineWrap(true);
        canvas.setWrapStyleWord(true);
        canvas.setBorder(null);

        MENU_FILE = new JMenu(Settings.LABEL_FILE);
        buildMenu(MENU_FILE, Settings.FUNCTIONS_FILE);
        MENU_EDIT = new JMenu(Settings.LABEL_EDIT);
        buildMenu(MENU_EDIT, Settings.FUNCTIONS_EDIT);

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
        Construct menus from a given map for a JMenu
     */
    private void buildMenu(JMenu menu, Map<String, Integer> functionMap){
        functionMap.forEach((key, value) -> {
            JMenuItem item = new JMenuItem(key);
            String classPath = "com.imnachos.coffeepad.Commands." + key;
            classPath = classPath.replaceAll("\\s+","");
            try{
                Command action = (Command) Class.forName(classPath).newInstance();
                item.setAction(action);
                item.setAccelerator(KeyStroke.getKeyStroke(value, ActionEvent.CTRL_MASK));
                item.setName(key);
                item.setText(key);
                Image iconImage = new ImageIcon("data/images/" + key + ".png").getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(iconImage);
                item.setIcon(icon);

            }catch(Exception e){
                LogManager.printLog("Unhandled exception. Todo.");
                e.printStackTrace();
            }
            menu.add(item);
        });
    }


    public boolean isDocumentEmpty(){
        return this.canvas.getText().isEmpty();
    }

    /*
        Implementation of actionPerformed method.
     */
    public void actionPerformed(ActionEvent event) {

        JMenuItem item = (JMenuItem) event.getSource();
        item.getAction().actionPerformed(event);

    }


}

