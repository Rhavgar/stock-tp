package sort;

import app.Product;
import app.Stock;

public class SelectionSort implements Sort
{

    @Override
    public Stock sort(Stock stock)
    {
        Product vector[] = stock.getStock();
        int aux;
        
        for(int i = 0; i < stock.getTotalProducts(); i++)
        {
            aux = getLowestValuePosition(vector, i, stock.getTotalProducts());
            swap(vector, aux, i);
        }
        
        Stock newStock = new Stock();
        newStock.addProducts(vector, stock.getTotalProducts());
        return newStock;
    }

    private int getLowestValuePosition(Product vector[], int i, int size)
    {
        String lowestValue = vector[i].getName();
        int newPosition = i;
        
        for(int currentPosition = i + 1; currentPosition < size; currentPosition++)
        {
            if(vector[currentPosition].getName().compareToIgnoreCase(lowestValue) < 0)
            {
                lowestValue = vector[currentPosition].getName();
                newPosition = currentPosition;
            }
        }
        
        return newPosition;
    }

    private void swap(Product vector[], int lowest, int current)
    {
        Product aux = vector[current];
        vector[current] = vector[lowest];
        vector[lowest] = aux;
    }
    
}
