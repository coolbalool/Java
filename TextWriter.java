
// create a file with browser 
// cntrl s save wwith indicator
// optimaizze add to panel
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class  TextWriter implements ActionListener
{
    
    private JFrame frame;
    private JPanel panel;
    private JButton applyBtn,resetBtn,openFBtn,newFBtn;
    private JTextArea textArea;
    private JFileChooser fc;
    private JComboBox<String> 
    textSizeBox,textColorBox,textFontBox,textStyleBox;
    private Component last;

    private File f;
    private FileWriter fw;

    private final String[] 
    TEXT_SIZE = {"12","14","16","18","20","50"},
    TEXT_COLOR = {"Black","White","Red","Green","Blue"},
    TEXT_FONT = {"Serif", "SansSerif", "Monospaced"},
    TEXT_STYLE = {"plain","bold","italic","bold italic"};
    private final int[] DEF_BOUND = {-1,-1,-1,-1};

    public TextWriter()
    {
        frame = new JFrame("File Writer");
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        panel = new JPanel(null);
        frame.add(panel);

        textArea = new JTextArea();
        addToPanel(textArea, false,
        new int[]{10,frame.getHeight()/10,frame.getWidth(),frame.getHeight()-frame.getHeight()/10});

        applyBtn = new JButton("Apply");
        addToPanel(applyBtn,true,
        new int[]{10,0,80,frame.getHeight()/10});
        last = applyBtn;

        resetBtn = new JButton("Reset");
        addToPanel(resetBtn, true, DEF_BOUND);

        openFBtn = new JButton("Open");
        addToPanel(openFBtn, true, DEF_BOUND);

        newFBtn = new JButton("New");
        addToPanel(newFBtn, true, DEF_BOUND);

        textSizeBox = new JComboBox<String>(TEXT_SIZE);
        addToPanel(textSizeBox, false,
        new int[] {last.getX() + last.getWidth() + 10,0,40,frame.getHeight()/10});
        textSizeBox.addActionListener(this);
        last = textSizeBox;

        textColorBox = new JComboBox<String>(TEXT_COLOR);
        addToPanel(textColorBox, false,
        new int[] {last.getX() + last.getWidth() + 10,0,60,frame.getHeight()/10});
        textColorBox.addActionListener(this);
        last = textColorBox;

        textFontBox = new JComboBox<String>(TEXT_FONT);
        addToPanel(textFontBox, false,
        new int[] {last.getX() + last.getWidth() + 10,0,100,frame.getHeight()/10});
        textFontBox.addActionListener(this);
        last = textFontBox;

        textStyleBox = new JComboBox<String>(TEXT_STYLE);
        addToPanel(textStyleBox, false,
        new int[] {last.getX() + last.getWidth() + 10,0,80,frame.getHeight()/10});
        textStyleBox.addActionListener(this);
        last = textStyleBox;

        fc = new JFileChooser();

        panel.updateUI();
    }

    private void addToPanel(Component obj, boolean isButton,int[] specBnd)
    {
        if (specBnd == DEF_BOUND)
        obj.setBounds(last.getX()+
        last.getWidth()+
        10,0,80,frame.getHeight()/10);

        else 
            obj.setBounds(specBnd[0],specBnd[1],specBnd[2],specBnd[3]);

        if(isButton)
        ((AbstractButton) obj).addActionListener(this);

        panel.add(obj);

        if(specBnd == DEF_BOUND)
        last = obj;
    }

    private void writeToFile(String txt)
    {
        try
        {
            f = new File("filename.txt");
            fw = new FileWriter(f);
            fw.write(txt);
            fw.close();   
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private void loadFile(File file)
    {
        try (Scanner in = new Scanner(file)) {
            String text= "";
            while(in.hasNextLine())
                {
                    text += in.nextLine();
                    text += "\n";
                }
            textArea.setText(text);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
   
        if (e.getSource() == applyBtn)
        {
            writeToFile(textArea.getText());
        }

        if (e.getSource() == resetBtn)
        {
            writeToFile(new String());
            textArea.setText(new String());
        }

        if (e.getSource() == openFBtn)
        {
            
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                f = fc.getSelectedFile();
                loadFile(f);
            }

        }

        if (e.getSource() == newFBtn)
        {
           textArea.setText("New File Does Not Work");
        }   

        if(e.getSource() == textSizeBox)
        {
            textArea.setFont(new Font(textArea.getFont().getName(),
            textArea.getFont().getStyle(),
            Integer.parseInt(textSizeBox.getSelectedItem().toString()))); 
        }

        if (e.getSource() == textFontBox)
        {
            textArea.setFont(new Font(textFontBox.getSelectedItem().toString(),
            textArea.getFont().getStyle(),textArea.getFont().getSize())); 
        }

        if (e.getSource() == textStyleBox)
        {
            if (textStyleBox.getSelectedItem().toString().equals(TEXT_STYLE[3]))
            textArea.setFont(new Font(textArea.getFont().getName(),
            1|2,textArea.getFont().getSize())); 

            for (int i = 0; i < TEXT_STYLE.length -1; i++)
            {
                if(textStyleBox.getSelectedItem().toString().equals(TEXT_STYLE[i]))
                textArea.setFont(new Font(textArea.getFont().getName(),
            i,textArea.getFont().getSize())); 

            }

        }

        if (e.getSource() == textColorBox)
        {
            if (textColorBox.getSelectedItem().toString().equals(TEXT_COLOR[0]))
            textArea.setForeground(Color.BLACK);

            if (textColorBox.getSelectedItem().toString().equals(TEXT_COLOR[1]))
            textArea.setForeground(Color.WHITE);

            if (textColorBox.getSelectedItem().toString().equals(TEXT_COLOR[2]))
            textArea.setForeground(Color.RED);

            if (textColorBox.getSelectedItem().toString().equals(TEXT_COLOR[3]))
            textArea.setForeground(Color.GREEN);

            if (textColorBox.getSelectedItem().toString().equals(TEXT_COLOR[4]))
            textArea.setForeground(Color.BLUE);
            
        }

    }


}



