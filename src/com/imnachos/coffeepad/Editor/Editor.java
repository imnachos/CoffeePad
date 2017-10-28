package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Commands.ChangeStyle;
import com.imnachos.coffeepad.Commands.Command;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Style.LanguageStyle;
import com.imnachos.coffeepad.Style.LanguageStyleManager;
import com.imnachos.coffeepad.Util.CommandManager;
import com.imnachos.coffeepad.Util.LogManager;
import com.imnachos.coffeepad.Windows.SaveAs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Editor extends JFrame implements ActionListener{

    private JMenuBar toolbar;

    //Menu
    private JMenu MENU_FILE;
    private JMenu MENU_EDIT;
    private JMenu MENU_STYLE;


    /*
        Panel that contains the TextContainer. It provides de background color.
     */
    private JPanel textPanel;
    public TextContainer canvas;
    private JScrollPane scrollbars;

    private boolean isFileSaved;
    private File currentFile;
    public CommandManager commandManager;

    public LanguageStyle currentLanguageStyle;
    public Map<String, LanguageStyle> styledLanguages;


	/*
        Initialization of the text editor.
     */
    public Editor(){
        super(Settings.DEFAULT_TITLE);
        isFileSaved = false;
        ImageIcon windowIcon = new ImageIcon(Settings.WINDOW_ICON);
        this.setIconImage(windowIcon.getImage());
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        setOnCloseProperty();
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());
        contentPane.setForeground(Settings.DEFAULT_BACKGROUND);



        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LogManager.printLog("Unhandled exception. Todo.");
            e.printStackTrace();
        }
        styledLanguages = new HashMap<String, LanguageStyle>();
        styledLanguages = LanguageStyleManager.loadStyles();
        commandManager = new CommandManager();

        textPanel = new JPanel(new BorderLayout());
        textPanel.setForeground(Settings.DEFAULT_BACKGROUND);

        canvas = new TextContainer();

        scrollbars = new JScrollPane(canvas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbars.setBackground(Settings.GUI_COLOR);
        scrollbars.setForeground(Settings.GUI_COLOR);

        TextLineNumber numbering = new TextLineNumber(canvas);
        canvas.add(numbering);

        textPanel.add(scrollbars, BorderLayout.CENTER);
        add(textPanel, BorderLayout.WEST);


        //Menu items
        MENU_FILE = new JMenu(Settings.LABEL_FILE);
        buildMenu(MENU_FILE, Settings.FUNCTIONS_FILE);
        MENU_EDIT = new JMenu(Settings.LABEL_EDIT);
        buildMenu(MENU_EDIT, Settings.FUNCTIONS_EDIT);
        MENU_STYLE = new JMenu(Settings.LABEL_STYLE);
        buildStyleMenu(MENU_STYLE, styledLanguages);
        Font GUIFont = new Font(Font.MONOSPACED,12, 0);

        //File bar
        toolbar = new JMenuBar();
        toolbar.add(MENU_FILE);
        toolbar.add(MENU_EDIT);
        toolbar.add(MENU_STYLE);
        toolbar.setBackground(Settings.GUI_COLOR);
        toolbar.setForeground(Settings.GUI_COLOR);
        setJMenuBar(toolbar);

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
                LogManager.printLog("Unhandled exception. Todo."); //TODO EXCEPTION
                e.printStackTrace();
            }
            menu.add(item);
        });
    }


    /*
        Construct menus from a given map for a JMenu
     */
    private void buildStyleMenu(JMenu menu, Map<String, LanguageStyle> languageList){
        languageList.forEach((value, key) -> {
            JMenuItem item = new JMenuItem(key.languageName);
            try{
                Command action = ChangeStyle.class.newInstance();
                item.setAction(action);
                item.setName(key.languageName);
                item.setText(key.languageName);

            }catch(Exception e){
                LogManager.printLog("Unhandled exception. Todo."); //TODO EXCEPTION
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

    /*
     * Add a On Close listener
     */
    private void setOnCloseProperty(){
    	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	addWindowListener(new WindowAdapter() {
    	    
    		//TODO Use lambdas

    		@Override
    	    public void windowClosing(WindowEvent we){ 
    	        String objButtons[] = {"Exit","Save"};
    	        
    	        if(!isFileSaved){
    	        	int choice = JOptionPane.showOptionDialog(null, "Exit without saving?", "Warning",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, objButtons, objButtons[1]);
        	        
        	        if(choice == JOptionPane.YES_OPTION){
        	            System.exit(0);
        	        }else{
                        new SaveAs();
        	        }
    	        }else{
    	        	System.exit(0);
    	        }
    	    }
    	});
    }


    public void addLanguageStyle(LanguageStyle style){
        styledLanguages.putIfAbsent(style.languageName, style);
    }

    public boolean isFileSaved() {
		return isFileSaved;
	}

	public void setFileSaved(boolean isFileSaved) {
		this.isFileSaved = isFileSaved;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

    public LanguageStyle getCurrentLanguageStyle() {
        return currentLanguageStyle;
    }

    public void setCurrentLanguageStyle(LanguageStyle currentLanguageStyle) {
        this.currentLanguageStyle = currentLanguageStyle;
        canvas.textListener.setCurrentStyle(currentLanguageStyle);
    }





}

