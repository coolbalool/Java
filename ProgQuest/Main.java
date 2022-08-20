package ProgQuest;
import java.util.Scanner;

public class Main 
{
        
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String i = in.nextLine();
        String p = in.nextLine();
        String newString = "";

        int offset =0;

       // System.out.println(i.charAt(0));
        for (int j = 0; j < i.length() + offset; j++)
        {
            
            if(i.charAt(j)== p.charAt(j+ offset)) newString += p.charAt(j+ offset);
            else offset++;
        }
        
        if (!i.equals(newString)) System.out.println("IMPOSSIBLE");
        else System.out.println(offset);
    
    }

}
