package Chess;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Piece 
{
    
    public JButton button;
    public Name type;

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

    public enum Name
    {
        B_B,B_K,B_N,B_P,B_Q,B_R,W_B,W_K,W_N,W_P,W_Q,W_R,BLANK;    
    }

}
