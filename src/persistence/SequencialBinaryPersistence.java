package persistence;

import app.Product;
import app.Stock;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SequencialBinaryPersistence implements InterfaceIO
{

    @Override
    public void writeData(Stock stock, String fileName)
    {
        FileOutputStream outFile;
        DataOutputStream filteredOutFile;
        
        try
        {
            outFile = new FileOutputStream(fileName);
            filteredOutFile = new DataOutputStream(outFile);
            
            for(int indexProduct = 0; indexProduct < stock.getTotalProducts(); indexProduct++)
            {
                filteredOutFile.writeInt(stock.getProduct(indexProduct).getName().length());
                filteredOutFile.writeChars(stock.getProduct(indexProduct).getName());
                filteredOutFile.writeInt(stock.getProduct(indexProduct).getAmount());
                filteredOutFile.writeDouble(stock.getProduct(indexProduct).getPrice());
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
        boolean finalFile = false;
        Stock stock = new Stock();
        FileInputStream inFile;
        
        try
        {
            inFile = new FileInputStream(fileName);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Arquivo não encontrado, novo estoque criado!");
            return new Stock();
        }
        
        DataInputStream filteredInFile = new DataInputStream(inFile);
        
        while(finalFile == false)
        {
            String productName = "";
            int productAmount;
            double productPrice;
            
            try
            {
                int nameSize = filteredInFile.readInt();
                
                for(int indexChar = 0; indexChar < nameSize; indexChar++)
                {
                    productName = productName + filteredInFile.readChar();
                }
                
                productAmount = filteredInFile.readInt();
                productPrice = filteredInFile.readDouble();
                
                Product readProduct = new Product(productName, productAmount, productPrice);
                stock.addProduct(readProduct);
            }
            catch(EOFException e)
            {
                finalFile = true;
            }
            catch(IOException e)
            {
                System.out.println("Erro de entrada e saída!");
                e.printStackTrace();
            }
        }
        
        try
        {
            inFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Erro de entrada e saída ao fechar o arquivo!");
            e.printStackTrace();
        }
        
        return stock;
    }
    
}
