package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Commands.Command;
import com.imnachos.coffeepad.Commands.Saveas;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Listener.TextListener;
import com.imnachos.coffeepad.Util.CommandManager;
import com.imnachos.coffeepad.Util.LogManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;

public class Editor extends JFrame implements ActionListener{

    private JMenuBar topBar;

    //Menu
    private JMenu MENU_FILE;
    private JMenu MENU_EDIT;

    //Text util
    public String clipboard;
    
    //JTextPane
    public TextContainer textContainer;
    private JScrollPane scrollbar;

    /*
        Panel that contains the TextContainer. It provides de background color.
     */
    private JPanel textPanel;
    
    private boolean isFileSaved;
    private File currentFile;
    public CommandManager commandManager;

    public int fontSize;

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LogManager.printLog("Unhandled exception. Todo.");
            e.printStackTrace();
        }

        commandManager = new CommandManager();

        textPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        textPanel.setBackground(Settings.DEFAULT_BACKGROUND);
        textContainer = new TextContainer();
        scrollbar = new JScrollPane(textContainer);
        scrollbar.setBorder(null);
        textPanel.add(textContainer, BorderLayout.WEST);
        add(textPanel, BorderLayout.WEST);


        //Menu items
        MENU_FILE = new JMenu(Settings.LABEL_FILE);
        buildMenu(MENU_FILE, Settings.FUNCTIONS_FILE);
        MENU_EDIT = new JMenu(Settings.LABEL_EDIT);
        buildMenu(MENU_EDIT, Settings.FUNCTIONS_EDIT);


        //File bar
        topBar = new JMenuBar();
        topBar.add(MENU_FILE);
        topBar.add(MENU_EDIT);
        setJMenuBar(topBar);

        //Additional

        clipboard = "";

        //Text stuff

        fontSize = Settings.DEFAULT_FONT_SIZE;

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
    	    
    		//Use lambdas
    		@Override
    	    public void windowClosing(WindowEvent we){ 
    	        String ObjButtons[] = {"Exit","Save"};
    	        
    	        if(!isFileSaved){
    	        	int choice = JOptionPane.showOptionDialog(null, "Exit without saving?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        	        
        	        if(choice == JOptionPane.YES_OPTION){
        	            System.exit(0);
        	        }else{
        	        	new Saveas();
        	        }
    	        }else{
    	        	System.exit(0);
    	        }
    	    }
    	});
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
    


    

}
