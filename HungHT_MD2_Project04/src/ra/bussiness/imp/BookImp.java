package ra.bussiness.imp;

import ra.bussiness.design.ILibrary;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.LibraryBookCard;
import ra.config.CheckValidate;
import ra.data.ReadAndWriteFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.config.CheckValidate.*;
import static ra.config.Message.*;
import static ra.data.ConstantRegexAndUrl.*;

public class BookImp implements ILibrary<Book, String> {

    public static ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
    @Override
    public boolean create(Book book) {
        List<Book> bookList = readformFile();
        if (bookList == null){
            bookList = new ArrayList<>();
        }
        bookList.add(book);
        boolean result = writeToFile(bookList);
        return result;
    }

    @Override
    public boolean update(Book book) {
        Scanner sc = new Scanner(System.in);
        List<Book> bookList = readformFile();
        if (bookList == null){
            return false;
        }else {
            boolean check = false;
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getBookId().equals(book.getBookId())){
                    boolean checkname = false;
                    for ( Book book1: bookList) {
                        if (book1.getBookName().equals(book.getBookName()) && !book1.getBookId().equals(book.getBookId())){
                            checkname = true;
                            break;
                        }
                    }
                    if (checkname) {
                        System.out.println("Ten da ton tai voi ID khac. \n" +
                        "1. Su dung ten ban dau.      2.Nhap ten khac");
                        int choice = CheckValidate.choiceNumber(sc, 1,2);
                        switch (choice){
                            case 1:
                                book.setBookName(bookList.get(i).getBookName());
                                break;
                            case 2:
                                System.out.println(INPUTNAME);
                                book.setBookName(checkbookName(sc));
                                break;
                        }
                    }
                    bookList.set(i, book);
                    check = true;
                    break;
                }
            }
            LibraryBookCardImp lbCardImp = new LibraryBookCardImp();
            List<LibraryBookCard>  libraryBookCardList = lbCardImp.readformFile();
            for (LibraryBookCard lbCard : libraryBookCardList){
                for (int i = 0; i < lbCard.getBookArrayList().size(); i++) {
                    if (lbCard.getBookArrayList().get(i).getBookId().equals(book.getBookId()) ){
                        lbCard.getBookArrayList().set(i, book);
                    }
                }
            }
            lbCardImp.writeToFile(libraryBookCardList);
            boolean result = writeToFile(bookList);
            if (check && result){
                return true;
            }else {
                return false;
            }
        }

    }

    @Override
    public boolean delete(String id) {
        List<Book> bookList = readformFile();
        if (bookList == null){
            bookList = new ArrayList<>();
        }
        boolean check = false;
        for (Book book: bookList) {
            if (book.getBookId().equals(id)){
                book.setBookStatus(STATUS3);
                check = true;
                break;
            }
        }
        boolean result = writeToFile(bookList);
        if (result && check){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<Book> findAll() {
        return readformFile();
    }

    @Override
    public void displayData() {
        List<Book> bookList = readformFile();
        if (bookList == null){
            System.out.println(ERRORNULL);
        }else {
            for (Book book: bookList) {
                System.out.printf("1.Ma Sach: %-5s   2.Ten Sach: %-20s    3. Danh Muc: %-30s \n",book.getBookId(),book.getBookId(),book.getCatalog().getCatalogName());
                System.out.printf("4.So luong: %-4d    5.Tinh Trang: %-15s      6.Trang thai:%-16s   \n",book.getBookquantity(),book.getBookStates(),book.getBookStatus());
                for (String str: book.getListAuthor()) {
                    System.out.printf("   7.Tac gia:%-90s",book.getListAuthor().toString());
                }
            }
        }
    }

    @Override
    public Book inputData(Scanner sc) {
        List<Book> bookList = readformFile();
        if (bookList == null){
            bookList = new ArrayList<>();
        }
        Book book = new Book();
        do {
            System.out.println(INPUTIDBOOK);
            book.setBookId(CheckValidate.strValidate(sc,REGEXBOOKID));
            boolean check = false;
            for (Book book1:bookList) {
                if (book1.getBookId().equals(book.getBookId())){
                    check = true;
                    break;
                }
            }
            if (check){
                System.out.println(IDERROR1);
            }else {
                break;
            }
        }while (true);
        System.out.println(INPUTNAME);
        book.setBookName(checkbookName(sc));
        System.out.println(BOOKQUANTITY);
        String number = strValidate(sc,REGEXQUANTITY);
        book.setBookquantity(Integer.parseInt(number));
        System.out.println(CATALOGFORBOOK);
        book.setCatalog(catalogForBook(sc));
        if (!book.getCatalog().isCatalogStatus()){
            book.setBookStatus(STATUS3);
        }
        book.setListAuthor((ArrayList<String>) addAuthor(sc));
        book.setBookStates(bookStates(sc));
        return book;
    }

    @Override
    public List<Book> readformFile() {
        List<Book> bookList = readAndWriteFile.readformFile(URL_BOOK);
        if (bookList == null){
            bookList =new ArrayList<>();
        }
        return bookList;
    }

    @Override
    public boolean writeToFile(List<Book> list) {
        return readAndWriteFile.writeToFile(list, URL_BOOK)  ;
    }
    public List<Book> findbyName(String name) {                     // 6. find book by name
        List<Book> bookList = readformFile();
        List<Book> bookListByName = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getBookName().contains(name)) {
                bookListByName.add(book);
            }
        }
        return bookListByName;
    }
}
