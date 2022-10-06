
import javax.swing.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.awt.event.ActionListener;
import java.awt.*;

    public class TicTacToeGUI implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JButton board[][],exit,reset;
    private JLabel playerTxt,winnerTxt;
    private boolean isCross,gameOver;
    private int moves;
    
    public static void main(String[] args)
      {
        new TicTacToeGUI();
      }

    private enum GameState
      {

      SPACE(" "),
      XP("X"),
      OP("O"); 

        String state;
        GameState(String state)
        {
          this.state = state;
        }

        public String text()
        {
          return state;
        }

      }

    public TicTacToeGUI()
    {
      setLayout();
    }

    public void setLayout()
    {

      // frame 
      frame = new JFrame("Tic Tac Toe");
      frame.setSize(500,300);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
  
      // panel
      panel = new JPanel(null);
      panel.setBackground(Color.gray);
      frame.add(panel);

        // exit button
        exit = new JButton("Exit");
        exit.setBounds(400,100,75,50);
        exit.addActionListener(this);
        panel.add(exit);
    
        // reset button
        reset = new JButton("Reset");
        reset.setBounds(400,150,75,50);
        reset.addActionListener(this);
        reset.setVisible(false);
        panel.add(reset);
    
        // player turn txt
        playerTxt = new JLabel(GameState.XP.text() + ":");
        playerTxt.setForeground(Color.white);
        playerTxt.setBounds(400,50,70,70);
        panel.add(playerTxt);
    
        // winner txt
        winnerTxt = new JLabel("");
        winnerTxt.setForeground(Color.white);
        winnerTxt.setBounds(310,130,130,70);
        panel.add(winnerTxt);
    
        // board + start things
        isCross = true;
        gameOver = false;
        moves = 0;
        board = new JButton[3][3];
        for (int i = 0; i < board.length; i++)      
        for (int j = 0;  j < board.length; j++)
        {
        board[i][j] = new JButton(GameState.SPACE.text());
        board[i][j].setBounds(j * 100, i * 100 ,60,60);
        board[i][j].addActionListener(this);
        panel.add(board[i][j]);
        }

        // refresh panel
        panel.updateUI();
    }

    public void checkWinner()
    {

      // checks if the move count is too small for a win
      if (moves < 4) return;

        // tie checker
      if (moves >= 9) 
      {
        winHandler(GameState.SPACE);
        return;
      }

      String temp = "";
        for (int i = 0; i < board.length; i++)
        {
          // row
          if (board[i][0].getText() == GameState.SPACE.text()) continue;
          temp = board[i][0].getText();
          for(int j = 0;  j < board.length; j++)
          {
              if (!temp.equals(board[i][j].getText())) break;
              if (j == board.length -1) 
              {

                if(temp.equals(GameState.XP.text()))
                winHandler(GameState.XP);

                if(temp.equals(GameState.OP.text()))
                winHandler(GameState.OP);
                return;
              }
            }
          }

          for (int i = 0; i < board.length; i++)  
          {
            // col
            if (board[0][i].getText() == GameState.SPACE.text()) continue;
            temp = board[0][i].getText();
            for(int j = 0;  j < board.length; j++)
            {
                if (!temp.equals(board[j][i].getText())) break;
                if (j == board.length -1) 
                {
                  if(temp.equals(GameState.XP.text()))
                winHandler(GameState.XP);

                if(temp.equals(GameState.OP.text()))
                winHandler(GameState.OP);
  
                  return;
                }
              }
            }

        // (0,0) mid
        if (board[0][0].getText() == GameState.SPACE.text()) return;
        temp = board[0][0].getText();
        for (int i = 0;  i < board.length; i++)
        {
        if (!temp.equals(board[i][i].getText())) break;
        if (i == board.length -1) 
        {
          if(temp.equals(GameState.XP.text()))
                winHandler(GameState.XP);

                if(temp.equals(GameState.OP.text()))
                winHandler(GameState.OP);

          return;
        }
        }

        // other mid
        if (board[0][board.length -1].getText() == GameState.SPACE.text()) return;
        temp = board[0][board.length -1].getText();
        for (int i = 0;  i < board.length; i++)
        {
        if (!temp.equals(board[i][board.length -i -1].getText())) break;
        if (i == board.length -1) 
        {
               if(temp.equals(GameState.XP.text()))
                winHandler(GameState.XP);

                if(temp.equals(GameState.OP.text()))
                winHandler(GameState.OP);

                return;
        }
      }
    }

    public void winHandler(GameState state)
    {
      if (state == GameState.SPACE)
      {
        winnerTxt.setText("Game is a tie!");  
        playerTxt.setText("");
      }

      else
      {
        winnerTxt.setText(state.text() + " is the winner!");
        playerTxt.setText("");
      }

      gameOver = true;
      reset.setVisible(true);
    }
  
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) 
    {
   
    if (e.getSource() == exit)
    {
      frame.dispose();
      return;
    }

    if (e.getSource() == reset)
    {
      frame.dispose();
      setLayout();
    }

    for (int i = 0; i < board.length; i++)
    for (int j = 0; j < board.length; j++)
    {
      if (e.getSource() == board[i][j] && !gameOver)
      {
        if (board[i][j].getText() == GameState.SPACE.text())
        {
        if (isCross) 
        {
          board[i][j].setText(GameState.XP.text());
          playerTxt.setText(GameState.OP.text() + ":");
        }
        
        else 
        {
          board[i][j].setText(GameState.OP.text());
          playerTxt.setText(GameState.XP.text() + ":");
        }
        isCross = !isCross;
        moves++;
        checkWinner();
      }

    }
    }

    }
    }    



