package com.net.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MainPageFrame extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_h1, l_h2;
    JButton b_add, b_remove, b_show, b_exit;
    
    public MainPageFrame(){
        
        l_h1 = new JLabel("<html><span style='font-size: 15px'>Welcome!</span></html>");
        l_h2 = new JLabel("<html><span style='font-size: 13px'>What are you up to today?</span></html>");
        
        b_add = new JButton("<html><span style='font-size: 11px'>Add tasklist</span></html>");
        b_remove = new JButton("<html><span style='font-size: 11px'>Remove tasklist</span></html>");
        b_show = new JButton("<html><span style='font-size: 11px'>Show tasklists</span></html>");
        b_exit = new JButton("<html><span style='font-size: 11px'>Exit</span></html>");
        
        setDimensions();
        addComponents();
        
        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
                new AddTasklistFrame();
            }
        });
        
        b_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
                new RemoveTasklistFrame();
            }
        });
        
        b_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        b_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(4);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
                new MenuFrame();
            }
        });
        
        setLayout(null);
        setBounds(550, 200, 400, 400);
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void setDimensions(){
        l_h1.setBounds(50, 30, 400, 40);
        l_h2.setBounds(50, 80, 400, 30);
        b_add.setBounds(100, 140, 200, 30);
        b_remove.setBounds(100, 180, 200, 30);
        b_show.setBounds(100, 220, 200, 30);
        b_exit.setBounds(100, 260, 200, 30);
    }
    
    private void addComponents(){
        add(l_h1);
        add(l_h2);
        add(b_add);
        add(b_remove);
        add(b_show);
        add(b_exit);
    }
}