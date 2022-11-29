package MenuExam;

public class Logic 
{
    
    private int count = 0;
    boolean isTip;

    public void addMoney(int amount)
    {
        count += amount;
    }

    public void isTip(boolean isTip)
    {
        this.isTip = isTip;
    }

    public double getFinalPrice()
    {
        if (isTip)
            return count * 1.15;
        else return count;
    }

}
