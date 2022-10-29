
/* 
    TODO LIST:
    all color and font selector
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
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.Stack;
import java.awt.event.KeyListener;

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

    private Scanner in;
    private File f;
    private FileWriter fw;

    private Stack<String> undoStack;

    private JMenuBar menuBar;
    private JMenu fileMenu, styleMenu, settingsMenu,
            styleSubMenu[], settingsSubMenu[];
    private JMenuItem fileItem[], settingsItem[],
            styleColorItem[], styleFontItem[], styleStyleItem[],
            settingsThemeItem[];

    private final String[] TEXT_COLOR = { "Black", "White", "Red", "Green", "Blue" },
            TEXT_FONT = { "Serif", "SansSerif", "Monospaced" },
            TEXT_STYLE = { "plain", "bold", "italic", "bold italic" },
            SETTINGS_THEME = { "Dark", "Light" };

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

        styleSubMenu = new JMenu[] {
                new JMenu("Text Color"),
                new JMenu("Text Font"),
                new JMenu("Text Style") };
        styleColorItem = new JMenuItem[TEXT_COLOR.length];
        styleFontItem = new JMenuItem[TEXT_FONT.length];
        styleStyleItem = new JMenuItem[TEXT_STYLE.length];

        sizeSpinner = new JSpinner();
        sizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getName(),
                        textArea.getFont().getStyle(),
                        Integer.parseInt(sizeSpinner.getValue().toString())));
            }
        });
        styleMenu.add(sizeSpinner);

        for (int i = 0; i < TEXT_COLOR.length; i++) {
            styleColorItem[i] = new JMenuItem(TEXT_COLOR[i]);
            styleSubMenu[0].add(styleColorItem[i]);
            styleColorItem[i].addActionListener(this);
        }
        for (int i = 0; i < TEXT_FONT.length; i++) {
            styleFontItem[i] = new JMenuItem(TEXT_FONT[i]);
            styleSubMenu[1].add(styleFontItem[i]);
            styleFontItem[i].addActionListener(this);
        }
        for (int i = 0; i < TEXT_STYLE.length; i++) {
            styleStyleItem[i] = new JMenuItem(TEXT_STYLE[i]);
            styleSubMenu[2].add(styleStyleItem[i]);
            styleStyleItem[i].addActionListener(this);
        }

        for (int i = 0; i < styleSubMenu.length; i++)
            styleMenu.add(styleSubMenu[i]);

        settingsSubMenu = new JMenu[] {
                new JMenu("Theme"), };

        settingsItem = new JMenuItem[] {
                new JMenuItem("Exit") };

        settingsThemeItem = new JMenuItem[SETTINGS_THEME.length];

        for (int i = 0; i < SETTINGS_THEME.length; i++) {
            settingsThemeItem[i] = new JMenuItem(SETTINGS_THEME[i]);
            settingsSubMenu[0].add(settingsThemeItem[i]);
            settingsThemeItem[i].addActionListener(this);
        }

        for (int i = 0; i < settingsSubMenu.length; i++)
            settingsMenu.add(settingsSubMenu[i]);

        for (int i = 0; i < settingsItem.length; i++) {
            settingsMenu.add(settingsItem[i]);
            settingsItem[i].addActionListener(this);
        }

        currFileText = new JTextField(getCurrFileText());
        currFileText.setEditable(false);

        menuBar.add(fileMenu);
        menuBar.add(styleMenu);
        menuBar.add(settingsMenu);
        menuBar.add(currFileText);
        this.setJMenuBar(menuBar);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setSize(getWidth(), getHeight());
        textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(textScrollPane);
        textArea.addKeyListener(this);

        fc = new JFileChooser();
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

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // save
        if (e.getSource() == fileItem[0]) {
            saveFile();
            return;
        }
        // load
        if (e.getSource() == fileItem[1]) {
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
            return;
        }
        // reset
        if (e.getSource() == fileItem[2]) {
            try {
                fw = new FileWriter(f);
                fw.write(new String());
                textArea.setText(new String());
                fw.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return;
        }
        // new
        if (e.getSource() == fileItem[3]) {
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                f = new File(fc.getSelectedFile().getAbsolutePath() + ".txt");
                saveFile();
            }
            return;
        }
        // color
        for (int i = 0; i < styleColorItem.length; i++) {
            if (e.getSource() == styleColorItem[i]) {

                try {
                    Field field = Class.forName("java.awt.Color").getField(styleColorItem[i].getText().toLowerCase());
                    textArea.setForeground((Color) field.get(null));
                } catch (Exception d) {
                    System.err.println("Unknown Color !!");
                }
                return;
            }
        }
        // font
        for (int i = 0; i < styleFontItem.length; i++) {
            if (e.getSource() == styleFontItem[i]) {
                textArea.setFont(new Font(styleFontItem[i].getText(),
                        textArea.getFont().getStyle(), textArea.getFont().getSize()));
                return;
            }
        }
        // style
        for (int i = 0; i < styleStyleItem.length; i++) {
            if (e.getSource() == styleStyleItem[i]) {
                if (styleStyleItem[i].getText().equals(TEXT_STYLE[TEXT_STYLE.length - 1]))
                    textArea.setFont(new Font(textArea.getFont().getName(),
                            1 | 2, textArea.getFont().getSize()));

                else if (styleStyleItem[i].getText().equals(TEXT_STYLE[i]))
                    textArea.setFont(new Font(textArea.getFont().getName(),
                            i, textArea.getFont().getSize()));
                else
                    System.err.println("Text Style Does Not Match The Constant Order");
                return;
            }
        }
        // theme
        for (int i = 0; i < settingsThemeItem.length; i++) {
            if (e.getSource() == settingsThemeItem[i]) {
                if (settingsThemeItem[i].getText().equals(SETTINGS_THEME[0])) {
                    menuBar.setBackground(Color.BLACK);
                    textArea.setBackground(Color.DARK_GRAY);
                    fileMenu.setForeground(Color.WHITE);
                    styleMenu.setForeground(Color.WHITE);
                    settingsMenu.setForeground(Color.WHITE);
                    return;
                } else if (settingsThemeItem[i].getText().equals(SETTINGS_THEME[1])) {
                    menuBar.setBackground(new Color(238, 238, 238));
                    textArea.setBackground(Color.WHITE);
                    fileMenu.setForeground(Color.BLACK);
                    styleMenu.setForeground(Color.BLACK);
                    settingsMenu.setForeground(Color.BLACK);
                    return;
                }
            }
        }
        // settings button
        for (int i = 0; i < settingsItem.length; i++) {
            if (e.getSource() == settingsItem[i]) {

                if (settingsItem[i].getText().equals("Exit"))
                    this.dispose();

                return;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.isControlDown()) {
            if (e.getKeyCode() == KeyEvent.VK_Z)
                textArea.setText((String) undoStack.pop());
            else if (e.getKeyCode() == KeyEvent.VK_S)
                saveFile();
            else if (e.getKeyCode() == KeyEvent.VK_W)
                dispose();
        } else
            undoStack.push((String) textArea.getText());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
