package sort;

import app.Product;
import app.Stock;

public class InsertionSort implements Sort
{

    @Override
    public Stock sort(Stock stock)
    {
        Product vector[] = stock.getStock();
        Product aux;
        int position;
        
        for(int i = 1; i < stock.getTotalProducts(); i++)
        {
            aux = vector[i];
            position = getPosition(vector, i, aux);
            vector[position] = aux;
        }
        
        Stock newStock = new Stock();
        newStock.addProducts(vector, stock.getTotalProducts());
        return newStock;
    }

    private int getPosition(Product vector[], int position, Product aux)
    {
        while((position > 0) && (vector[position - 1].getName().compareToIgnoreCase(aux.getName()) > 0))
        {
            vector[position] = vector[position - 1];
            position--;
        }
        
        return position;
    }
    
}
