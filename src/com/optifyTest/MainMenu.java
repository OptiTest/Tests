/*
 * This project was written by Asnin Or to the Optify Company 2013.
 * Parts of the OATS system.
 * 
 * The MainMenu class.
 * ============================================================================
 * 
 * This class display the main menu of the OATS program and display to the user
 * the all program functionality, includes: settings options, selecting script 
 * to run,running new test scenario, change server and user information for the 
 * testing etc.
 * 
 * 
 * MainMenu methods:
 * =================
 * 
 * 1. MainMenu():
 * 
 *    The MainMenu constructor initialize the list using the manageTests class,
 *    In addition all the GUI components, user name & password fields and 
 *    create the JTree scripts to run selection tree.   
 * 
 * 
 * 2. private void initComponents(DefaultMutableTreeNode rootNode).
 *    
 *    This method received the JTree as explained and create the 
 *    main menu screen by using all GUI components.
 *    
 *    
 * 3. public void endApp()
 *    
 *    The method responsible to save the state of the JTree (With scripts has
 *    privilege to run) to data/data3 file. In addition responsible to end 
 *    the program and to end all her process.
 *    
 *    
 * 4. private DefaultMutableTreeNode creatJTree()
 * 
 *    This method responsible to create the JTree himself. The idea is very
 *    simple by creating two String vector, one vector include all tests pages
 *    arrays (the groupVector) when the second vector it's array which include 
 *    all the list scripts for each page. This group of vector with constructed
 *    from test pages and their scripts is the base of the construction of the
 *    JTree himself. After we have got the list name of the test script we want
 *    to organized them in a tree of check box list in order to use the ability
 *    to select or deselect. For this reason we are using the CheckBox class in
 *    order to built those check box from the vector list, and just adding 
 *    those check boxes into the JTree.
 *    
 *    
 * 5. private boolean getScriptsPrivilege(String name)
 *    
 *    This method checks with scripts as the privileged to run and "send" this
 *    information to the creatJTree method in order to built this tree, by
 *    the privilege information (with scripts enabled/disabled - true/false).
 *    The method get the privileged list from data/data3 file.
 *    
 *    
 * 6. private void saveJTree()
 * 
 * 	  This method will always run when exiting program command initiate.
 *    Her purpose is to save all the privilege test information (with script 
 *    has the permission to run) to the disk, data/data3 file.
 *    
 *      
 */


package com.optifyTest;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


