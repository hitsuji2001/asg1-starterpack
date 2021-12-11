/*
 * @author hitsuji2001
 */

package Menu;

import java.util.Scanner;
import java.io.IOException;
import java.util.List;

import Book.BookManager;
import Book.Book;

public class Menu
{
    private static BookManager bookManager = new BookManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void printMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("1. list all books");
        System.out.println("2. add a new book");
        System.out.println("3. edit book");
        System.out.println("4. delete a book");
        System.out.println("5. search books by name");
        System.out.println("6. sort books descending by price");
        System.out.println();
        System.out.println("0. save & exit");
        System.out.println("-----------------------------------");
        System.out.print("Your option: ");
    }

    public static void handle()
    {
        Menu.printMenu();
        String option = scanner.nextLine();

        while(true) {
            switch(Integer.parseInt(option)) {
                case 0:
                    try {
                        bookManager.saveToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                case 1:
                    bookManager.printBooks(bookManager.getBooks());
                    break;
                case 2:
                    Menu.handleAddBook();
                    break;
                case 3:
                    Menu.handleEditBook();
                    break;
                case 4:
                    Menu.handleDeleteBook();
                    break;
                case 5:
                    Menu.handleSearchBookByName();
                    break;
                case 6:
                    bookManager.sortDescByPrice();
                    break;
                default:
                    System.out.println("Invalid option!");
                    System.out.println("-----------------------------------");
                    Menu.printMenu();
                    break;
            }
            Menu.printMenu();
            option = scanner.nextLine();
        }
    }

    public static void loadInfo()
    {
        Menu.bookManager.loadFromFile();
    }

    private static void handleAddBook()
    {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter book name: ");
        String name = scanner.nextLine();

        System.out.print("Enter book price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Book book = new Book(id, name, price);

        if(bookManager.add(book)) System.out.println("Added successfully");
        else System.out.println("Duplicated ID!");
    }

    private static void handleEditBook()
    {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if(!bookManager.hasThisBookID(id)) System.out.println("Invalid ID!");
        else {
            System.out.print("Enter book name: ");
            String name = scanner.nextLine();

            System.out.print("Enter book price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Book book = new Book(id, name, price);

            bookManager.editBook(id, book);

            System.out.println("Updated successfully.");
        }
    }
    
    private static void handleDeleteBook()
    {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if(!bookManager.hasThisBookID(id)) System.out.println("Invalid ID!");
        else {
            bookManager.removeBookByID(id);
            System.out.println("Deleted successfully.");
        }
    }
    
    private static void handleSearchBookByName()
    {
        System.out.print("Enter keyword: ");
        String keyWord = scanner.nextLine();
        List <Book> list = bookManager.searchByName(keyWord);

        if(list.isEmpty()) System.out.println("(empty)");
        else {
            System.out.println(String.format("%-5s %-45s %-10s", "ID", "Name", "Price"));
            for(Book b : list) System.out.println(b);
        }
    }
}
