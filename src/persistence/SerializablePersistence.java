package persistence;

import app.Product;
import app.Stock;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializablePersistence implements InterfaceIO
{

    @Override
    public void writeData(Stock stock, String fileName)
    {
        try
        {
            ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName));
            
            for(int indexProduct = 0; indexProduct < stock.getTotalProducts(); indexProduct++)
            {
                outFile.writeObject(stock.getProduct(indexProduct));
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

    @Override
    public Stock readData(String fileName)
    {
        ObjectInputStream inFile = null;
        try
        {
            inFile = new ObjectInputStream(new FileInputStream(fileName));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Arquivo não encontrado, novo estoque criado!");
            return new Stock();
        }
        catch (IOException e)
        {
            System.out.println("Erro de entrada e saída ao ler linha do arquivo!");
            e.printStackTrace();
        }
        
        boolean finalFile = false;
        Stock stock = new Stock();
        
        try
        {
            Product readProduct = null;
            
            while(finalFile == false)
            {
                readProduct = (Product) inFile.readObject();
                
                if(readProduct != null)
                {
                    stock.addProduct(readProduct);
                }
            }
        }
        catch(EOFException e)
        {
            finalFile = true;
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Classe não encontrada!");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println("Erro de entrada e saída ao ler linha do arquivo!");
            e.printStackTrace();
        }
        
        return stock;
    }
    
}
