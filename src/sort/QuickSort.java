package sort;

import app.Product;
import app.Stock;
//import java.util.Arrays;

public class QuickSort implements Sort
{

    @Override
    public Stock sort(Stock stock)
    {
        Product vector[] = stock.getStock();
        //vector = Arrays.copyOfRange(vector, 0, stock.getTotalProducts() - 1);
        
        quickSort(vector, 0, stock.getTotalProducts() - 1);
        
        Stock newStock = new Stock();
        newStock.addProducts(vector, stock.getTotalProducts());
        return newStock;
    }

    private void quickSort(Product vector[], int start, int end)
    {
        int pivotPosition;
        
        if(end > start)
        {
            pivotPosition = partVector(vector, start, end);
            quickSort(vector, start, pivotPosition - 1);
            quickSort(vector, pivotPosition + 1, end);
        }
    }

    private int partVector(Product vector[], int start, int end)
    {
        int left, right;
        
        left = start;
        right = end;
        
        Product pivot = vector[start];
        
        while(left < right)
        {
            while(vector[left].getName().compareToIgnoreCase(pivot.getName()) <= 0 && left < end)
            {
                left++;
            }
            while(vector[right].getName().compareToIgnoreCase(pivot.getName()) > 0 && right > start)
            {
                right--;
            }
            if(left < right)
            {
                swapValue(vector, left, right);
            }
        }
        
        vector[start] = vector[right];
        vector[right] = pivot;
        
        return right;
    }

    private void swapValue(Product vector[], int left, int right)
    {
        Product aux = vector[left];
        
        vector[left] = vector[right];
        vector[right] = aux;
    }
    
}
