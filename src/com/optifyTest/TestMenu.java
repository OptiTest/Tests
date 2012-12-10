package com.optifyTest;

import javax.swing.JFrame;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestMenu implements ActionListener{
	//Test system variables:
	private String homeServer="https://dashboard.optify.net";
	private String path="D:\\selenium-2.23.1\\chromedriver.exe";
	private static String userName="";
	private static String userPassword="";
	
	//Graphic variables:
	int point[]={0,0};
	protected final static String CREATE_WINDOW = "new_win"; 
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() throws Exception{
        //Create and set up the window.
        JFrame frame = new JFrame("Optify Automation Test System Version 2.0");
        Panel textPanel = new Panel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(400, 400));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        
        //Create GUI parts:
        final JTextField userText = new JTextField(10);
        final JTextField passwordText = new JTextField(10);
        
        //Set frame location "centered".
        centerScreen(frame);
         
        JButton button = new JButton("Run Test");
        button.setBounds(100, 30, 100, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //Get variables:
            	userName=userText.getText();
            	userPassword=passwordText.getText();
            	runTest();
           }
        });
        
        //Set panel & Display the window.
        frame.pack();
        //buttonPanel.add(button);
        
        textPanel.add(userText);
        textPanel.add(passwordText);
        textPanel.add(button);
        
        frame.add(textPanel,BorderLayout.NORTH);
        //frame.add(buttonPanel);
  
        frame.setVisible(true);
    }
 
    //Run test:
    private static void runTest(){
    	//JUnitCore.runClasses(DashBoard.class);
    	 Result result = JUnitCore.runClasses(DashBoard.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString());
 	    }
    }
    
    //Handle action events from all the buttons.===============================
    public void actionPerformed(ActionEvent e) {
        //String command = e.getActionCommand();

    	runTest();
    }
	
	//=========================================================================
	
	public String getHomeServer(){
		return homeServer;
	}
	
	//=========================================================================
	
	public String getPath(){
		return path;
	}
	
	//=========================================================================
	
	public String getUsername(){
		return userName;
	}
	
	//=========================================================================
	
	public String getPassword(){
		return userPassword;
	}
	
	//=========================================================================
	
	private static void centerScreen(Frame frame){
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    frame.setLocation(screenWidth / 3, screenHeight / 4);
	}
} 