package Chess;

import Chess.Piece.Name;

public class GameLogic 
{
 
    public static boolean isLegal(Piece p,Piece target, Piece[][] board)
    {
        //pawn movement
        if (p.type == Piece.Name.W_P || p.type == Piece.Name.B_P)
        isLegalPawn(p, target, board);

        return false;
    }

    private static boolean isLegalPawn(Piece p,Piece target, Piece[][] board)
    {
        // normal move
        if(p.place.y == 1 || p.place.y == 6)
        {
            if (target.type == Name.BLANK && 
            (Math.abs(p.place.y - target.place.y) == 1 || Math.abs(p.place.y - target.place.y) == 2))
            return true;
        }
        return false;
    }

}
