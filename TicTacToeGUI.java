
import javax.swing.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.awt.event.ActionListener;
import java.awt.*;

    public class TicTacToeGUI implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JButton board[][],tExit,tReset;
    private JLabel tPlayerTxt,tWinnerText;
    private boolean tIsCross;
    private int tMoves;
    private String tWinner;
    
    
    public TicTacToeGUI()
    {

    frame = new JFrame("Tic Tac Toe");
    frame.setSize(500,300);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.add(panel);
    ticTacToe(null);
    }

    public void setLayout()
    {
        panel = new JPanel(null);
        panel.setBackground(Color.gray);
    
        tExit = new JButton("Exit");
        tExit.setBounds(400,100,75,50);
        tExit.addActionListener(this);
        panel.add(tExit);
    
        tReset = new JButton("Reset");
        tReset.setBounds(400,150,75,50);
        tReset.addActionListener(this);
        tReset.setVisible(false);
        panel.add(tReset);
    
        tPlayerTxt = new JLabel("X:");
        tPlayerTxt.setForeground(Color.white);
        tPlayerTxt.setBounds(400,50,70,70);
        panel.add(tPlayerTxt);
    
        tWinnerText = new JLabel("");
        tWinnerText.setForeground(Color.white);
        tWinnerText.setBounds(310,130,130,70);
        panel.add(tWinnerText);
    
        tIsCross = true;
        tMoves = 0;
        tWinner = "";
        board = new JButton[3][3];
        for (int i = 0; i < board.length; i++)      
        for (int j = 0;  j < board.length; j++)
        {
        board[i][j] = new JButton("");
        board[i][j].setBounds(j * 100, i * 100 ,60,60);
        board[i][j].addActionListener(this);
        panel.add(board[i][j]);
        }
    }

    public void checkWinner()
    {
        // tie checker
      if (tMoves >= 9) 
      {
        tWinner = "tie";
        ticTacToe("go");
        return;
      }

      // checks if the move count is too small for a win
      if (tMoves < 4) return;
 
      String temp = "";
        for (int i = 0; i < board.length; i++)
        {
          // row
          if (board[i][0].getText() == "") continue;
          temp = board[i][0].getText();
          for(int j = 0;  j < board.length; j++)
          {
              if (!temp.equals(board[i][j].getText())) break;
              if (j == board.length -1) 
              {
                tWinner = temp;
                ticTacToe("go");
                return;
              }
            }
          }

          for (int i = 0; i < board.length; i++)  
          {
            // col
            if (board[0][i].getText() == "") continue;
            temp = board[0][i].getText();
            for(int j = 0;  j < board.length; j++)
            {
                if (!temp.equals(board[j][i].getText())) break;
                if (j == board.length -1) 
                {
                  tWinner = temp;
                  ticTacToe("go");
                  return;
                }
              }
            }

        // (0,0) mid
        if (board[0][0].getText() == "") return;
        temp = board[0][0].getText();
        for (int i = 0;  i < board.length; i++)
        {
        if (!temp.equals(board[i][i].getText())) break;
        if (i == board.length -1) 
        {
          tWinner = temp;
          ticTacToe("go");
          return;
        }
        }

        // other mid
        if (board[0][board.length -1].getText() == "") return;
        temp = board[0][board.length -1].getText();
        for (int i = 0;  i < board.length; i++)
        {
        if (!temp.equals(board[i][board.length -i -1].getText())) break;
        if (i == board.length -1) 
        {
          tWinner = temp;
          ticTacToe("go");
        }
      }
    }

    public void winHandler()
    {
        
    }

    public void ticTacToe(String operation)
    {

    if (operation == "go")
    {
      if (tWinner == "tie")
      {
        tWinnerText.setText("Game is a tie!");  
        tPlayerTxt.setText("");
      }

      else if (tWinner == "X" || tWinner == "O")
      {
        tWinnerText.setText(tWinner + " is the winner!");
        tPlayerTxt.setText("");
      }

      tReset.setVisible(true);
    }
    }

    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) 
    {
   
    if (e.getSource() == tExit)
    {
      frame.dispose();
      return;
    }

    if (e.getSource() == tReset)
    {
      frame.dispose();
      new Gui();
    }

    for (int i = 0; i < board.length; i++)
    for (int j = 0; j < board.length; j++)
    {
      if (e.getSource() == board[i][j])
      {
        if (board[i][j].getText() == "")
        {
        if (tIsCross) 
        {
          board[i][j].setText("X");
          tPlayerTxt.setText("O:");
        }
        
        else 
        {
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
    }    



