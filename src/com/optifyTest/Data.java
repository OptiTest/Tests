package com.optifyTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import com.google.common.io.Files;

public class Data {
	
	private String userName;
	private String password;
	private String url;
	private File file;
	
	//Data constructor:
	public Data(){
	    this.file=new File("data\\data.txt");
	    this.userName="";
	    this.password="";
	    this.url="";
	}
	
	//=========================================================================
	public void saveUserInformation(){
		String info=(this.userName+"\r\n"+this.password+"\r\n"+this.url);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedWriter writer = Files.newWriter(this.file, charset)) {
		    writer.write(info, 0, info.length());
		} catch (IOException x) {
			JOptionPane.showMessageDialog(null,"Failed to save your information:\n"+x.toString());
	        System.err.format("IOException: %s%n", x);
	        return;
	    }
		
		JOptionPane.showMessageDialog(null,"Information has been saved");
	}
	
	//======================================================================================
	public String[] loadUserInformation(){
        final int SIZE=3;
		String []info=new String[SIZE];
		int i=0;
        
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newReader(this.file, charset)){
		     String line = null;
	         while ((line = reader.readLine())!=null){
	            info[i]=line.toString();i++; 
	         }
		}
		          
	    catch (IOException x) {
			JOptionPane.showMessageDialog(null,"Failed to save your information:\n"+x.toString());
	        System.err.format("IOException: %s%n", x);
	        return null;
	    }
		
		return info;
	}
	
	//=========================================================================
	public void setUser(String name){
		this.userName=name;
	}
	
	//=========================================================================
	public void setPassword(String pass){
		this.password=pass;
	}
	
	//=========================================================================
	public void setServerUrl(String url){
		this.url=url;
	}
}
