import java.util.Scanner;
import java.util.ArrayList;

class Book{
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
    public int getId() {return id;}
    public String getTitle() {return title;}
    public boolean getStatus() {return isIssued;}
    void display(){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        if (isIssued){
            System.out.println("Status: Issued");
        }
        else{
            System.out.println("Status: Available");
        }
    }
    void issueBook(){
        isIssued = true;
    }
    void returnBook(){
        isIssued = false;
    }
}

class User{
    private int id;
    private String name;
    ArrayList<Book> issuedBooks = new ArrayList<>();

    User(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    void display(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.print("Issued books: ");
        for (Book b : issuedBooks){
            System.out.print(b.getTitle() + ", ");
        }
    }
    void issueBook(Book book){
        issuedBooks.add(book);
    }
    void returnBook(Book book){
        issuedBooks.remove(book);
    }
}

class Library{
    static Scanner scanner = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    void addBook(){
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ttile: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added to the library.");
    }

    void addUser(){
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        users.add(new User(id, name));
    }

    void issueBook(){
        boolean found = false;
        System.out.print("Enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
        for (Book b : books){
            if (bookID == b.getId()){
                b.issueBook();
                for (User u : users){
                    if (userID == u.getId()){
                        u.issueBook(b);
                        found = true;
                    }
                }
            }
        }
        if (!found) {System.out.print("There is no book with such ID.");}
    }
    void returnBook(){
        boolean found = false;
        System.out.print("Enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
        for (Book b : books){
            if (bookID == b.getId()){
                b.returnBook();
                for (User u : users){
                    if (userID == u.getId()){
                        u.returnBook(b);
                        found = true;
                    }
                }
            }
        }
        if (!found) {System.out.print("There is no book with such ID.");}
    }
    void displayAllBooks(){
        for (Book b : books){
            b.display();
        }
    }
    void displayAllUsers(){
        for (User u : users){
            u.display();
        }
    }
}

public class Day_3 {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int answer;
        do{
            System.out.println("\n\nEnter numbers 1-5 for the following options.");
            System.out.println("1. Add a book to the library");
            System.out.println("2. Add a user");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Display all books");
            System.out.println("6. Display all users");
            System.out.print("7. Exit\n");
            answer = scanner.nextInt();
            System.out.println();
            switch(answer){
                case 1: library.addBook(); break;
                case 2: library.addUser(); break;
                case 3: library.issueBook(); break;
                case 4: library.returnBook(); break;
                case 5: library.displayAllBooks(); break;
                case 6: library.displayAllUsers(); break;
                default: System.out.println("This is not a valid option.");
            }
        }while(answer != 7);
    }
}