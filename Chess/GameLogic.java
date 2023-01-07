package Chess;

import Chess.Piece.Name;

public class GameLogic 
{
 
    public boolean isLegal(Piece p,Piece target, Piece[][] board)
    {
        //pawn movement
        if (p.type == Piece.Name.W_P || p.type == Piece.Name.B_P)
        isLegal(p, target, board);

        return false;
    }

    private boolean isLegalPawn(Piece p,Piece target, Piece[][] board)
    {
        // pawn on first move can move 2 blocks 
        if(p.type == Piece.Name.W_P && p.place.y == 1)
        {
            if (target.type == Name.BLANK && p.place.y + 2 == target.place.y)
            {
                
            }
        }
        else if (p.type == Piece.Name.B_P && p.place.y == 6)
        {

        }
        return false;
    }

}
