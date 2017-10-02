package com.imnachos.coffeepad.Engine;

import com.imnachos.coffeepad.Util.CommandManager;
import com.imnachos.coffeepad.Util.LogManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class Editor extends JFrame implements ActionListener{

    private JMenuBar topBar;
    private JToolBar toolbar;
    private JScrollPane scrollbar;

    //Menu
    private JMenu MENU_FILE, MENU_EDIT;

    //Text utils
    public static String clipboard;
    public static JTextArea canvas;
    public static CommandManager commandManager;

    /*
        Initialization of the text editor.
     */
    public Editor(){
        super(Settings.DEFAULT_TITLE);
        ImageIcon img = new ImageIcon(Settings.WINDOW_ICON);
        this.setIconImage(img.getImage());

        commandManager = new CommandManager();

        setSize(Settings.DEFAULT_WINDOW_WIDTH, Settings.DEFAULT_WINDOW_HEIGHT);
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

        canvas.setLineWrap(true);
        canvas.setWrapStyleWord(true);

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
            String classPath = "com.imnachos.coffeepad.Functions." + key;
            classPath = classPath.replaceAll("\\s+","");
            item.addActionListener(this);
            item.setAccelerator(KeyStroke.getKeyStroke(value, ActionEvent.CTRL_MASK));
            item.setName(key);
            try{
                Object action = Class.forName(classPath).newInstance();
                item.setAction((AbstractAction) action);
            }catch(Exception e){
                LogManager.printLog("Unhandled exception. Todo.");
                e.printStackTrace();
            }
            menu.add(item);
        });
    }

    /*
        Implementation of actionPerformed method.
     */
    public void actionPerformed(ActionEvent event) {

        JMenuItem item = (JMenuItem) event.getSource();
        item.getAction().actionPerformed(event);

    }
}

