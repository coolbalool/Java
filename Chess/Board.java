package Chess;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board 
{

    private JPanel panel;
    public JButton[][] board;

    public Board(JPanel panel)
    {
        this.panel = panel;
        setBoard();
        setPieces();
    }

    private void setBoard()
    {
        board = new JButton[8][8];
        boolean paintWhite = true;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = new JButton(i + " " + j);
                board[i][j].setBounds(100*(i), 70*(j), 100, 70);
                if(allEven(i, j)) board[i][j].setBackground(new Color(255,255,255));
                else 
                {
                    board[i][j].setBackground(new Color(10,25,130));
                    board[i][j].setForeground(Color.WHITE);
                }
                paintWhite = !paintWhite;
                board[i][j].addActionListener(new ButtonListener());
                board[i][j].setVisible(true);
                panel.add(board[i][j]);
            }
        }

    }

    private void setPieces()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                
                // Pawn
                if (j == 1)
                    board[i][j].setText("B-P");
                else if (j == board.length -2)
                    board[i][j].setText("W-P");

                // Rook
                else if (j == 0 &&(i == 0 || i == board[0].length-1))
                    board[i][j].setText("B-R");
                else if (j == board.length-1 &&(i == 0 || i == board.length-1))
                board[i][j].setText("W-R");

                // Knight
                else if (j == 0 &&(i == 1 || i == board.length-2))
                    board[i][j].setText("B-N");
                else if (j == board.length-1 &&(i == 1 || i == board.length-2))
                    board[i][j].setText("W-N");

                // Bishop
                else if (j == 0 &&(i == 2 || i == board.length-3))
                    board[i][j].setText("B-B");
                else if (j == board.length-1 &&(i == 2 || i == board.length-3))
                    board[i][j].setText("W-B");
                
                // Queen
                else if (j == 0 && i == 3)
                    board[i][j].setText("B-Q");
                else if (j == board.length-1 &&i == 3)
                    board[i][j].setText("W-Q");
                
                // King
                else if (j == 0 && i == 4)
                    board[i][j].setText("B-K");
                else if (j == board.length-1 && i == 4)
                    board[i][j].setText("W-K");

            }
        }
    }
    
    private boolean allEven(int i, int j)
    {
        if (i%2 !=0 && j%2 != 0) return true;
        return i % 2 ==0 && j % 2 ==0;
    }

}
