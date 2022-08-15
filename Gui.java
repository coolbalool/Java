import javax.swing.*;

public class Gui {

    public static void main(String[] args) 
    {
        // JFrame frame = new JFrame();
        // JPanel panel = new JPanel();    
        // panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        // panel.setLayout(new GridLayout(0,1));

        JFrame f=new JFrame();//creating instance of JFrame  
          
JButton b=new JButton("click");//creating instance of JButton  
b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
          
f.add(b);//adding button in JFrame  
          
f.setSize(400,500);//400 width and 500 height  
f.setLayout(null);//using no layout managers  
f.setVisible(true);//making the frame visible  
    }
}
