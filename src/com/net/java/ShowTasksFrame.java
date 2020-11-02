package com.net.java;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ShowTasksFrame extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l;
    JTextField t;
    JTextArea a;
    
    public ShowTasksFrame(int num, String title, String tasks){
        
        l = new JLabel("<html><span style='font-size: 12px'>Title :</span></html>");
        t = new JTextField(10);
        a = new JTextArea(15, 15);
        
        setFont();
        setDimensions();
        addComponents();
        
        t.setText(title);
        a.setText(tasks);
        
        int x = (num-1) * 450;
        int y = ((num-1) % 2) * 400;
        
        setLayout(null);
        setVisible(true);
        setBounds(x, y, 400, 350);
        setTitle("Task " + num);
    }
    
    private void setFont() {
    	Font f = t.getFont();
        Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+5);
        t.setFont(f2);
        a.setFont(f2);
    }
    
    private void setDimensions() {
    	l.setBounds(20, 20, 100, 30);
        t.setBounds(120, 18, 150, 30);
        a.setBounds(20, 60, 340, 220);
        t.setEditable(false);
        a.setEditable(false);
    }
    
    private void addComponents() {
    	add(l);
        add(t);
        add(a);
    }
    
    public static void showDialogBox(String s1, String s2){
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
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				d.dispose();
				new MainPageFrame();
			}
		});
        
        d.setLayout(null);
        d.setBounds(600, 300, 300, 200);
        d.setTitle("Dialog Box");
        d.setVisible(true); 
    }
}