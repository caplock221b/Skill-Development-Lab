package com.net.java;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

class LoginFrame extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_heading, l_username, l_pass;
    JTextField t_username, t_pass;
    JButton login;
    
    public LoginFrame(){
        
        l_heading = new JLabel("<html><span style='font-size: 14px'><u>Login to start</u></span></html>");
        l_username = new JLabel("<html><span style='font-size: 12px'>Username : </span></html>");
        l_pass = new JLabel("<html><span style='font-size: 12px'>Password : </span></html>");
        
        t_username = new JTextField(10);
        t_pass = new JTextField(10);
        
        login = new JButton("<html><span style='font-size: 12px'>Login</span></html>");
        
        setDimensions();
        addComponents();
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = t_username.getText();
                String pass = t_pass.getText();
                if(username.length()==0){
                    showDialogBox("Invalid credentials!", "Username is missing.", 0);
                }
                else if(pass.length()==0){
                    showDialogBox("Invalid credentials!", "Password is missing.", 0);
                }
                else{
                	User user = new User(username, "", pass);
                	try {
						Client.sendUser(user);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//                    showDialogBox("Successful login.", "", 1);
                    dispose();
                }
            }
        });
        
        setLayout(null);
        setVisible(true);
        setBounds(550, 250, 400, 300);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setDimensions(){
        l_heading.setBounds(140, 10, 120, 40);
        l_username.setBounds(50, 70, 100, 20);
        t_username.setBounds(160, 72, 190, 20);
        l_pass.setBounds(50, 120, 100, 20);
        t_pass.setBounds(160, 122, 190, 20);
        login.setBounds(140, 170, 120, 30);
    }
    
    private void addComponents(){
        add(l_heading);
        add(l_username);
        add(t_username);
        add(l_pass);
        add(t_pass);
        add(login);
    }
    
    public static void showDialogBox(String s1, String s2, int c){
        JDialog d = new JDialog();
        JLabel l1 = new JLabel("<html><span style='font-size: 11px'>" + s1 + "</span></html>");
        JLabel l2 = new JLabel("<html><span style='font-size: 11px'>" + s2 +"</span></html>");
        JButton b = new JButton("OK");
        
        l1.setBounds(40, 20, 200, 30);
        l2.setBounds(40, 60, 200, 30);
        b.setBounds(110, 100, 80, 30);
        
        d.add(l1); 
        d.add(l2);
        d.add(b);
        
        if(c==0){
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                }
            });
        }
        else if(c==1){
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                    new MenuFrame();
                }
            });
        }
        else {
        	b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                    new MainPageFrame();
                }
            });
        }
        
        d.setLayout(null);
        d.setBounds(600, 300, 300, 200);
        d.setTitle("Dialog Box");
        d.setVisible(true); 
    }
}
