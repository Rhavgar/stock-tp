package search;

import app.Product;
import app.Stock;

public class IterativeBinarySearch implements Search
{

    @Override
    public int search(Stock stock, String productName)
    {
        Product vector[] = stock.getStock();
        
        int start = 0;
        int end = stock.getTotalProducts() - 1;
        int mid;
        
        while(start <= end)
        {
            mid = (start + end) / 2;
            
            if(productName.compareToIgnoreCase(vector[mid].getName()) == 0)
            {
                return mid;
            }
            if(productName.compareToIgnoreCase(vector[mid].getName()) < 0)
            {
                end = mid - 1;
            }
            else
            {
                start = mid + 1;
            }
        }
        
        return -1;
    }
    
}
