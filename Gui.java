import javax.swing.*;


public class Gui {

    public Gui()
    {
        JFrame frame = new JFrame();    //creating instance of JFrame  

    JButton button =new JButton("Free VeBux");   //creating instance of JButton  
    button.setBounds(130,100,200, 200);  //x axis, y axis, width, height  

    JTextArea text = new JTextArea("Click here for free ram");

    frame.add(button,text);//adding button in JFrame  
    frame.setSize(400,500);//400 width and 500 height  
    frame.setLayout(null);//using no layout managers  
    frame.setVisible(true);//making the frame visible  
    frame.setTitle("Theniggezx");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
}
}
