package ra.presentation;

import ra.bussiness.entity.Book;
import ra.bussiness.imp.BookImp;
import ra.config.CheckValidate;
import ra.config.Message;
import ra.data.ConstantRegexAndUrl;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static BookImp bookImp = new BookImp();

    public static void main(String[] args) {

    }
    public static void displayBook(Scanner sc){
        boolean checkout = true;
        do {
            System.out.println("*********TIEM SACH HAHANOBITA********");
            System.out.println("1.Danh sach");
            System.out.println("2.Them moi");
            System.out.println("3.Cap nhat");
            System.out.println("4.Xoa");
            System.out.println("5.Tim theo ten");
            System.out.println("6.Thoat");
            int choice = CheckValidate.choiceNumber(sc,1,6);
            switch (choice){
                case 1:
                    showListBook(sc);
                    break;
                case 2:
                    addBook(sc);
                    break;
                case 3:
                    updateBook(sc);
                    break;
                case 4:
                    deleteBook(sc);
                    break;
                case 5:
                    searchByNameBook(sc);
                    break;
                case 6:
                    checkout = false;
                    break;
            }
        }while (checkout);
    }
    public static void showListBook(Scanner sc){
        System.out.println("Danh sach");
        bookImp.displayData();
        System.out.println("\n");
    }
    public static void addBook(Scanner sc){
        System.out.println("Them moi sach");
        System.out.println("Nhap so luong can them");
        int number = Integer.parseInt(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY));
        for (int i = 0; i < number; i++) {
            Book book = new Book();
            System.out.printf("Sach thu %d \n",(i+1));
            book = bookImp.inputData(sc);
            boolean check = bookImp.create(book);
            CheckValidate.soutMess(check);
        }
    }
    public static void updateBook(Scanner sc){
        List<Book> bookList = bookImp.readformFile();
        Book book = new Book();
        System.out.println("Cap nhat sach");
        System.out.println("Nhap vao ID muon cap nhat");
        book.setBookId(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXBOOKID));
        System.out.println(Message.INPUTNAME);
        book.setBookName(CheckValidate.strValidate(sc,ConstantRegexAndUrl.REGEXFULLNAME));
        System.out.println(Message.BOOKQUANTITY);
        String number = CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY);
        book.setBookquantity(Integer.parseInt(number));
        System.out.println(Message.CATALOGFORBOOK);
        book.setCatalog(CheckValidate.catalogForBook(sc));
        book.setListAuthor((ArrayList<String>) CheckValidate.addAuthor(sc));
        book.setBookStates(CheckValidate.bookStates(sc));
        boolean check = bookImp.update(book);
        CheckValidate.soutMess(check);
    }
    public static void deleteBook(Scanner sc){
        System.out.println("Xoa sach");
        System.out.println("Nhap vao ID sach muon xoa");
        String bookId = CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXBOOKID);
        boolean check = bookImp.delete(bookId);
        CheckValidate.soutMess(check);
    }
    public static void searchByNameBook(Scanner sc){
        System.out.println("Nhap ten sach ma ban muon tim");
        String bookName = sc.nextLine();
        System.out.println("Tim sach theo ten");
        List<Book> bookList = bookImp.findbyName(bookName);
        if (bookList==null){
            System.out.printf("Khong ton tai %s trong ds", bookName);
        }else {
            for (Book book: bookList) {
                System.out.printf("1.MÃ SÁCH: %-6S           2.TÊN SÁCH: %-25S        3. DANH MỤC: %-20S\n", book.getBookId(), book.getBookName(), book.getCatalog().getCatalogName());
                System.out.printf("4.SỐ LƯỢNG: %-4d          5.TÌNH TRẠNG: %-13s      6. TRẠNG THÁI: %-16s\n", book.getBookquantity(), book.getBookStates(), book.getBookStatus());
                System.out.printf("7.TÁC GIẢ: %-100s",book.getListAuthor().toString());
            }
        }
    }


}