public class MainMenu extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Settings set=new Settings();
	private Thread thread2;
	private ManageTests listTests;
	public CheckBoxNode testList[];
	private CheckBoxNodeRenderer renderer;
	private DefaultMutableTreeNode rootNode;
	public boolean select[];
	private String pageName;
	private boolean flag;
	
    public MainMenu() {
    	//Set MainMenu Components & default user & password:
    	this.listTests=new ManageTests();
    	
        renderer = new CheckBoxNodeRenderer();
        initComponents(creatJTree());
        
        jUserNameField.setText(set.getUserName());
        jPasswordField.setText(set.getUserPassword());
        
        pageName="";
        this.flag=false;    //Thread finish flag.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(DefaultMutableTreeNode rootNode) {
        jButtonRunTest = new javax.swing.JButton();
        jUserNameField = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jUserNameLabel = new javax.swing.JLabel();
        jUserPasswordLabel = new javax.swing.JLabel();
        jIconLabel = new javax.swing.JLabel();
        //jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree(rootNode);
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        
        JTree tree = new JTree(rootNode);
        tree.setToggleClickCount(1);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.putClientProperty("JTree.lineStyle", "None");
        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        tree.setCellRenderer(renderer);
        
        tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(true);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener(){                                                        
            public void actionPerformed(ActionEvent e)              
            {  if (e.getActionCommand().equals("Submit")) {
                System.out.println("Button1 has been clicked");
            } }});
        
        JScrollPane jScrollPane1 = new JScrollPane(tree);
       
        
        tree.setModel(new DefaultTreeModel(rootNode) {
    public void valueForPathChanged(TreePath path, Object newValue) {
        Object currNode = path.getLastPathComponent();
        super.valueForPathChanged(path, newValue);
        if ((currNode != null) && (currNode instanceof DefaultMutableTreeNode)) {
            DefaultMutableTreeNode editedNode = (DefaultMutableTreeNode) currNode;
            CheckBoxNode newCBN = (CheckBoxNode) newValue;
            
            if (!editedNode.isLeaf()) {
                for (int i = 0; i < editedNode.getChildCount(); i++) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getChildAt(i);
                    CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                    cbn.setSelected(newCBN.isSelected());
                }
            }
            else{
                boolean isAllChiledSelected = true;
               for (int i = 0; i < editedNode.getParent().getChildCount(); i++) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent().getChildAt(i);
                    CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                    if(!cbn.isSelected()){
                        isAllChiledSelected = false;
                    }
                }
                
                if(isAllChiledSelected){
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)editedNode.getParent();
                      CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                    cbn.setSelected(isAllChiledSelected);
                }
            }
            
            if (!newCBN.isSelected()) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
                if (node.getUserObject() instanceof CheckBoxNode)
                    ((CheckBoxNode) node.getUserObject()).setSelected(false);                            
            }                                        
        }
    }
});

        
        //Close App & process.
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
          addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent e){
        	  endApp();
          }
        });
        
        setTitle("Optify Automation Suite 2.0");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setResizable(false);
        

        jButtonRunTest.setBackground(new java.awt.Color(255, 153, 0));
        jButtonRunTest.setText("Run Test");
        jButtonRunTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                try {
					jButtonRunTestActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jUserNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUserNameFieldActionPerformed(evt);
            }
        });

        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });

        jUserNameLabel.setText("Username:");

        jUserPasswordLabel.setText("Password:");

        jIconLabel.setIcon(new javax.swing.ImageIcon("objects/optify.PNG")); // NOI18N
        jIconLabel.setText("jIconLabel");

        jLabel4.setText("Select your tests:");

        jMenu1.setText("File  ");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Reports");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Settings");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        
        jMenuItem5.setText("Failure Analyzer");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Manage   ");
        jMenu2.add(jMenuItem4);
        jMenu2.add(jMenuItem5);
        
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help   ");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("About   ");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jIconLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jUserNameLabel)
                            .addComponent(jUserPasswordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jUserNameField)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                    .addComponent(jButtonRunTest, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUserNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUserNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUserPasswordLabel)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jButtonRunTest)
                .addGap(23, 23, 23)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    @SuppressWarnings("deprecation")
	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    	endApp();
    	System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    	Settings set=new Settings();
        set.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    	FailureAnalyzer fa=new FailureAnalyzer();
        fa.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

	private void jButtonRunTestActionPerformed(java.awt.event.ActionEvent evt) throws Exception{//GEN-FIRST:event_jButtonRunTestActionPerformed
    	if(this.flag){
    		while(thread2.isAlive()){
    			jButtonRunTest.setText("Run Test");
	    		Runtime.getRuntime().exec("killall chromedriver");
	    		thread2.interrupt();
	    		this.flag=false;
    		}
    		
    		return;
    	}
    	
    	this.thread2 = new Thread(new MainTest(this.rootNode));
    	this.flag=true;
    	this.thread2.start();
    	jButtonRunTest.setText("Stop Test");
       
    }//GEN-LAST:event_jButtonRunTestActionPerformed

    private void jUserNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUserNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUserNameFieldActionPerformed

    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        //About about=new About();
        //about.setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
       
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        Settings set=new Settings();
        set.setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    	
    	System.out.println("Starting Optify Automation Suite Version2.0...\n");
    	
    	ManageTests listF=new ManageTests();
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRunTest;
    private javax.swing.JLabel jUserNameLabel;
    private javax.swing.JLabel jUserPasswordLabel;
    private javax.swing.JLabel jIconLabel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jUserNameField;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables
    
    //=========================================================================
    String getUserName(){
    	return jUserNameField.getText();
    }
    
    //=========================================================================
	String getUserPassword(){
		return jPasswordField.getText();
	}
	
	//=========================================================================
	public void endApp(){
			System.out.println("Exiting!!!");
			
			//Save JTree scripts privilege
			saveJTree();
			
			try {
				Runtime.getRuntime().exec("killall chromedriver");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//========================================================================
    private DefaultMutableTreeNode creatJTree(){
    	Vector<Vector<String>> groupVector = new Vector<Vector<String>>();
    	
   	 	@SuppressWarnings("unchecked")
		Vector<String> group[] = (Vector<String>[]) new Vector[this.listTests.testList.size()];
   	 	
   	 	for(int i=0;i<this.listTests.testList.size();i++){
	   		 group[i]=new Vector<String>();
	   		 group[i].add(this.listTests.returnFileName(this.listTests.testList.get(i).getFileName()));
	   		 
	   		 for(int j=0;j<this.listTests.testList.get(i).getScriptList().size();j++)
	   			 group[i].add(this.listTests.testList.get(i).getScriptList().get(j).toString());
	   		 
	   		 groupVector.add(group[i]);
   	 	}
   	 	
   	 	this.rootNode = new DefaultMutableTreeNode("Root");
  	
	    for(int i =0;i<groupVector.size();i++){
	     	DefaultMutableTreeNode node = new DefaultMutableTreeNode(new CheckBoxNode(((Vector<?>)groupVector.elementAt(i)).elementAt(0).toString(), getScriptsPrivilege(((Vector<?>)groupVector.elementAt(i)).elementAt(0).toString()+" page")));
	     	for(int j=1;j<((Vector<?>)groupVector.elementAt(i)).size();j++){
	     		node.add(new DefaultMutableTreeNode(new CheckBoxNode(((Vector<?>)groupVector.elementAt(i)).elementAt(j).toString(), getScriptsPrivilege(((Vector<?>)groupVector.elementAt(i)).elementAt(j).toString()))));		
	     	}
	     	rootNode.add(node);
	    }
	    
	    return rootNode;
    }
    
    //=========================================================================
    private boolean getScriptsPrivilege(String name){
    	//Load all file info into Contact List.
		BufferedReader reader=null;
		File file=new File("data/data3");
		String line="";
		
		final int PAGE_SIZE=4;
		String arr[];
		
		//Set with page test scenario his been testing for privilege.
		if(name.length()>=PAGE_SIZE && name.substring(name.length()-PAGE_SIZE, name.length()).equals("page"))
			this.pageName=name.substring(0,name.length()-PAGE_SIZE);
			
		try { @SuppressWarnings("resource")
			  FileReader fstreamRead=new FileReader(file);
		      reader=new BufferedReader(fstreamRead);
		      line = reader.readLine();
		  
		      while(line!=null){
		    	 arr=line.split(" ");
		    	 
		    	 if((this.pageName).equals(arr[0]+" ")&&arr.length==1)
		    		 return true;
		    	 
			     if(arr[0].equals(this.pageName.substring(0,this.pageName.length()-1))&&arr[1].equals(name))
				    return true;
			     
			     line = reader.readLine();
		      }
		  
		      reader.close();
	
		} catch (Exception e) {
		// TODO Auto-generated catch block
			System.out.println("Can't load scripts privilege from file data/data3!");
			e.printStackTrace();
		}
		
	 	return false;
    }
    
    //=========================================================================
    private void saveJTree(){
    	File file=new File("data/data3");     //Destination writing to file.
		FileWriter fstream;
		final int SIZE=this.rootNode.getChildCount(); //Get the number of test pages.
		DefaultMutableTreeNode value;             
		DefaultMutableTreeNode p=this.rootNode;
   	 	String pageName="";
		
		value = ((DefaultMutableTreeNode) p.getFirstChild());
		
		try { fstream = new FileWriter(file,false);
	      	   BufferedWriter out = new BufferedWriter(fstream);
	      	 
	      	   for(int i=0;i<SIZE;i++){ 
	      		    //Get the test scripts nodes of the test page.
	      		   	DefaultMutableTreeNode child=(DefaultMutableTreeNode)value.getFirstChild(); 
	        	
	      		   	Object pageObject = ((DefaultMutableTreeNode) value)
		      				.getUserObject(); 
	      		  
		      		if (pageObject instanceof CheckBoxNode) {
	       		        CheckBoxNode node = (CheckBoxNode) pageObject;
				             //Write to file
	       		        
	       		        pageName=node.text.toString();
	       		        
	       		        if(node.selected)
	       		        	out.write(node.text.toString()+"\n");
		            }
		      		
	        		final int PAGE_SIZE=value.getChildCount();
	        		  
	        		for(int j=0;j<PAGE_SIZE;j++){
			      		Object scriptObject = ((DefaultMutableTreeNode) child)
			      				.getUserObject(); 
		        		if (scriptObject instanceof CheckBoxNode) {
		        		       CheckBoxNode node = (CheckBoxNode) scriptObject;
					             //Write to file
		        		         if(node.selected)
		        		        	out.write(pageName+" "+node.text.toString()+"\n");
			             }
		        		 
		        		 child=(DefaultMutableTreeNode)child.getNextLeaf();
	        		}
	        		 
	        		value = ((DefaultMutableTreeNode)value.getNextSibling());
	      	   }
	      		
		     out.close();
		 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    	
    }
}

//=============================================================================
class CheckBoxNodeRenderer implements TreeCellRenderer {
    private JCheckBox leafRenderer = new JCheckBox();
    
    private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();
    
    Color selectionBorderColor, selectionForeground, selectionBackground,
            textForeground, textBackground;
    
    protected JCheckBox getLeafRenderer() {
        return leafRenderer;
    }
    
    public CheckBoxNodeRenderer() {
        Font fontValue;
        fontValue = UIManager.getFont("Tree.font");
        if (fontValue != null) {
            leafRenderer.setFont(fontValue);
        }
        Boolean booleanValue = (Boolean) UIManager
                .get("Tree.drawsFocusBorderAroundIcon");
        leafRenderer.setFocusPainted((booleanValue != null)
        && (booleanValue.booleanValue()));
        
        selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");
        textForeground = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
    }
    
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        
        Component returnValue;
        
        String stringValue = tree.convertValueToText(value, selected,
                expanded, leaf, row, false);
        leafRenderer.setText(stringValue);
        leafRenderer.setSelected(false);
        
        leafRenderer.setEnabled(tree.isEnabled());
        
        if (selected) {
            leafRenderer.setForeground(selectionForeground);
            leafRenderer.setBackground(selectionBackground);
        } else {
            leafRenderer.setForeground(textForeground);
            leafRenderer.setBackground(textBackground);
        }
        
        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
            Object userObject = ((DefaultMutableTreeNode) value)
            .getUserObject();
            if (userObject instanceof CheckBoxNode) {
                CheckBoxNode node = (CheckBoxNode) userObject;
                leafRenderer.setText(node.getText());
                leafRenderer.setSelected(node.isSelected());
            }
        }
        returnValue = leafRenderer;
        return returnValue;
    }
}
 
