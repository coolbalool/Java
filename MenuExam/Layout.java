package MenuExam;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout implements ActionListener
{
    private JButton[] button;
    private JCheckBox checkBox;
    private JTextField text;
    private JFrame frame;
    private Logic logic;

    public Layout(JFrame frame)
    {
       this.frame = frame;
       logic = new Logic();
       checkBox();
       text();
       buttonManager(); 
    }

    public void checkBox()
    {
        checkBox = new JCheckBox("Tip (15%)", false);
        checkBox.setBounds(frame.getX()/2, 160, 100, 80);
        checkBox.addActionListener(this);
        frame.add(checkBox);
    }

    public void text()
    {
        text = new JTextField(Double.toString(logic.getFinalPrice()));
        text.setEditable(false);
        text.setBounds(frame.getX()/2-100,300,60,60);
        frame.add(text);
    }

    public void buttonManager()
    {
        button = new JButton[4];
        for (int i = 0; i < button.length; i++)
        {
            button[i] = new JButton(Integer.toString(i*30) + '$');
            button[i].setBounds(80*i,50,80,80);
            button[i].addActionListener(this);
            frame.add(button[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
     
        if (e.getSource() == checkBox)
            logic.isTip(checkBox.isSelected());

        else for (int i = 0; i < button.length; i++)
            if (e.getSource() == button[i])
                logic.addMoney(Integer.parseInt(button[i].getText().substring
                (0,button[i].getText().length()-1)));

                text();
    }

}
