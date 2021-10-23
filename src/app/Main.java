package app;

import java.util.InputMismatchException;
import java.util.Scanner;
import persistence.CSVTextPersistence;
import persistence.InterfaceIO;
import persistence.RandomAccessPersistence;
import persistence.SequencialBinaryPersistence;
import persistence.SerializablePersistence;
import persistence.TextPersistence;
import search.IterativeBinarySearch;
import search.RecursiveBinarySearch;
import search.Search;
import search.SequencialSearch;
import sort.BubbleSort;
import sort.InsertionSort;
import sort.QuickSort;
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
        Search searchMethod;
        String productName;
        int productPosition;
        
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
            System.out.println("11 - Incluir\n12 - Listar"
                    + "\n21 - Ordenar (Bolha)\n22 - Ordenar (Inserção)\n23 - Ordenar (Seleção)\n24 - Orderner (Quick)"
                    + "\n31 - Busca (Sequencial)\n32 - Busca (Binaria Recursiva)\n33 - Busca (Binaria Iterativa)"
                    + "\n0 - Finalizar\nEntre com a sua opção:");
            menuOption = input.nextInt();
            System.out.println("#################################");
            
            if(menuOption == 11)
            {
                Product newProduct = newProduct();
                stock.addProduct(newProduct);
            }
            if(menuOption == 12)
            {
                productList(stock);
            }
            if(menuOption == 21)
            {
                sortMethod = new BubbleSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
            if(menuOption == 22)
            {
                sortMethod = new InsertionSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
            if(menuOption == 23)
            {
                sortMethod = new SelectionSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
            if(menuOption == 24)
            {
                sortMethod = new QuickSort();
                stock = sortMethod.sort(stock);
                System.out.println("Ordenado com sucesso!");
            }
            if(menuOption == 31)
            {
                searchMethod = new SequencialSearch();
                System.out.println("\nEntre com o nome do produto a ser buscado.");
                productName = input.next();
                productPosition = searchMethod.search(stock, productName);
                
                if(productPosition == -1)
                {
                    System.out.println("Produto não encontrado!");
                }
                else
                {
                    System.out.println("Produto encontrado na posição: " + ++productPosition);
                }
            }
            if(menuOption == 32)
            {
                searchMethod = new RecursiveBinarySearch();
                System.out.println("\nEntre com o nome do produto a ser buscado.");
                productName = input.next();
                productPosition = searchMethod.search(stock, productName);
                
                if(productPosition == -1)
                {
                    System.out.println("Produto não encontrado!");
                }
                else
                {
                    System.out.println("Produto encontrado na posição: " + ++productPosition);
                }
            }
            if(menuOption == 33)
            {
                searchMethod = new IterativeBinarySearch();
                System.out.println("\nEntre com o nome do produto a ser buscado.");
                productName = input.next();
                productPosition = searchMethod.search(stock, productName);
                
                if(productPosition == -1)
                {
                    System.out.println("Produto não encontrado!");
                }
                else
                {
                    System.out.println("Produto encontrado na posição: " + ++productPosition);
                }
            }
        }
        while(menuOption != 0);
            
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
