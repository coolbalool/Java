package Chess;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame
{

    public static JPanel panel;
    public static Piece[][] board;

    public static void main(String[] args) 
    {
         new Main();
    }

    public Main()
    {
        super("Chess");
        setSize(810,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
        panel = new JPanel(null);
        add(panel);
        SetBoard.setPieces(panel);
        System.out.println(GameLogic.isLegal(board[3][1],board[3][2]));
        panel.updateUI();


    }

}