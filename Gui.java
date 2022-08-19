
import javax.swing.*;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;

import java.awt.*;

public class Gui implements ActionListener{

        private JFrame frame;
        private JPanel panel;
        private JLabel userLabel;
        private JLabel passLabel;
        private JTextField userField;
        private JPasswordField passField;
        private JButton button;
        private String password,username;

    public Gui()
    {
        username = "dag";
        password = "123daf";

        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(500,300);
        frame.setVisible(true);
        frame.setTitle("Gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        panel.setLayout(null);
        panel.setVisible(true);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10,20,70,50);
        panel.add(userLabel);

        userField = new JTextField(20);
        userField.setBounds(80,35,120,20);
        panel.add(userField);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(10,50,70,50);
        panel.add(passLabel);

        passField = new JPasswordField(20);
        passField.setBounds(80,65,120,20);
        panel.add(passField);

        button = new JButton("Login");
        button.setBounds(80,100,70,30);
        button.addActionListener(this);
        panel.add(button);

        panel.updateUI();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) 
    {
            if (e.getSource() == button)
              {
                if (userField.getText().equals(username))
                if (passField.getText().equals(password))
                System.out.println("asd");
              }
    }
    
}

