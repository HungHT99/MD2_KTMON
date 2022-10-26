package ra.presentation;

import ra.bussiness.entity.User;
import ra.bussiness.imp.BookImp;
import ra.bussiness.imp.UserImp;
import ra.config.CheckValidate;
import ra.config.Message;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class UserManagement {
    public static UserImp userImp = new UserImp();

    public static void displayUser(Scanner sc) {
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
                showListUser(sc);
                break;
            case 2:
                addUser(sc);
                break;
            case 3:
                updateUser(sc);
                break;
            case 4:
                deleteUser(sc);
                break;
            case 5:
                searchByName(sc);
                break;
            case 6:
                checkout=false;
                break;
        }
    }while (checkout);
    }
    public static void showListUser(Scanner sc){
        System.out.println("Danh sach nguoi dung");
        userImp.displayData();
        System.out.println("\n");
    }
    public static void addUser(Scanner sc){
        User user= userImp.inputData(sc);
        boolean check = userImp.create(user);
        if (check){
            System.out.println("Tao moi thanh cong");
        }else {
            System.out.println("That bai!!");
        }
    }
    public static void updateUser(Scanner sc){
        System.out.println("Nhap ID muon cap nhat");
        int userId = Integer.parseInt(sc.nextLine());
        List<User> userList = userImp.findAll();
        for ( User user:userList) {
            if (user.getUserId()==userId){
                System.out.println("Nhap ten: ");
                String name = sc.nextLine();
                if (name!=""||name.length()!=0) {
                    user.setUserName(name);
                }
                System.out.println("Nhap dia chi: ");
                String address = sc.nextLine();
                if (address!=""||address.length()!=0) {

                    user.setUserAdress(address);
                }
                System.out.println("Nhap vao email: ");
                String email = sc.nextLine();
                if (email!=""||email.length()!=0) {
                    user.setUserEmail(sc.nextLine());
                }
                System.out.println("Chon trang thai: ");
                System.out.println("1. Hoat dong");
                System.out.println("2. Khong hoat dong");
                System.out.print("Lua chon cua ban: ");
                int choice = Integer.parseInt(sc.nextLine());
                boolean status = false;
                if (choice==1){
                    status = true;
                }
                user.setUserStatus(status);
                userImp.update(user);
                break;
            }
        }
    }
    public static void deleteUser(Scanner sc){
        System.out.println(Message.DELETEID);
        String userId = sc.nextLine();
        boolean checkexit = userImp.checkUserId(Integer.parseInt(userId));
        if (checkexit) {
            boolean check = userImp.delete(userId);
            if (check){
                System.out.println(Message.DELETESUCCESS);
            }else {
                System.out.println(Message.DELETEFAIL);
            }
        }
    }
    public static void searchByName(Scanner sc){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhap ten muon tim kiem");
        String strUser = sc.nextLine();
        List<User> userList = userImp.findbyName(strUser);
        if (userList.size()==0){
            System.out.println("Khong ton tai");
        }else {
            String userStatus;
            System.out.printf("%-5s%-20s%-15s%-40s%-30s%-20s%-20s%-15s\n", "ID", "Ten nguoi dung", "So dien thoai", "Email", "Dia chi", "Ngay bat dau", "Ngay het han", "Trang thai");
            for (User user : userList){
                userStatus = user.isUserStatus() ? "Hoat dong" : "Khong hoat dong";
                System.out.printf("%-5s%-20s%-15s%-40s%-30s%-20s%-20s%-15s\n",user.getUserId(),user.getUserName(),user.getPhonenumber(),user.getUserEmail(),
                        user.getUserAdress(),df.format(user.getLibraryCardStartDay()),df.format(user.getLibraryCardEndDay()),userStatus);
            }
        }
    }
}
