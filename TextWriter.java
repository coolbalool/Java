
// create a file with browser 
// cntrl s save wwith indicator

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
    private final int DEF_WIDTH = -1;

    public TextWriter()
    {
        frame = new JFrame("File Writer");
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        panel = new JPanel(null);
        frame.add(panel);

        textArea = new JTextArea();
        textArea.setBounds(10,frame.getHeight()/10,
        frame.getWidth(),frame.getHeight()-frame.getHeight()/10);
        panel.add(textArea);

        applyBtn = new JButton("Apply");
        addToTop(applyBtn,true,DEF_WIDTH);

        resetBtn = new JButton("Reset");
        addToTop(resetBtn, true, DEF_WIDTH);

        openFBtn = new JButton("Open");
        addToTop(openFBtn, true, DEF_WIDTH);

        newFBtn = new JButton("New");
        addToTop(newFBtn, true, DEF_WIDTH);

        textSizeBox = new JComboBox<String>(TEXT_SIZE);
        addToTop(textSizeBox, false,40);
        textSizeBox.addActionListener(this);

        textColorBox = new JComboBox<String>(TEXT_COLOR);
        addToTop(textColorBox, false,60);
        textColorBox.addActionListener(this);

        textFontBox = new JComboBox<String>(TEXT_FONT);
        addToTop(textFontBox, false,100);
        textFontBox.addActionListener(this);

        textStyleBox = new JComboBox<String>(TEXT_STYLE);
        addToTop(textStyleBox, false,DEF_WIDTH);
        textStyleBox.addActionListener(this);

        fc = new JFileChooser();

        panel.updateUI();
    }

    private void addToTop(Component obj, boolean isButton,int width)
    {
        int w = width;
        if (width == DEF_WIDTH) w = 80;
        if (last != null)
        {
        obj.setBounds(last.getX()+
        last.getWidth()+
        10,0,w,frame.getHeight()/10);
        }
        else obj.setBounds(10,0,w,frame.getHeight()/10);

        if(isButton)
        ((AbstractButton) obj).addActionListener(this);

        panel.add(obj);
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



