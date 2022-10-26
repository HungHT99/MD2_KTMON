package ra.presentation;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.LibraryBookCard;
import ra.bussiness.imp.LibraryBookCardImp;
import ra.config.CheckValidate;
import ra.config.Message;
import ra.data.ConstantRegexAndUrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class LibraryBookCardManagement {
    public static LibraryBookCardImp libraryBookCardImp = new LibraryBookCardImp();


    public static void displayLibraryBookCard(Scanner sc){
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
                    showListLBC(sc);
                    break;
                case 2:
                    addListLBC(sc);
                    break;
                case 3:
                    updateListLBC(sc);
                    break;
                case 4:
                    deleteListLBC(sc);
                    break;
                case 5:
                    searchByNameLBC(sc);
                    break;
                case 6:
                    checkout=false;
                    break;
            }
        }while (checkout);
    }
    public static void showListLBC(Scanner sc){
        System.out.println("Danh sach the thu vien");
        libraryBookCardImp.displayData();
        System.out.println("\n");
    }
    public static void addListLBC(Scanner sc){
        System.out.println("Them the thu vien");
        System.out.println("Nhap so luong muon them");
        int number = Integer.parseInt(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY));
        for (int i = 0; i < number; i++) {
            LibraryBookCard libraryBookCard = new LibraryBookCard();
            System.out.printf("The thu vien thu %d \n",(i+1));
            libraryBookCard = libraryBookCardImp.inputData(sc);
            boolean check =libraryBookCardImp.create(libraryBookCard);
            CheckValidate.soutMess(check);
        }
    }
    public static void updateListLBC(Scanner sc){
        List<LibraryBookCard> libraryBookCardList = libraryBookCardImp.readformFile();
        LibraryBookCard libraryBookCard = new LibraryBookCard();
        System.out.println("Cap nhat the thu vien");
        System.out.println("Nhap vao ma cua muc muon cap nhat");
        libraryBookCard.setLibraryBookCardId(Integer.parseInt(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY)));
        System.out.println(Message.ADDBOOKTOCARD);
        libraryBookCard.setBookArrayList(CheckValidate.bookListCard(sc));
        System.out.println(Message.DAYRETURNBOOK);
        libraryBookCard.setReturnDate(CheckValidate.dateValidate(sc));
        boolean result = libraryBookCardImp.update(libraryBookCard);
        CheckValidate.soutMess(result);
    }

    public static void deleteListLBC(Scanner sc){
        System.out.println("Xoa the thu vien");
        System.out.println("Nhap ma the muon xoa");
        String cardID = CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY);
        boolean check = libraryBookCardImp.delete(cardID);
        CheckValidate.soutMess(check);
    }

    public static void searchByNameLBC(Scanner sc){
        System.out.println("Tim kiem theo ma phieu");
        System.out.println("Nhap vao ten muon tim");
        String searchName = CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXNAME);
        List<LibraryBookCard> libraryBookCardList = libraryBookCardImp.findbyName(searchName);
        if (libraryBookCardList == null){
            System.out.println(Message.ERRORNULL);
        }else {
            for (LibraryBookCard libraryBookCard:libraryBookCardList) {
                String actReturnDate = "Chua tra";
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                if (libraryBookCard.getActualReturnDate() !=null){
                    actReturnDate = df.format(libraryBookCard.getActualReturnDate());
                }
                String brDay = df.format(libraryBookCard.getBorrowDate());
                String rtDay = df.format(libraryBookCard.getReturnDate());
                System.out.printf("   1.MÃ THẺ: %-5d    2.TÊN THẺ: %-18s      3.NGƯỜI MƯỢN: %-20s      4.TRẠNG THÁI: %-10s \n" +
                                  " 5.NGÀY MƯỢN: %-10s                        6.NGÀY TRẢ: %-10s        7.NGÀY TRẢ THỰC TẾ: %-10s \n",
                        libraryBookCard.getLibraryBookCardId(), libraryBookCard.getLibraryBookCardName(), libraryBookCard.getUser().getUserName(), libraryBookCard.getLibraryBookCardStatus(), brDay, rtDay, actReturnDate);
                for (Book book: libraryBookCard.getBookArrayList()) {
                    System.out.printf("|   8.DANH SÁCH SÁCH MƯỢN: %-80s                |\n", book.getBookName());
                }
            }
        }
    }
}
