package Book;

public class Book implements Comparable <Book>
{
    private int id;
    private String name;
    private double price;

    public Book(int id, String name, double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Required Functions
    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return String.format("%5d %-45s %10.2f", this.id, this.name, this.price);
    }
    // End Of Required Functions

    public int getID()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
    
    public double getPrice()
    {
        return this.price;
    }

    @Override
    public int compareTo(Book other)
    {
        return -Double.valueOf(this.getPrice()).compareTo(Double.valueOf(other.getPrice()));
    }
}
