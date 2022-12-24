package Chess;

import javax.swing.JButton;

public class PieceState
{

    public int i,j;
    public JButton piece;

    public PieceState(int i, int j, JButton piece)
{
    this.i = i;
    this.j = j;
    this.piece = piece;
}

}
