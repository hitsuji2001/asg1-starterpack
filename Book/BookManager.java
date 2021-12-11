package Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class BookManager
{
    private ArrayList <Book> books;
    private File inputFile;
    private Scanner scanner;

    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public BookManager()
    {
        this.books = new ArrayList<>();
        this.inputFile = new File("resources/books.txt");
    }

    // Required Functions
    public ArrayList <Book> getBooks()
    {
        return this.books;
    }

    public void loadFromFile()
    {
        try {
            this.scanner = new Scanner(this.inputFile);
            
            while(this.scanner.hasNextLine())
            {
                Book book = this.getBookByLine(this.scanner);
                this.books.add(book);
            }
            scanner.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void printBooks(ArrayList <Book> books)
    {
        if(books.isEmpty()) System.out.println("(empty)");
        else {
            System.out.println(String.format("%-5s %-45s %-10s", "ID", "Name", "Price"));
            for(Book b : books) System.out.println(b);
        }
    }

    public boolean add(Book book)
    {
        if(this.hasThisBook(book)) return false;
        else {
            this.books.add(book);
            return true;
        }
    }

    public Book getBookById(int id)
    {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getID() == id) return this.books.get(i);
        }
        return null;
    }

    public void remove(Book book)
    {
        if(this.hasThisBook(book)) {
            this.books.remove(this.findBookIndex(book));
        }
        else return;
    }

    public void sortDescByPrice()
    {
        ArrayList <Book> list = this.books;
        Collections.sort(list);

        this.printBooks(list);
    }

    public List<Book> searchByName(String keyword)
    {
        List <Book> list = new ArrayList <Book>();

        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getName().contains(keyword)) {
                list.add(this.books.get(i));
            }
        }

        return list;
    }

    public void saveToFile() throws IOException
    {
        System.out.println("Saving to file..");

        this.fileWriter = new FileWriter(this.inputFile);
        this.printWriter = new PrintWriter(this.fileWriter);

        for(int i = 0; i < this.books.size(); i++) {
            this.printWriter.println(this.books.get(i));
        }

        this.fileWriter.close();
        this.printWriter.close();

        System.out.println("Bye!");
    }
    // End Of Required Functions

    // My Functions
    
    public void removeBookByID(int id)
    {
        this.books.remove(this.findBookWithID(id));
    }

    private Book getBookByLine(Scanner scanner)
    {
        Book book;
        String line = scanner.nextLine();
        String[] words = line.trim().split("\\s++");
        String bookName = "";

        for(int i = 1; i < words.length - 1; i++) bookName += words[i] + " ";

        book = new Book(Integer.parseInt(words[0]), bookName, Double.parseDouble(words[words.length - 1]));

        return book;
    }

    private boolean hasThisBook(Book book)
    {
        for(int i = 0; i < this.books.size(); i++) {
            if(book.getID() == this.books.get(i).getID()) return true;
        }
        return false;
    }

    private int findBookWithID(int id)
    {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getID() == id) return i;
        }
        return -1;
    }

    private int findBookIndex(Book book)
    {
        for (int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getID() == book.getID()) return i;
        }
        return -1;
    }

    public void editBook(int id, Book book)
    {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getID() == id) {
                this.books.set(i, book);
                break;
            }
        }
    }

    public boolean hasThisBookID(int id)
    {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getID() == id) return true;
        }
        return false;
    }
}
