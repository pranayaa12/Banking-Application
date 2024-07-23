package banking.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

	public class Pin extends JFrame implements ActionListener {
	    
		JPasswordField t1, t2;
	    JButton b1, b2;                               
	    JLabel l1, l2, l3;
	    String pin;
	    String Accountno;
	    
	    Pin(String pin, String Accountno) {
	        this.pin = pin;
	        this.Accountno = Accountno;
	        
	        setLayout(null);

	        // Load and set images
	        loadAndSetImage("C:\\Users\\palla\\Downloads\\bankpic2.jpg", 90, 50, 100, 100);
	        loadAndSetImage("C:\\Users\\palla\\Downloads\\paymentProc.jpg", 600, 0, 1000, 900);

	        JLabel text = new JLabel("Dear Customer.");
	        text.setForeground(Color.black);
	        text.setFont(new Font("Osward", Font.BOLD, 32));
	        text.setBounds(200, 80, 450, 40);
	        add(text);
	        
	        l1 = new JLabel("CHANGE YOUR PIN");
	        l1.setFont(new Font("System", Font.BOLD, 25));
	        l1.setForeground(Color.black);
	        l1.setBounds(350, 320, 800, 35);
	        add(l1);
	        
	        l2 = new JLabel("New PIN:");
	        l2.setFont(new Font("System", Font.BOLD, 18));
	        l2.setForeground(Color.black);
	        l2.setBounds(220, 390, 150, 35);
	        add(l2);
	        
	        l3 = new JLabel("Re-Enter New PIN:");
	        l3.setFont(new Font("System", Font.BOLD, 18));
	        l3.setForeground(Color.black);
	        l3.setBounds(200, 440, 200, 35);
	        add(l3);
	        
	        t1 = new JPasswordField();
	        t1.setFont(new Font("Raleway", Font.BOLD, 25));
	        t1.setBounds(370, 390, 200, 30);
	        add(t1);
	        
	        t2 = new JPasswordField();
	        t2.setFont(new Font("Raleway", Font.BOLD, 25));
	        t2.setBounds(370, 440, 200, 30);
	        add(t2);
	        
	        b1 = new JButton("CHANGE");
	        b1.setBounds(390, 500, 150, 30);
	        b1.setBackground(Color.BLACK);
	        b1.setForeground(Color.white);
	        b1.addActionListener(this);
	        add(b1);
	        
	        b2 = new JButton("BACK");
	        b2.setBounds(390, 553, 150, 30);
	        b2.setBackground(Color.BLACK);
	        b2.setForeground(Color.white);
	        b2.addActionListener(this);
	        add(b2);
	        
	        setSize(1600, 1200);
	        getContentPane().setBackground(Color.white);
	        setVisible(true);
	    }

	    private void loadAndSetImage(String path, int x, int y, int width, int height) {
	        try {
	            ImageIcon icon = new ImageIcon(path);
	            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
	            ImageIcon scaledIcon = new ImageIcon(img);
	            JLabel label = new JLabel(scaledIcon);
	            label.setBounds(x, y, width, height);
	            add(label);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Error loading image: " + path);
	        }
	    }
	    
	    public void actionPerformed(ActionEvent ae) {
	        SwingUtilities.invokeLater(() -> {
	            try {
	                String npin = new String(t1.getPassword());
	                String rpin = new String(t2.getPassword());
	                
	                if (!npin.equals(rpin)) {
	                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
	                    return;
	                }
	                
	                if (ae.getSource() == b1) {
	                    if (npin.equals("") || rpin.equals("")) {
	                        JOptionPane.showMessageDialog(null, "PIN fields cannot be empty");
	                        return;
	                    }
	                    
	                    ConnectionSql c1 = new ConnectionSql();
	                    String q1 = "update bank set Login_Password = '" + rpin + "' where Account_No = '" + Accountno + "' and Login_Password = '" + pin + "'";
	                    String q2 = "update login set Login_Password = '" + rpin + "' where Account_No = '" + Accountno + "' and Login_Password = '" + pin + "'";
	                    String q3 = "update signup3 set Login_Password = '" + rpin + "' where Account_No = '" + Accountno + "' and Login_Password = '" + pin + "'";
	    
	                    c1.s.executeUpdate(q1);
	                    c1.s.executeUpdate(q2);
	                    c1.s.executeUpdate(q3);
	    
	                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
	                    
	                    setVisible(false);
	                    new Transactions(rpin, Accountno).setVisible(true);
	                
	                } else if (ae.getSource() == b2) {
	                    setVisible(false);
	                    new Transactions(pin, Accountno).setVisible(true);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new Pin("", ""));
	    }
	}
