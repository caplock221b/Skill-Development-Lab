package com.net.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class AddTasksFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l;
    JTextField t;
    JButton b;
    
    public AddTasksFrame(){
        
        l = new JLabel("<html><span style='font-size: 12px'>Enter task: </span></html>");
        t = new JTextField(10);
        b = new JButton("<html><span style='font-size: 11px'>Add</span></html>");
        
        setDimensions();
        addComponents();
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = t.getText().toString();
                if(task.length()==0){
                    showDialogBox("Task is required", "", 0);
                }
                else{
                	try {
						Client.sendString(task);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    addAnotherTaskDialogBox();
                }
            }
        });
        
        setLayout(null);
        setVisible(true);
        setBounds(550, 250, 400, 300);
        setTitle("Add Task");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setDimensions(){
        l.setBounds(40, 40, 300, 40);
        t.setBounds(40, 100, 250, 20);
        b.setBounds(125, 160, 150, 30);
    }
    
    private void addComponents(){
        add(l);
        add(t);
        add(b);
    }
    
    private void showDialogBox(String s1, String s2, int c){
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
                    new MainPageFrame();
                }
            });
        }
        
        d.setLayout(null);
        d.setBounds(600, 300, 300, 200);
        d.setTitle("Dialog Box");
        d.setVisible(true); 
    }
    
    private void addAnotherTaskDialogBox(){
        JDialog d = new JDialog();
        JLabel l1 = new JLabel("<html><span style='font-size: 11px'>Add another task?</span></html>");
        JButton b1 = new JButton("Yes");
        JButton b2 = new JButton("No");
        
        l1.setBounds(40, 20, 200, 30);
        b1.setBounds(60, 70, 80, 30);
        b2.setBounds(150, 70, 80, 30);
        
        d.add(l1); 
        d.add(b1);
        d.add(b2);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	d.dispose();
                t.setText("");
            }
        });
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Client.sendChoice(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                d.dispose();
                dispose();
                new MainPageFrame();
            }
        });
        
        d.setLayout(null);
        d.setBounds(600, 300, 300, 200);
        d.setTitle("Dialog Box");
        d.setVisible(true); 
    }
}