package ra.presentation;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.User;
import ra.bussiness.imp.BookImp;
import ra.bussiness.imp.CatalogImp;
import ra.bussiness.imp.LibraryBookCardImp;
import ra.bussiness.imp.UserImp;
import ra.config.CheckValidate;

import java.util.List;
import java.util.Scanner;

public class LibraryManagement {
    public static LibraryBookCardImp libraryBookCardImp = new LibraryBookCardImp();
    public static CatalogImp catalogImp = new CatalogImp();
    public static UserImp userImp = new UserImp();
    public static BookImp bookImp = new BookImp();

    public static void main(String[] args) {
        List<Book> bookList = bookImp.readformFile();

        Scanner sc =new Scanner(System.in);
        do {
            System.out.println("*********TIEM SACH HAHANOBITA********");
            System.out.println("1.Dang nhap");
            System.out.println("2.Dang ky");
            System.out.println("3.Thoat");
            int choice = CheckValidate.choiceNumber(sc,1,3);
            switch (choice){
                case 1:
                    login(sc);
                    break;
                case 2:
                    register(sc);
                    break;
                case 3:
                    System.exit(0);
            }
        }while (true);
    }
    public static void login(Scanner sc){
        do {
            System.out.println("Dang nhap");
            System.out.print("Ten dang nhap: ");
            String userName = sc.nextLine();
            System.out.print("Mat khau: ");
            String passWord = sc.nextLine();
            User user = userImp.checkLogin(userName,passWord);
            if (user !=null){
                if (user.isPermission()){
                    displayMenu(sc);
                }
                break;
            }else {
                System.out.println("Dang nhap lai");
                int choice = CheckValidate.choiceNumber(sc,1,3);
                if (choice==2){
                    register(sc);
                } else if (choice==3) {
                    break;
                }
            }
        }while (true);
    }
    public static void register(Scanner sc){
        User user = new User();
        user = userImp.inputData(sc);
        boolean result = userImp.create(user);
        CheckValidate.soutMess(result);
    }
    public static void displayMenu(Scanner sc){
        boolean checkout = true;
        do {
            System.out.println("*********TIEM SACH HAHANOBITA********");
            System.out.println("1.Quan ly sach");
            System.out.println("2.Quan ly danh muc");
            System.out.println("3.Quan ly phieu");
            System.out.println("4.Quan ly nguoi dung");
            System.out.println("5.Thoat");
            int choice = CheckValidate.choiceNumber(sc,1,5);
            switch (choice){
                case 1:
                    BookManagement.displayBook(sc);
                    break;
                case 2:
                    CatalogManagement.displayCatalog(sc);
                    break;
                case 3:
                    LibraryBookCardManagement.displayLibraryBookCard(sc);
                    break;
                case 4:
                    UserManagement.displayUser(sc);
                    break;
                case 5:
                    checkout = false;
                    break;
            }
        }while (checkout);
    }
}