class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {
    
    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
    
    ChangeEvent changeEvent = null;
    
    JTree tree1;
    
    DefaultMutableTreeNode editedNode;
    
    public CheckBoxNodeEditor(JTree tree) {
        this.tree1 = tree;
    }
    
    public Object getCellEditorValue() {
        JCheckBox checkbox = renderer.getLeafRenderer();
        CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),
                checkbox.isSelected());
        return checkBoxNode;
    }
    
    public boolean isCellEditable(EventObject event) {
        boolean returnValue = false;
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            TreePath path = tree1.getPathForLocation(mouseEvent.getX(),
                    mouseEvent.getY());
            if (path != null) {
                Object node = path.getLastPathComponent();
                if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
                    editedNode = (DefaultMutableTreeNode) node;
                    Object userObject = editedNode.getUserObject();
                    Rectangle r = tree1.getPathBounds(path);
                    int x = mouseEvent.getX() - r.x;
                    int y = mouseEvent.getY() - r.y;
                    JCheckBox checkbox = renderer.getLeafRenderer();
                    checkbox.setText("");
                    returnValue = userObject instanceof CheckBoxNode && x > 0 && x < checkbox.getPreferredSize().width;
                }
            }
        }
        return returnValue;
    }
    
    public Component getTreeCellEditorComponent(final JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row) {
        
        Component editor = renderer.getTreeCellRendererComponent(tree, value,
                true, expanded, leaf, row, true);
        
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                    tree.repaint();
                    fireEditingStopped();
            }
        };
        
        if (editor instanceof JCheckBox) {
            ((JCheckBox) editor).addItemListener(itemListener);
        }
        
        return editor;
    }
}
 
class CheckBoxNode {
    String text;
    
    boolean selected;
    
    public CheckBoxNode(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean newValue) {
        selected = newValue;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String newValue) {
        text = newValue;
    }
    
    public String toString() {
        return getClass().getName() + "[" + text + "/" + selected + "]";
    }
}