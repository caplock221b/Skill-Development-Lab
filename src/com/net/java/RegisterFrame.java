package com.net.java;

import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;

class RegisterFrame extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_heading, l_username, l_email, l_pass, l_cpass;
    JTextField t_username, t_email, t_pass, t_cpass;
    JButton reg;
    
    public RegisterFrame(){
        
        l_heading = new JLabel("<html><span style='font-size: 14px'><u>Register a new user</u></span></html>");
        l_username = new JLabel("<html><span style='font-size: 12px'>Username : </span></html>");
        l_email = new JLabel("<html><span style='font-size: 12px'>Email : </span></html>");
        l_pass = new JLabel("<html><span style='font-size: 12px'>Password : </span></html>");
        l_cpass = new JLabel("<html><span style='font-size: 12px'>Confirm Password : </span></html>");
        
        t_username = new JTextField(10);
        t_email = new JTextField(10);
        t_pass = new JTextField(10);
        t_cpass = new JTextField(10);
        
        reg = new JButton("<html><span style='font-size: 12px'>Register</span></html>");
        
        setDimensions();
        addComponents();
        
        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = t_username.getText().toString();
                String email = t_email.getText().toString();
                String pass = t_pass.getText().toString();
                String c_pass = t_cpass.getText().toString();
                if(username.length()==0){
                    System.out.println("Invalid credentials. Please enter username.");
                    showDialogBox("Invalid Credentials!", "Please enter username.", 0);
                }
                else if(email.length()==0){
                    System.out.println("Invalid credentials. Please enter email.");
                    showDialogBox("Invalid Credentials!", "Please enter email.", 0);
                }
                else if(pass.length()==0){
                    System.out.println("Invalid credentials. Please enter password.");
                    showDialogBox("Invalid Credentials!", "Please enter password.", 0);
                }
                else if(pass.compareTo(c_pass) != 0){
                    System.out.println("Invalid credentials. Passwords don't match.");
                    showDialogBox("Invalid Credentials!", "Passwords don't match.", 0);
                }
                else{
                	User user = new User(username, email, pass);
                	try {
						Client.sendUser(user);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    dispose();
                }
            }
        });
                
        setLayout(null);
        setBounds(500, 200, 500, 400);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void setDimensions(){
        l_heading.setBounds(161, 10, 178, 40);
        l_username.setBounds(50, 70, 100, 20);
        t_username.setBounds(250, 72, 200, 20);
        l_email.setBounds(50, 120, 100, 20);
        t_email.setBounds(250, 122, 200, 20);
        l_pass.setBounds(50, 170, 100, 20);
        t_pass.setBounds(250, 172, 200, 20);
        l_cpass.setBounds(50, 220, 250, 20);
        t_cpass.setBounds(250, 222, 200, 20);
        reg.setBounds(175, 270, 150, 30);
    }
    
    private void addComponents(){
        add(l_heading);
        add(l_username);
        add(t_username);
        add(l_email);
        add(t_email);
        add(l_pass);
        add(t_pass);
        add(l_cpass);
        add(t_cpass);
        add(reg);
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
        else{
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                    new MenuFrame();
                }
            });
        }
        
        d.setLayout(null);
        d.setBounds(600, 300, 300, 200);
        d.setTitle("Dialog Box");
        d.setVisible(true); 
    }
}