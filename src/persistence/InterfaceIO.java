package persistence;

import app.Stock;

public interface InterfaceIO
{
    public void writeData(Stock stock, String fileName);
    public Stock readData(String fileName);
}
