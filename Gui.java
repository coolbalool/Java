
import javax.swing.*;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;

import java.awt.*;

public class Gui implements ActionListener{

        private JFrame frame;
        private JPanel panel;
        private JButton button;
        private JLabel userLabel;
        private JLabel passLabel;
        private JLabel loginLabel;
        private JTextField userField;
        private JPasswordField passField;
        private String password,username;
        private CardLayout layout;

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
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        panel.setVisible(true);
        loginPanel();

        panel.updateUI();
        
    }

    private void loginPanel()
    {

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

      loginLabel = new JLabel("");
      loginLabel.setBounds(80,130,200,20);
      panel.add(loginLabel);

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
                loginLabel.setText("Login successfully");
                else loginLabel.setText("Username or password incorrect");
                else loginLabel.setText("Username or password incorrect");
              }
    }
    
}

