package Chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener
{
    private JButton[][] board;
    private boolean hasChose;

    public ButtonListener(JButton[][] board)
    {
        this.board = board;
        hasChose = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
            {
                if (e.getSource() == board[i][j]){
               // if (board[i][j].getText().charAt(1) != ' ')
                //{
                    hasChose = true;
                //}
                //else hasChose = false;
                System.out.println(hasChose);
            }

            }
        
    }
    
}
