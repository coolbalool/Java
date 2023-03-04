package Chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardManager implements ActionListener
{

    
    public BoardManager()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
      for (int i = 0; i < Main.board.length; i++)
        for (int j = 0; j < Main.board.length; j++)
            if (Main.board[i][j].button == e.getSource())
            {
                GameLogic.movePiece(Main.board[1][1],Main.board[3][5], false);
                Main.panel.updateUI();
            }
    }
    
}
