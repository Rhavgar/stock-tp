package sort;

import app.Product;
import app.Stock;

public class BubbleSort implements Sort
{

    @Override
    public Stock sort(Stock stock)
    {
        Product vector[] = stock.getStock();
        boolean swapped;
        
        do
        {
            swapped = false;
            
            for(int i = 0; i < stock.getTotalProducts() - 1; i++)
            {
                if(vector[i].getName().compareToIgnoreCase(vector[i + 1].getName()) > 0)
                {
                    swap(vector, i);
                    swapped = true;
                }
            }
        }
        while(swapped);
        
        Stock newStock = new Stock();
        newStock.addProducts(vector, stock.getTotalProducts());
        return newStock;
    }
    
    public void swap(Product vector[], int position)
   {
       Product aux = vector[position];
       vector[position] = vector[position + 1];
       vector[position + 1] = aux;
   }
    
}
