package search;

import app.Product;
import app.Stock;

public class SequencialSearch implements Search
{
    
    @Override
    public int search(Stock stock, String productName)
    {
        Product vector[] = stock.getStock();
        
        for(int i = 0; i < stock.getTotalProducts(); i++)
        {
            if(vector[i].getName().equalsIgnoreCase(productName))
            {
                return i;
            }
        }
        
        return -1;
    }
}
