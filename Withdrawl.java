package banking.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.File;

public class Withdrawl extends JFrame implements ActionListener {

    JLabel l1, l3;
    JTextField t1;
    JButton b1, b2, b3;
    String pin;
    String Accountno;

    Withdrawl(String pin, String Accountno) {
        this.pin = pin;
        this.Accountno = Accountno;
        setLayout(null);

        // Load images using absolute file paths
        ImageIcon h1 = new ImageIcon(new File("C:\\Users\\palla\\Downloads\\logo.jpg").getAbsolutePath());
        Image h2 = h1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon h3 = new ImageIcon(h2);
        JLabel label = new JLabel(h3);
        label.setBounds(70, 30, 100, 100);
        add(label);

        ImageIcon j1 = new ImageIcon(new File("C:\\Users\\palla\\Downloads\\withdraw2.jpg").getAbsolutePath());
        Image j2 = j1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon j3 = new ImageIcon(j2);
        JLabel label6 = new JLabel(j3);
        label6.setBounds(800, 0, 800, 800);
        add(label6);

        JLabel text = new JLabel("Dear Customer");
        text.setForeground(Color.black);
        text.setFont(new Font("Osward", Font.BOLD, 32));
        text.setBounds(200, 80, 450, 40);
        add(text);

        l1 = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW!!");
        l1.setForeground(Color.black);
        l1.setFont(new Font("System", 1, 20));
        l1.setBounds(220, 220, 700, 55);
        add(l1);

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", 1, 20));
        t1.setBounds(220, 280, 500, 35);
        t1.setBackground(Color.white);
        add(t1);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(570, 350, 150, 40);
        b1.setFont(new Font("Raleway", 1, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(570, 408, 150, 40);
        b2.setFont(new Font("Raleway", 1, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("EXIT");
        b3.setBounds(570, 458, 150, 40);
        b3.setFont(new Font("Raleway", 1, 15));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        add(b3);

        setSize(1600, 1200);
        getContentPane().setBackground(new Color(204, 204, 255));
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = t1.getText();
            java.util.Date date = new java.util.Date(); // Specify java.util.Date explicitly
            if (ae.getSource() == b1) {
                if (t1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
                } else {
                    ConnectionSql c = new ConnectionSql(); // Ensure ConnectionSql is implemented correctly

                    ResultSet rs = c.s.executeQuery("select * from bank where Account_No = '" + Accountno + "' and Login_Password = '" + pin + "'");
                    int balance = 0;
                    while (rs.next()) {
                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }

                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }

                    c.s.executeUpdate("insert into bank values('" + pin + "', '" + Accountno + "', '" + new java.sql.Date(date.getTime()) + "', 'Withdrawl', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

                    setVisible(false);
                    new Transactions(pin, Accountno).setVisible(true);
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin, Accountno).setVisible(true);
            } else if (ae.getSource() == b3) {
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Withdrawl("", "");
    }
}
