
// create a file with browser 
// cntrl s save wwith indicator

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class  TextWriter implements ActionListener
{
    
    private JFrame frame;
    private JPanel panel;
    private JButton applyBtn,resetBtn,openFBtn,newFBtn;
    private JTextArea textArea;
    private JFileChooser fc;

    private File f;
    private FileWriter fw;

    public TextWriter()
    {
    
        frame = new JFrame("File Writer");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        panel = new JPanel(null);
        frame.add(panel);

        
        textArea = new JTextArea();
        textArea.setBounds(10,frame.getHeight() / 10,frame.getWidth(),frame.getHeight() - frame.getHeight() / 10);
        panel.add(textArea);

        applyBtn = new JButton("Apply");
        applyBtn.setBounds(10,0,80,frame.getHeight()/10);
        applyBtn.addActionListener(this);
        panel.add(applyBtn);

        resetBtn = new JButton("Reset");
        resetBtn.setBounds(applyBtn.getX() + applyBtn.getWidth() + 10,0,80,frame.getHeight()/10);
        resetBtn.addActionListener(this);
        panel.add(resetBtn);

        openFBtn = new JButton("Open");
        openFBtn.setBounds(resetBtn.getX() + resetBtn.getWidth() + 10,0,80,frame.getHeight()/10);
        openFBtn.addActionListener(this);
        panel.add(openFBtn);

        newFBtn = new JButton("New");
        newFBtn.setBounds(openFBtn.getX() + openFBtn.getWidth() + 10,0,80,frame.getHeight()/10);
        newFBtn.addActionListener(this);
        panel.add(newFBtn);

        fc = new JFileChooser();

        panel.updateUI();
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
            writeToFile(null);
            textArea.setText(null);
        }

        if (e.getSource() == openFBtn)
        {
            
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                f = fc.getSelectedFile();
                loadFile(f);
            }

        }

    }

    }



