package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {
    private String bookId;
    private String bookName;
    private int bookquantity;
    private Catalog catalog;
    private ArrayList<String> listAuthor = new ArrayList<>();
    private String bookStates;
    private String bookStatus;

    public Book() {
    }

    public Book(String bookId, String bookName, int bookquantity, Catalog catalog, ArrayList<String> listAuthor, String bookStates, String bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookquantity = bookquantity;
        this.catalog = catalog;
        this.listAuthor = listAuthor;
        this.bookStates = bookStates;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookquantity() {
        return bookquantity;
    }

    public void setBookquantity(int bookquantity) {
        this.bookquantity = bookquantity;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ArrayList<String> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(ArrayList<String> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public String getBookStates() {
        return bookStates;
    }

    public void setBookStates(String bookStates) {
        this.bookStates = bookStates;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }
}

