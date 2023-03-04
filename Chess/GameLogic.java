package Chess;

import Chess.Piece.Name;

public class GameLogic 
{
 
    public static Piece lastPiece;

    public static boolean isLegal(Piece p,Piece target)
    {
        //pawn movement
        if (p.type == Piece.Name.W_P || p.type == Piece.Name.B_P)
        return isLegalPawn(p, target);

        return false;
    }

    private static boolean isLegalPawn(Piece p,Piece target)
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
