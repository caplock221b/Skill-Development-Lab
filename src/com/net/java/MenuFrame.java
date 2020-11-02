package com.net.java;

import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;

class MenuFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l;
    JButton reg, login, exit;
    
    public MenuFrame(){
        
        l = new JLabel("<html><span style='font-size: 14px'><u>Welcome to List-It App!</u></span></html>");
        reg = new JButton("Register");
        login = new JButton("Login");
        exit = new JButton("Exit");
        
        setDimensions();
        addComponents();
        
        reg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					Client.sendChoice(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	new RegisterFrame();
                dispose();
            }
        });
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                new LoginFrame();
                dispose();
            }
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	dispose();
            }
        });
        
        setLayout(null);
        setVisible(true);
        setBounds(550, 250, 400, 300);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setDimensions(){
        l.setBounds(98, 20, 204, 50);
        reg.setBounds(150, 100, 100, 30);
        login.setBounds(150, 150, 100, 30);
        exit.setBounds(150, 200, 100, 30);
    }
    
    private void addComponents(){
        add(l);
        add(reg);
        add(login);
        add(exit);
    }
}