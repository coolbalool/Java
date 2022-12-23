
public class Node
{

    private Node left,right;
    private int value;

    public Node(int value)
    {
        this.value = value;
    }

    public void insert(int value)
    {
        if (value <= this.value)
            if (left == null)
                left = new Node(value);
            else 
                left.insert(value);
        
        else 
            if (right == null)
                right = new Node(value);
            else right.insert(value);

    }

    public boolean contains(int value)
    {
        if (value == this.value)
            return true;
        if (value < this.value)
        {
            if(left == null)
                return false;
             return left.contains(value);
        }
        else
        { 
            if (right == null)
                return false;
            return right.contains(value);
        }
    }

 

}