package persistence;

import app.Product;
import app.Stock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessPersistence implements InterfaceIO
{

    @Override
    public void writeData(Stock stock, String fileName)
    {
        try
        {
            RandomAccessFile outFile = new RandomAccessFile(fileName, "rw");
            
            outFile.seek(outFile.length());
            
            for(int indexProduct = 0; indexProduct < stock.getTotalProducts(); indexProduct++)
            {
                writeString(outFile, stock.getProduct(indexProduct).getName(), 20);
                outFile.writeInt(stock.getProduct(indexProduct).getAmount());
                outFile.writeDouble(stock.getProduct(indexProduct).getPrice());
            }
            
            outFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Erro ao criar ou encontrar o arquivo!");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println("Erro de entrada e saída!");
            e.printStackTrace();
        }
    }
    
    private void writeString(RandomAccessFile outFile, String name, int size)
    {
        StringBuilder mutableString = new StringBuilder(name);
        mutableString.setLength(size);
        try
        {
            outFile.writeChars(mutableString.toString());            
        }
        catch(IOException e)
        {
            System.out.println("Erro de entrada e saída!");
            e.printStackTrace();
        }
    }

    @Override
    public Stock readData(String fileName)
    {
        String productName;
        int productAmount;
        double productPrice;
        Stock stock = new Stock();
        
        try
        {
            RandomAccessFile inFile = new RandomAccessFile(fileName, "r");
            
            int i;
            long n = (inFile.length() / 52);
            
            for(i=1; i<=n; i++)
            {
                productName = readString(inFile, 20);
                productAmount = inFile.readInt();
                productPrice = inFile.readDouble();
                
                Product readProduct = new Product(productName, productAmount, productPrice);
                stock.addProduct(readProduct);
            }
            
            inFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Erro ao criar ou encontrar o arquivo!");
            return new Stock();
        }
        catch(IOException e)
        {
            System.out.println("Erro de entrada e saída!");
            e.printStackTrace();
        }
        
        return stock;
    }

    private String readString(RandomAccessFile file, int size)
    {
        char string[] = new char[size];
        
        try
        {
            for(int i=0; i<size; i++)
            {
                string[i] = file.readChar();
            }        
        }
        catch(IOException e)
        {
            System.out.println("Erro de entrada e saída!");
            e.printStackTrace();
        }
        
        return(new String(string).replace('\0', ' '));
    }
    
}
