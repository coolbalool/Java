package Chess;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Board 
{

    private JPanel panel;
    private JButton[][] board;

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
                board[i][j] = new JButton();
                board[i][j].setBounds(100*(i), 70*(j), 100, 70);
                if(allEven(i, j)) board[i][j].setBackground(new Color(255,255,255));
                else 
                {
                    board[i][j].setBackground(new Color(10,25,130));
                    board[i][j].setForeground(Color.WHITE);
                }
                paintWhite = !paintWhite;
                board[i][j].addActionListener(new ButtonListener(board));
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
                String s = "Chess/Pieces/";
                // Pawn
                if (j == 1)
                    s += "B_P.png";
                else if (j == board.length -2)
                    s += "W_P.png";

                // Rook
                else if (j == 0 &&(i == 0 || i == board[0].length-1))
                    s += "B_R.png";
                else if (j == board.length-1 &&(i == 0 || i == board.length-1))
                    s += "W_R.png";

                // Knight
                else if (j == 0 &&(i == 1 || i == board.length-2))
                    s += "B_N.png";
                else if (j == board.length-1 &&(i == 1 || i == board.length-2))
                    s += "W_N.png";

                // Bishop
                else if (j == 0 &&(i == 2 || i == board.length-3))
                    s += "B_B.png";
                else if (j == board.length-1 &&(i == 2 || i == board.length-3))
                    s += "W_B.png";
                
                // Queen
                else if (j == 0 && i == 3)
                    s += "B_Q.png";
                else if (j == board.length-1 &&i == 3)
                    s += "W_Q.png";
                
                // King
                else if (j == 0 && i == 4)
                    s += "B_K.png";
                else if (j == board.length-1 && i == 4)
                    s += "W_K.png";

                if (s == "Chess/Pieces/") continue;

                board[i][j].setIcon(new ImageIcon(
                    new ImageIcon(s).getImage().getScaledInstance(
                        board[i][j].getWidth(),board[i][j].getHeight(),Image.SCALE_DEFAULT)));

            }
        }
    }
    
    private boolean allEven(int i, int j)
    {
        if (i%2 !=0 && j%2 != 0) return true;
        return i % 2 ==0 && j % 2 ==0;
    }

}
