package search;

import app.Product;
import app.Stock;

public class RecursiveBinarySearch implements Search
{

    @Override
    public int search(Stock stock, String productName)
    {
        Product vector[] = stock.getStock();
        
        return binarySearch(vector, productName, 0, stock.getTotalProducts() - 1);
    }

    private int binarySearch(Product vector[], String productName, int start, int end)
    {
        int mid = (start + end) / 2;
        
        if(vector[mid].getName().equalsIgnoreCase(productName))
        {
            return mid;
        }
        if(start >= end)
        {
            return -1;
        }
        else
        {
            if(vector[mid].getName().compareToIgnoreCase(productName) < 0)
            {
                return binarySearch(vector, productName, mid + 1, end);
            }
            else
            {
                return binarySearch(vector, productName, start, mid - 1);
            }
        }
    }
    
}
