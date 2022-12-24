package Chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JButton;

public class ButtonListener implements ActionListener
{
    private JButton[][] board;
    private boolean hasChose;
    private JButton chosen;
    private int[] place = {-1,-1};

    public ButtonListener(JButton[][] board)
    {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
            {
                if (e.getSource() == board[i][j]){
                if (board[i][j].getIcon() == null && hasChose)
                {
                    System.out.println("3");
                    System.out.println(place[0] + "," + place[1] + " : " + i + "," + j);
                    hasChose = false;
                    place = new int[]{-1,-1};
                    chosen = null;

                }
                else if (board[i][j].getIcon() != null)
                {
                    
                    System.out.println("d" + i + j);
                    hasChose = true;
                    chosen = board[i][j];
                    place = new int[] {i,j};
                }
               //x System.out.println(hasChose);
            }

            }
        
    }
    
}
