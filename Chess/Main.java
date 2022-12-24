package Chess;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame
{

    JPanel panel = new JPanel(null);

    public static void main(String[] args) 
    {
         new Main();
    }

    public Main()
    {
        super("Chess");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
        add(panel);
        new Board(panel);
        panel.updateUI();

    }

}