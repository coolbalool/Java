
/* 
    TODO LIST:
    cntrl s save wwith indicator
    all color and theme selector
    file loaded indicator
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSpinner;

public class TextWriter implements ActionListener {

    public static void main(String[] args) {
        new TextWriter();
    }

    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JFileChooser fc;
    private JSpinner sizeSpinner;

    private Scanner in;
    private File f;
    private FileWriter fw;

    private JMenuBar menuBar;
    private JMenu fileMenu, styleMenu, settingsMenu,
            styleSubMenu[], settingsSubMenu[];
    private JMenuItem fileItem[],
            styleColorItem[],
            styleFontItem[], styleStyleItem[],
            settingsThemeItem[], settingsItem[];

    private final String[]
            TEXT_COLOR = { "Black", "White", "Red", "Green", "Blue" },
            TEXT_FONT = { "Serif", "SansSerif", "Monospaced" },
            TEXT_STYLE = { "plain", "bold", "italic", "bold italic" },
            SETTINGS_THEME = { "Dark", "Light" };

    public TextWriter() {
        frame = new JFrame("File Writer");
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        frame.addComponentListener(new ComponentListener()
        {
            @Override
            public void componentResized(ComponentEvent e) 
            {
                // calls to the things that need to be dynamic scaled
                textArea.setBounds(0, 0,frame.getWidth(), frame.getHeight());

            }
            @Override public void componentHidden(ComponentEvent e) {}
            @Override public void componentMoved(ComponentEvent e) {}
            @Override public void componentShown(ComponentEvent e) {}
        });

        panel = new JPanel(null);
        frame.add(panel);

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
                Integer.parseInt(sizeSpinner.getValue().toString())));}
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

        menuBar.add(fileMenu);
        menuBar.add(styleMenu);
        menuBar.add(settingsMenu);
        frame.setJMenuBar(menuBar);

        textArea = new JTextArea();
        textArea.setBounds(0, 0,frame.getWidth(), frame.getHeight());
        panel.add(textArea);

        fc = new JFileChooser();

        panel.updateUI();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // save
        if (e.getSource() == fileItem[0]) {
            try {
                if (f == null)
                f = new File("NewFile.txt");
                fw = new FileWriter(f);
                fw.write(textArea.getText());
                fw.close();
            } catch (Exception s) {
                System.err.println(s.toString());
            }
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
            return;
        }
        // reset
        if (e.getSource() == fileItem[2]){
            try{
             fw = new FileWriter(f);
             fw.write(new String());
             textArea.setText(new String());
            fw.close();}
            catch (Exception e2) {e2.printStackTrace();}
            return;
        }
        //  new 
        if (e.getSource() == fileItem[3]) {
        
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                f = new File(fc.getSelectedFile().getAbsolutePath() + "");
            }
            return;
        }
        //color
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
        //font
        for (int i = 0; i < styleFontItem.length; i++) {
            if (e.getSource() == styleFontItem[i]) {
                textArea.setFont(new Font(styleFontItem[i].getText(),
                        textArea.getFont().getStyle(), textArea.getFont().getSize()));
                return;
            }
        }
        //style
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
        //theme
        for (int i = 0; i < settingsThemeItem.length; i++) {
            if (e.getSource() == settingsThemeItem[i]) {
                if (settingsThemeItem[i].getText().equals(SETTINGS_THEME[0])) {
                    menuBar.setBackground(Color.DARK_GRAY);
                    textArea.setBackground(Color.GRAY);
                    fileMenu.setForeground(Color.WHITE);
                    styleMenu.setForeground(Color.WHITE);
                    settingsMenu.setForeground(Color.WHITE);
                    return;
                } else if (settingsThemeItem[i].getText().equals(SETTINGS_THEME[1])) {
                    menuBar.setBackground(Color.LIGHT_GRAY);
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
                    frame.dispose();

                return;
            }
        }
    }

}
