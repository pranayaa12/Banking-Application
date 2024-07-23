package banking.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {
    JLabel l1;
    JButton b1, b2, b4, b5, b6, b7;
    String pin;
    String Accountno;

    Transactions(String pin, String Accountno) {
        this.Accountno = Accountno;
        this.pin = pin;

        setTitle("Transactions ");
        setLayout(null);

        // Setting background color to sky blue
        getContentPane().setBackground(new Color(135, 206, 250)); // Sky Blue color

        // Logo image
        ImageIcon i1 = new ImageIcon("C:\\Users\\palla\\Downloads\\bankpic2.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        // Welcome text
        l1 = new JLabel("WELCOME TO AXIS BANK ");
        l1.setFont(new Font("timesnewroman", Font.BOLD, 30));
        l1.setBounds(200, 40, 450, 40);
        l1.setForeground(Color.white);
        add(l1);

        // Buttons
        b1 = new JButton("DEPOSIT");
        b1.setBounds(150, 180, 180, 45);
        customizeButton(b1);
        add(b1);

        b2 = new JButton("WITHDRAWL");
        b2.setBounds(400, 180, 180, 45);
        customizeButton(b2);
        add(b2);

        b4 = new JButton("MINI STATEMENT");
        b4.setBounds(150, 250, 180, 45);
        customizeButton(b4);
        add(b4);

        b5 = new JButton("CHANGE PASSWORD");
        b5.setBounds(400, 250, 180, 45);
        customizeButton(b5);
        add(b5);

        b6 = new JButton("BALANCE ENQUIRY");
        b6.setBounds(150, 320, 180, 45);
        customizeButton(b6);
        add(b6);

        b7 = new JButton("SIGN-OUT");
        b7.setBounds(400, 320, 180, 45);
        customizeButton(b7);
        add(b7);

        // Action listeners for buttons
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        // Setting frame properties
        setSize(800, 450); // Adjust size as per your requirement
        setLocationRelativeTo(null); // Center align the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Prevent resizing
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setBackground(Color.white);
        button.setForeground(Color.black);
        button.setFont(new Font("Arial", Font.BOLD, 13));
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            setVisible(false);
            new Deposit(this.pin, this.Accountno).setVisible(true);
        } else if (ae.getSource() == b2) {
            setVisible(false);
            new Withdrawl(this.pin, this.Accountno).setVisible(true);
        } else if (ae.getSource() == b4) {
            new MiniStatement(this.pin, this.Accountno).setVisible(true);
        } else if (ae.getSource() == b5) {
            setVisible(false);
            new Pin(this.pin, this.Accountno).setVisible(true);
        } else if (ae.getSource() == b6) {
            setVisible(false);
            new BalanceEnquiry(this.pin, this.Accountno).setVisible(true);
        } else if (ae.getSource() == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("", "");
    }
}
