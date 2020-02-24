import java.util.ArrayList;
import java.util.Collections;

public class Library implements Comparable<Library>{
    private int numberOfBooks;
    private int daysForSync;
    private int booksPerDay;
    private int id;
    private double value;
    private ArrayList<Book> books;

    public Library() {
    }

    public Library(int numberOfBooks, int daysForSync, int booksPerDay, int id, ArrayList<Book> books) {
        this.numberOfBooks = numberOfBooks;
        this.daysForSync = daysForSync;
        this.booksPerDay = booksPerDay;
        this.id = id;
        this.books = books;
    }
    
    int[] actScores(int[] bookScores) {
        for (int i = 0; i < numberOfBooks; i++) {
            bookScores[i] = 0;
        }
        return bookScores;
    }
    
    void sortBooks(int[] bookScores) {
        books.forEach((book) -> {
            book.setValue(bookScores[book.getId()]);
        });
        Collections.sort(books);
    }
    
    public void calcValue(int[] bookScores) {
        value = 0;
        books.forEach((book) -> {
            value += bookScores[book.getId()];
        });
        value = value/daysForSync;
        value = daysForSync;
    }
    
    
    public String getBooksAsString() {
        String toRet = "";
        for (Book book : books) {
            toRet += book.getId() + " ";
        }
        return toRet;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public int getDaysForSync() {
        return daysForSync;
    }

    public int getBooksPerDay() {
        return booksPerDay;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }    
        
    @Override
    public int compareTo(Library o) {
        int toRet = 0;
        if (this.value < o.getValue()){
            toRet = -1;
        }else{
            if(this.value > o.getValue()){
                toRet = 1;
            }
        }
        return toRet;
    }
}
