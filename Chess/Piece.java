package Chess;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Piece 
{
    
    public JButton button;
    public Name type;
    public Place place;

    public Piece(Name type)
    {
        if (type == null) type = Name.BLANK;
        this.type = type;
        if(type != Name.BLANK)
        {
        button.setIcon(new ImageIcon(
            new ImageIcon("Chess/Pieces/" + type.toString() + ".png")
            .getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT)));
        
        }
    }

    public Piece(JButton button,Name type)
    {
        this.button = button;
        if (type == null) type = Name.BLANK;
        this.type = type;
        if(type != Name.BLANK)
        {
        button.setIcon(new ImageIcon(
            new ImageIcon("Chess/Pieces/" + type.toString() + ".png")
            .getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT)));
        
        }
    }

    public void setPlace()
    {
        for(int i = 0; i < Main.board.length; i++)
            for(int j = 0; j < Main.board.length;j++)
                if (this == Main.board[i][j]) place = new Place(i, j);
    }

    public Piece()
    {
        this.button = null;
        this.type = Name.BLANK;
        this.place = new Place();
    }

    public enum Name
    {
        B_B,B_K,B_N,B_P,B_Q,B_R,W_B,W_K,W_N,W_P,W_Q,W_R,BLANK;    
    }

}
