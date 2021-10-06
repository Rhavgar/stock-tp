package app;

public class Stock
{
    private Product products[] = new Product[100];
    private int totalProducts = 0;

   public void addProduct(Product newProduct)
   {
       this.products[totalProducts] = newProduct;
       this.totalProducts++;
   }
   
   public void addProducts(Product batch[], int total)
   {
       this.products = batch;
       this.totalProducts = total;
   }
   
   public Product getProduct(int indexProduct)
   {
       return this.products[indexProduct];
   }
   
   public int getTotalProducts()
   {
       return totalProducts;
   }
   
   public Product[] getStock()
   {
       return products;
   }
   
}
