package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class LibraryBookCard implements Serializable {
    private int libraryBookCardId;
    private String libraryBookCardName;
    private User user;
    private Date borrowDate;
    private Date returnDate;
    private Date actualReturnDate;
    private ArrayList<Book> bookArrayList=new ArrayList<>();
    private String libraryBookCardStatus;

    public LibraryBookCard() {
    }

    public LibraryBookCard(int libraryBookCardId, String libraryBookCardName, User user, Date borrowDate, Date returnDate, Date actualReturnDate, ArrayList<Book> bookArrayList, String libraryBookCardStatus) {
        this.libraryBookCardId = libraryBookCardId;
        this.libraryBookCardName = libraryBookCardName;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.actualReturnDate = actualReturnDate;
        this.bookArrayList = bookArrayList;
        this.libraryBookCardStatus = libraryBookCardStatus;
    }

    public int getLibraryBookCardId() {
        return libraryBookCardId;
    }

    public void setLibraryBookCardId(int libraryBookCardId) {
        this.libraryBookCardId = libraryBookCardId;
    }

    public String getLibraryBookCardName() {
        return libraryBookCardName;
    }

    public void setLibraryBookCardName(String libraryBookCardName) {
        this.libraryBookCardName = libraryBookCardName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    public String getLibraryBookCardStatus() {
        return libraryBookCardStatus;
    }

    public void setLibraryBookCardStatus(String libraryBookCardStatus) {
        this.libraryBookCardStatus = libraryBookCardStatus;
    }
}

