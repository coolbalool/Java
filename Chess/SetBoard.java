package Chess;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SetBoard
{

    public static Piece[][] setPieces(JPanel panel,Piece[][] board)
    {
        board = new Piece[8][8];
        boolean paintWhite = true;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {

                board[i][j] = new Piece(new JButton(),Piece.Name.BLANK);
                board[i][j].button.setBounds(100*(i), 70*(j), 100, 70);
                if(allEven(i, j)) board[i][j].button.setBackground(new Color(191, 181, 147));
                else 
                {
                    board[i][j].button.setBackground(new Color(94, 84, 50));
                    board[i][j].button.setForeground(Color.WHITE);
                }
                paintWhite = !paintWhite;
                //board[i][j].addActionListener(new GameManager(board));
                board[i][j].button.setVisible(true);
                panel.add(board[i][j].button);

                    Piece.Name type = Piece.Name.BLANK;
                // Pawn
                if (j == 1)
                    type = Piece.Name.B_P;
                else if (j == board.length -2)
                    type = Piece.Name.W_P;

                // Rook
                else if (j == 0 &&(i == 0 || i == board[0].length-1))
                    type = Piece.Name.B_R;
                else if (j == board.length-1 &&(i == 0 || i == board.length-1))
                    type = Piece.Name.W_R;

                // Knight
                else if (j == 0 &&(i == 1 || i == board.length-2))
                type = Piece.Name.B_N;
                else if (j == board.length-1 &&(i == 1 || i == board.length-2))
                type = Piece.Name.W_N;

                // Bishop
                else if (j == 0 &&(i == 2 || i == board.length-3))
                type = Piece.Name.B_B;
                else if (j == board.length-1 &&(i == 2 || i == board.length-3))
                type = Piece.Name.W_B;
                
                // Queen
                else if (j == 0 && i == 3)
                type = Piece.Name.B_Q;
                else if (j == board.length-1 &&i == 3)
                type = Piece.Name.W_Q;
                
                // King
                else if (j == 0 && i == 4)
                type = Piece.Name.B_K;
                else if (j == board.length-1 && i == 4)
                type = Piece.Name.W_K;

                board[i][j] = new Piece(board[i][j].button,type);


            }
        }
        return board;
    }
    
    
    
    
    private static boolean allEven(int i, int j)
    {
        return (i+j) % 2 == 0;
    }

}
