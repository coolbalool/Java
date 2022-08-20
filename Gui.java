
import javax.swing.*;
import java.awt.event.ActionListener;

import java.awt.*;

public class Gui implements ActionListener{

        private boolean isLoginMode = true,ranLogin = false;

        private JFrame frame;
        private JPanel panel,loginPanel;
        private JButton loginButton,registerButton;
        private JTextField userField;
        private JPasswordField passField;
        private JLabel loginLabel,loginModeLabel;
        private String password,username;
        private CardLayout cl;

    public Gui()
    {
        username = "dag";
        password = "123daf";

        frame = new JFrame("GUI");
        panel = new JPanel(cl);
        
        
        cl = new CardLayout();

        frame.setSize(500,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(cl);

      
      loginPanel(true);

        panel.add(loginPanel,"login");
        

        
        cl.show(panel,"login");
        panel.updateUI();
    }

    private void loginPanel(boolean changeTextMode)
    {
        
         JLabel userLabel;
         JLabel passLabel;
         
         loginPanel = new JPanel(null);
         loginPanel.setBackground(Color.gray);

        if (changeTextMode && ranLogin) 
        {
          if (isLoginMode) 
          loginModeLabel.setText("Type Username and Password to log in. Then click on login button");
          else 
          loginModeLabel.setText("Type Username and Password to register. then click on register button");
          return;
        }

        else if (!ranLogin && changeTextMode)
        {
          loginPanel(false);
          loginPanel(true);
        }

      userLabel = new JLabel("Username:");
      userLabel.setBounds(10,20,70,50);
      userLabel.setForeground(Color.white);
      loginPanel.add(userLabel);

      userField = new JTextField(20);
      userField.setBounds(80,35,170,20);
      loginPanel.add(userField);

      passLabel = new JLabel("Password:");
      passLabel.setBounds(10,50,70,50);
      passLabel.setForeground(Color.white);
      loginPanel.add(passLabel);

      passField = new JPasswordField(20);
      passField.setBounds(80,65,170,20);
      loginPanel.add(passField);

      loginLabel = new JLabel("");
      loginLabel.setBounds(80,130,200,20);
      loginLabel.setForeground(Color.white);
      loginPanel.add(loginLabel);

      loginButton = new JButton("Login");
      loginButton.setBounds(80,100,70,30);
      loginButton.addActionListener(this);
      loginPanel.add(loginButton);
      

      registerButton = new JButton("Register");
      registerButton.setBounds(150,100,100,30);
      registerButton.addActionListener(this);
      loginPanel.add(registerButton);

      loginModeLabel = new JLabel("");
      loginModeLabel.setBounds(80,150,200,20);
      loginModeLabel.setForeground(Color.white);
      loginPanel.add(loginModeLabel);

      loginPanel.updateUI();
      ranLogin = true;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) 
    {
            if (e.getSource() == loginButton)
              {
                loginPanel(true);
                if (userField.getText().equals(username))
                if (passField.getText().equals(password))
                loginLabel.setText("Login successfully");
                else loginLabel.setText("Username or password incorrect");
                else loginLabel.setText("Username or password incorrect");
                
              }

            if (e.getSource() == registerButton)
            {
              loginPanel(true);
              if (isLoginMode) isLoginMode = false;
              else 
              {
                username = userField.getText();
                password = passField.getText();
                isLoginMode = true;
              }
            }
    }
    
}

