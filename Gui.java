
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class Gui implements ActionListener {

  // login + register page
  private boolean isLoginMode = true, ranLogin = false;
  private JPanel loginPanel;
  private JButton loginButton, registerButton;
  private JTextField userField;
  private JPasswordField passField;
  private JLabel loginLabel, loginModeLabel;
  private String password, username;
  // ticTacToe
  private JPanel tPanel;
  private JButton tExit;
  private JButton[][] board;
  private boolean tIsCross;
  private int tMoves;
  private JLabel tPlayerTxt;
  private JLabel tWinnerText;
  private String tWinner;
  private JButton tReset;

  private JFrame frame;
  private JPanel panel;
  private CardLayout cl;

  public Gui() {
    username = "dag";
    password = "123daf";

    frame = new JFrame("GUI");
    panel = new JPanel(cl);

    cl = new CardLayout();

    frame.setSize(500, 300);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.add(panel);
    panel.setLayout(cl);

    loginPanel(true);
    ticTacToe(null);

    panel.add(loginPanel, "login");
    panel.add(tPanel, "ttt");
    cl.show(panel, "ttt");
  }

  public void ticTacToe(String operation) {

    if (operation == null) {
      tPanel = new JPanel(null);
      tPanel.setBackground(Color.gray);

      tExit = new JButton("Exit");
      tExit.setBounds(400, 100, 75, 50);
      tExit.addActionListener(this);
      tPanel.add(tExit);

      tReset = new JButton("Reset");
      tReset.setBounds(400, 150, 75, 50);
      tReset.addActionListener(this);
      tReset.setVisible(false);
      tPanel.add(tReset);

      tPlayerTxt = new JLabel("X:");
      tPlayerTxt.setForeground(Color.white);
      tPlayerTxt.setBounds(400, 50, 70, 70);
      tPanel.add(tPlayerTxt);

      tWinnerText = new JLabel("");
      tWinnerText.setForeground(Color.white);
      tWinnerText.setBounds(310, 130, 130, 70);
      tPanel.add(tWinnerText);

      tIsCross = true;
      tMoves = 0;
      tWinner = "";
      board = new JButton[3][3];
      for (int i = 0; i < board.length; i++)
        for (int j = 0; j < board.length; j++) {
          board[i][j] = new JButton("");
          board[i][j].setBounds(j * 100, i * 100, 60, 60);
          board[i][j].addActionListener(this);
          tPanel.add(board[i][j]);
        }
    }

    if ("check".equals(operation)) {
      // tie checker
      if (tMoves >= 9) {
        tWinner = "tie";
        ticTacToe("go");
        return;
      }

      // checks if the move count is too small for a win
      if (tMoves < 4)
        return;

      String temp = "";
      for (int i = 0; i < board.length; i++) {
        // row
        if ("".equals(board[i][0].getText()))
          continue;
        temp = board[i][0].getText();
        for (int j = 0; j < board.length; j++) {
          if (!temp.equals(board[i][j].getText()))
            break;
          if (j == board.length - 1) {
            tWinner = temp;
            ticTacToe("go");
            return;
          }
        }
      }

      for (int i = 0; i < board.length; i++) {
        // col
        if ("".equals(board[0][i].getText()))
          continue;
        temp = board[0][i].getText();
        for (int j = 0; j < board.length; j++) {
          if (!temp.equals(board[j][i].getText()))
            break;
          if (j == board.length - 1) {
            tWinner = temp;
            ticTacToe("go");
            return;
          }
        }
      }

      // (0,0) mid
      if ("".equals(board[0][0].getText()))
        return;
      temp = board[0][0].getText();
      for (int i = 0; i < board.length; i++) {
        if (!temp.equals(board[i][i].getText()))
          break;
        if (i == board.length - 1) {
          tWinner = temp;
          ticTacToe("go");
          return;
        }
      }

      // other mid
      if ("".equals(board[0][board.length - 1].getText()))
        return;
      temp = board[0][board.length - 1].getText();
      for (int i = 0; i < board.length; i++) {
        if (!temp.equals(board[i][board.length - i - 1].getText()))
          break;
        if (i == board.length - 1) {
          tWinner = temp;
          ticTacToe("go");
        }
      }

    }

    if ("go".equals(operation)) {
      if ("tie".equals(tWinner)) {
        tWinnerText.setText("Game is a tie!");
        tPlayerTxt.setText("");
      }

      else if ("X".equals(tWinner) || "O".equals(tWinner)) {
        tWinnerText.setText(tWinner + " is the winner!");
        tPlayerTxt.setText("");
      }

      ticTacToe("reset");
    }

    if ("reset".equals(operation))
      tReset.setVisible(true);

  }

  private void loginPanel(boolean changeTextMode) {

    JLabel userLabel;
    JLabel passLabel;

    loginPanel = new JPanel(null);
    loginPanel.setBackground(Color.gray);

    if (changeTextMode && ranLogin) {
      if (isLoginMode)
        loginModeLabel.setText("Login Page");
      else
        loginModeLabel.setText("Register Page");
      return;
    }

    else if (!ranLogin && changeTextMode) {
      loginPanel(false);
      loginPanel(true);
    }

    userLabel = new JLabel("Username:");
    userLabel.setBounds(10, 50, 70, 50);
    userLabel.setForeground(Color.white);
    loginPanel.add(userLabel);

    userField = new JTextField(20);
    userField.setBounds(80, 65, 170, 20);
    loginPanel.add(userField);

    passLabel = new JLabel("Password:");
    passLabel.setBounds(10, 80, 70, 50);
    passLabel.setForeground(Color.white);
    loginPanel.add(passLabel);

    passField = new JPasswordField(20);
    passField.setBounds(80, 95, 170, 20);
    loginPanel.add(passField);

    loginLabel = new JLabel("");
    loginLabel.setBounds(80, 160, 300, 20);
    loginLabel.setForeground(Color.white);
    loginPanel.add(loginLabel);

    loginButton = new JButton("Login");
    loginButton.setBounds(80, 130, 70, 30);
    loginButton.addActionListener(this);
    loginPanel.add(loginButton);

    registerButton = new JButton("Register");
    registerButton.setBounds(150, 130, 100, 30);
    registerButton.addActionListener(this);
    loginPanel.add(registerButton);

    loginModeLabel = new JLabel("Login Page");
    loginModeLabel.setBounds(80, 25, 200, 20);
    loginModeLabel.setForeground(Color.white);
    loginPanel.add(loginModeLabel);

    loginPanel.updateUI();
    ranLogin = true;
  }

  @Override
  public void actionPerformed(java.awt.event.ActionEvent e) {
    if (e.getSource() == loginButton) {
      loginPanel(true);
      if (userField.getText().equals(username))
        if (charToString(passField.getPassword()).equals(password)) {
          loginLabel.setText("Login successfully");
          loginLabel.setForeground(Color.white);
        } else {
          loginLabel.setText("Username or password incorrect");
          loginLabel.setForeground(Color.red);
        }

      else {
        loginLabel.setText("Username or password incorrect");
        loginLabel.setForeground(Color.red);
      }
      return;
    }

    if (e.getSource() == registerButton) {
      if (isLoginMode) {
        isLoginMode = false;
        loginLabel.setText("");
        loginLabel.setForeground(Color.white);
        loginPanel(true);
      } else {
        if (!userField.getText().equals("") && !charToString(passField.getPassword()).equals("")) {
          username = userField.getText();
          password = charToString(passField.getPassword());
        } else {
          loginLabel.setText("Username or password cannot be blank");
          loginLabel.setForeground(Color.white);
        }
        isLoginMode = true;
      }
      return;
    }

    if (e.getSource() == tExit) {
      frame.dispose();
      return;
    }

    if (e.getSource() == tReset) {
      frame.dispose();
      new Gui();
    }

    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board.length; j++) {
        if (e.getSource() == board[i][j]) {
          if (board[i][j].getText() == "") {
            if (tIsCross) {
              board[i][j].setText("X");
              tPlayerTxt.setText("O:");
            }

            else {
              board[i][j].setText("O");
              tPlayerTxt.setText("X:");
            }
            tIsCross = !tIsCross;
            tMoves++;
            ticTacToe("check");
          }

        }
      }

  }

  private String charToString(char[] arr)
  {
    String s = "";
    for (int i = 0; i < arr.length; i++)
      s += arr[i];
    return s;
  }

}
