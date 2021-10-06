package persistence;

import app.Product;
import app.Stock;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVTextPersistence implements InterfaceIO
{

    @Override
    public void writeData(Stock stock, String fileName)
    {
        FileWriter writeFile;
        BufferedWriter bufferedFile;
        
        try
        {
            writeFile = new FileWriter(fileName);
            bufferedFile = new BufferedWriter(writeFile);
            
            for(int indexProduct = 0; indexProduct < stock.getTotalProducts(); indexProduct++)
            {
                bufferedFile.write(stock.getProduct(indexProduct).getName() + ";");
                bufferedFile.write(stock.getProduct(indexProduct).getAmount() + ";");
                bufferedFile.write(stock.getProduct(indexProduct).getPrice() + ";\n");
            }
            
            bufferedFile.flush();
            writeFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Erro ao criar ou encontrar o arquivo!");
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println("Erro de entrada e saída!");
            e.printStackTrace();
        }
    }

    @Override
    public Stock readData(String fileName)
    {
        FileReader inFile = null;
        
        try
        {
            inFile = new FileReader(fileName);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Arquivo não encontrado, novo estoque criado!");
            return new Stock();
        }
        
        BufferedReader bufferedInFile = new BufferedReader(inFile);
        
        Stock stock = new Stock();
        String readLine = "";
        
        while(!fileEnd(readLine))
        {
            try
            {
                readLine = bufferedInFile.readLine();
            
                if(!fileEnd(readLine))
                {
                    Product readProduct = readProduct(readLine);
                    stock.addProduct(readProduct);
                }
           }
            catch(IOException e)
            {
                System.out.println("Erro de entrada e saída ao ler linha do arquivo!");
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

    private boolean fileEnd(String readLine)
    {
        return readLine == null;
    }

    private Product readProduct(String readLine)
    {
        String productName;
        int productAmount;
        double productPrice;
        String readLineVector[];
        int readLineVectorSize;
        
        readLineVector = readLine.split(";");
        readLineVectorSize = readLineVector.length;
        
        productName = readLineVector[readLineVectorSize - 3];
        productAmount = Integer.parseInt(readLineVector[readLineVectorSize - 2]);
        productPrice = Double.parseDouble(readLine.split(";")[readLineVectorSize - 1]);
        
        Product readProduct = new Product(productName, productAmount, productPrice);
        return readProduct;
    }
    
}
