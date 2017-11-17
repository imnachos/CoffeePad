package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Commands.ChangeStyle;
import com.imnachos.coffeepad.Commands.Command;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Style.LanguageStyle;
import com.imnachos.coffeepad.Style.LanguageStyleManager;
import com.imnachos.coffeepad.Windows.SaveAs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;

public class Editor extends JFrame implements ActionListener{

    private Toolbar toolbar;
    private Footer footer;
    private JPanel textPanel;
    public TextContainer canvas;
    private JScrollPane scrollbars;
    private LineNumberPanel lineNumberPanel;
    private ProjectTree projectTree;

    private boolean isFileSaved;
    private File currentFile;

    public Map<String, LanguageStyle> styledLanguages;


	/**
        Initialization of the text editor.
     */
    public Editor(){
        super(Settings.DEFAULT_TITLE);
        initialize();

        projectTree = new ProjectTree();
        styledLanguages = LanguageStyleManager.loadStyles();
        toolbar = new Toolbar(styledLanguages);
        setJMenuBar(toolbar);
        footer = new Footer();

        textPanel = new JPanel(new BorderLayout());
        canvas = new TextContainer();
        scrollbars = new JScrollPane(canvas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        lineNumberPanel = new LineNumberPanel(canvas);
        scrollbars.setRowHeaderView(lineNumberPanel);

        textPanel.add(scrollbars, BorderLayout.CENTER);
        textPanel.add(lineNumberPanel, BorderLayout.WEST);


        add(projectTree, BorderLayout.LINE_START);
        add(textPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);

    }

    /**
     * Sets up the JFrame
     */
    private void initialize(){
        isFileSaved = false;
        ImageIcon windowIcon = new ImageIcon(Settings.WINDOW_ICON);
        this.setIconImage(windowIcon.getImage());
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        setOnCloseProperty();
        getContentPane().setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unhandled exception. Todo.");
            //TODO EXCEPTIONS
            e.printStackTrace();
        }
    }


    /**
        Implementation of actionPerformed method.
     */
    public void actionPerformed(ActionEvent event) {
        JMenuItem item = (JMenuItem) event.getSource();
        item.getAction().actionPerformed(event);
    }

    /**
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

    public void setCurrentLanguageStyle(LanguageStyle currentLanguageStyle) {
        canvas.setCurrentStyle(currentLanguageStyle);
    }

    public void updateFooter(int line, int column){
        footer.updateCaretPosition(line, column);
    }





}

