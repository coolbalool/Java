package Chess;

import Chess.Piece.Name;

public class GameLogic 
{
 
    public static Piece lastPiece;

    public static int[] getLocation(Piece p)
    {
        for (int i = 0; i < Main.board.length; i++)
            for (int j = 0;  j < Main.board[i].length; j++)
                if (Main.board[i][j] == p) 
                {
                    int[] cords = {i,j};
                    return cords;
                }
        int[] err = {-1,-1};
        System.err.println("Piece not found");
        return err;
    }

    public static void movePiece(Piece ogP,Piece newP,boolean swapPiece)
    {
        //int[] ogCords = getLocation(ogP);
        //int[] newCords = getLocation(newP);
        if (!swapPiece)
        {
            newP = new Piece(ogP.type);
            ogP = new Piece(null);
        }
    }

}
