package app;

import java.util.InputMismatchException;
import java.util.Scanner;
import persistence.CSVTextPersistence;
import persistence.InterfaceIO;
import persistence.RandomAccessPersistence;
import persistence.SequencialBinaryPersistence;
import persistence.SerializablePersistence;
import persistence.TextPersistence;
import sort.BubbleSort;
import sort.InsertionSort;
import sort.SelectionSort;
import sort.Sort;

public class Main 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String fileName = new String();
        int menuOption;
        InterfaceIO dataFile;
        Sort sortMethod;
        
        System.out.println("Programa simulando um controle simples de estoque em arquivo.");
        System.out.print("Entre com o nome do arquivo a ser trabalhado (<enter> para default):");
        
        fileName = input.nextLine();
        if(fileName.isEmpty())
        {
            fileName = "data.txt";
        }
        
        System.out.println("********* Tipos de arquivo *********");
	System.out.println("1 - Binário\n2 - Texto\n3 - Texto CSV\n4 - Serializado\n5 - Acesso Randomico");
	System.out.print("Entre com o tipo do arquivo desejado:");
        
        int fileType = input.nextInt();
        if(fileType == 1)
        {
            dataFile = new SequencialBinaryPersistence();
        }
        else if(fileType == 2)
        {
            dataFile = new TextPersistence();
        }
        else if(fileType == 3)
        {
            dataFile = new CSVTextPersistence();
        }
        else if(fileType == 4)
        {
            dataFile = new SerializablePersistence();
        }
        else // melhorar a dinamica desse 'if else'
        {
            dataFile = new RandomAccessPersistence();
        }
        
        Stock stock = dataFile.readData(fileName);
        do
        {
            System.out.println("\n#################################");
            System.out.println("1 - Incluir\n2 - Listar\n3 - Ordenar (Bolha)\n4 - Ordenar (Inserção)\n5 - Ordenar (Seleção)\n6 - Finalizar\nEntre com a sua opção:");
            menuOption = input.nextInt();
            System.out.println("#################################");
            
            if(menuOption == 1)
            {
                Product newProduct = newProduct();
                stock.addProduct(newProduct);
            }
            if(menuOption == 2)
            {
                productList(stock);
            }
            if(menuOption == 3)
            {
                sortMethod = new BubbleSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
            if(menuOption == 4)
            {
                sortMethod = new InsertionSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
            if(menuOption == 5)
            {
                sortMethod = new SelectionSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
        }
        while(menuOption < 6);
            
        dataFile.writeData(stock, fileName);
        System.out.println("Fim do programa.");
    }

    private static Product newProduct()
    {
        Scanner input = new Scanner(System.in);
        Product newProduct = new Product(" ", 0, 0);
        
        try
        {
            System.out.println("Entre com o nome do produto:");
            String productName = input.nextLine();
            
            System.out.println("Entre com a quantidade do produto:");
            int productAmount = input.nextInt();
            
            System.out.println("Entre com o preco do product:");
            double productPrice = input.nextDouble();
            
            newProduct = new Product(productName, productAmount, productPrice);
        }
        catch(InputMismatchException e)
        {
            System.out.println("Erro na entrada pelo teclado.");
        }
        
        return newProduct;
    }
    
    private static void productList(Stock stock)
    {
        System.out.println("Total de produtos no estoque:" + stock.getTotalProducts());
	System.out.println("Listagem dos produtos:");
        
	for (int indexProduct = 0; indexProduct < stock.getTotalProducts(); indexProduct++)
        {
		System.out.println("\n" + stock.getStock()[indexProduct].getName());
		System.out.println(stock.getStock()[indexProduct].getAmount());
		System.out.println(stock.getStock()[indexProduct].getPrice());
        }
    }
    
}
