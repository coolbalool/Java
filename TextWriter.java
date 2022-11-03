
/* 
    TODO LIST:
    make multi files 
    make code better 
    
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Stack;
import java.awt.event.KeyListener;
import java.awt.GraphicsEnvironment;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSpinner;

public class TextWriter extends JFrame implements ActionListener, KeyListener {

    public static void main(String[] args) {
        new TextWriter();
    }

    private JTextArea textArea;
    private JFileChooser fc;
    private JSpinner sizeSpinner;
    private JTextField currFileText;
    private JScrollPane textScrollPane;
    private JColorChooser colorChooser;
    private JComboBox<String> fontComboBox;
    private JCheckBox styleItalicBox,styleBoldBox,themeBox;

    private Scanner in;
    private File f;
    private FileWriter fw;

    private Stack<String> undoStack;

    private JMenuBar menuBar;
    private JMenu fileMenu, styleMenu, settingsMenu;
    private JMenuItem fileItem[], styleColorButton,exitButton;

    public TextWriter() {
        setTitle("Text Writer");
        setDefaultCloseOperation(3);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        styleMenu = new JMenu("Style");
        settingsMenu = new JMenu("Settings");

        fileItem = new JMenuItem[] {
                new JMenuItem("Save"),
                new JMenuItem("Load"),
                new JMenuItem("Reset"),
                new JMenuItem("Save As") };

        for (int i = 0; i < fileItem.length; i++) {
            fileMenu.add(fileItem[i]);
            fileItem[i].addActionListener(this);
        }
     // font menu
        sizeSpinner = new JSpinner();
        sizeSpinner.setValue(25);
        sizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getName(),
                        textArea.getFont().getStyle(),
                        Integer.parseInt(sizeSpinner.getValue().toString())));
            }
        });
        styleMenu.add(sizeSpinner);
        styleColorButton = new JMenuItem("Text Color");
        styleColorButton.addActionListener(this);
        styleMenu.add(styleColorButton);
            //styke box
            styleItalicBox = new JCheckBox("Italic");
            styleItalicBox.addActionListener(this);
            styleMenu.add(styleItalicBox);
            styleBoldBox = new JCheckBox("Bold");
            styleBoldBox.addActionListener(this);
            styleMenu.add(styleBoldBox);
            // settings menu
            themeBox = new JCheckBox("Dark Theme");
            themeBox.addActionListener(this);
            settingsMenu.add(themeBox); 
            exitButton =  new JMenuItem("Exit");
            exitButton.addActionListener(this);
            settingsMenu.add(exitButton);
        
        currFileText = new JTextField(getCurrFileText());
        currFileText.setEditable(false);

        menuBar.add(fileMenu);
        menuBar.add(styleMenu);
        menuBar.add(settingsMenu);
        fontComboBox = new JComboBox<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontComboBox.addActionListener(this);
        menuBar.add(fontComboBox);
        menuBar.add(currFileText);
        this.setJMenuBar(menuBar);
        // text area
        textArea = new JTextArea();
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setSize(getWidth(), getHeight());
        textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(textScrollPane);
        textArea.addKeyListener(this);

        fc = new JFileChooser();
        colorChooser = new JColorChooser();
        undoStack = new Stack<String>();

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                // calls to the things that need to be dynamic scaled
                textScrollPane.setSize(getWidth(), getHeight());
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }
        });

        addKeyListener(this);
        this.pack();
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    public String getCurrFileText() {

        if (f == null)
            return "No Open File";
        else
            return "Current File:" + f.getName();
    }

    public void saveFile() {
        try {
            if (f == null)
                f = new File("NewFile.txt");
            fw = new FileWriter(f);
            fw.write(textArea.getText());
            fw.close();
        } catch (Exception s) {
            System.err.println(s.toString());
        }
        currFileText.setText(getCurrFileText());
    }

    public void fontStyleManager()
    {
        if (styleBoldBox.isSelected() && styleItalicBox.isSelected())
        textArea.setFont(new Font(textArea.getFont().getName(),Font.BOLD | Font.ITALIC,textArea.getFont().getSize()));
        else if (styleBoldBox.isSelected())
        textArea.setFont(new Font(textArea.getFont().getName(),Font.BOLD,textArea.getFont().getSize()));
        else if (styleItalicBox.isSelected())
        textArea.setFont(new Font(textArea.getFont().getName(),Font.ITALIC,textArea.getFont().getSize()));
        else textArea.setFont(new Font(textArea.getFont().getName(),Font.PLAIN,textArea.getFont().getSize()));
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // save
        if (e.getSource() == fileItem[0]) saveFile();
        // load
        else if (e.getSource() == fileItem[1]) {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String text = new String();
                    f = new File(fc.getSelectedFile().getAbsolutePath());
                    System.out.println(f.getName());
                    in = new Scanner(f);
                    while (in.hasNextLine())
                        text += in.nextLine() + "\n";
                    textArea.setText(text);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            currFileText.setText(getCurrFileText());
        }
        // reset
        else if (e.getSource() == fileItem[2]) {
            try {
                fw = new FileWriter(f);
                fw.write(new String());
                textArea.setText(new String());
                fw.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }}
        // new
        else if (e.getSource() == fileItem[3]) {
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                f = new File(fc.getSelectedFile().getAbsolutePath() + ".txt");
                saveFile();
            }}
        // color
        else if (e.getSource() == styleColorButton)
        textArea.setForeground(JColorChooser.showDialog(colorChooser, "Choose Text Color", textArea.getForeground()));
        // font
        else if(e.getSource() == fontComboBox)
        textArea.setFont(new Font((String)fontComboBox.getSelectedItem(),textArea.getFont().getStyle(), textArea.getFont().getSize()));
        //style
        else if (e.getSource() == styleBoldBox || e.getSource() == styleItalicBox) fontStyleManager();
        // theme
        else if (e.getSource() == themeBox) {
                if (themeBox.isSelected()) {
                    menuBar.setBackground(Color.BLACK);
                    textArea.setBackground(Color.DARK_GRAY);
                    fileMenu.setForeground(Color.WHITE);
                    styleMenu.setForeground(Color.WHITE);
                    settingsMenu.setForeground(Color.WHITE);
                } else{
                    menuBar.setBackground(new Color(238, 238, 238));
                    textArea.setBackground(Color.WHITE);
                    fileMenu.setForeground(Color.BLACK);
                    styleMenu.setForeground(Color.BLACK);
                    settingsMenu.setForeground(Color.BLACK);}}
        // settings button
       if(e.getSource() == exitButton) 
        this.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.isControlDown()) {
            if (e.getKeyCode() == KeyEvent.VK_Z)
                textArea.setText((String) undoStack.pop());
            else if (e.getKeyCode() == KeyEvent.VK_S) saveFile();
            else if (e.getKeyCode() == KeyEvent.VK_W) dispose();
        } else
            undoStack.push((String) textArea.getText());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
