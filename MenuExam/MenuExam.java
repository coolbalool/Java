package MenuExam;
import javax.swing.JFrame;

public class MenuExam extends JFrame 
{

    public static void main(String[] args)
    {
        new MenuExam();
    }

    public MenuExam()
    {
        super("Menu Exam");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
        new Layout(this);
        
    }

    

}