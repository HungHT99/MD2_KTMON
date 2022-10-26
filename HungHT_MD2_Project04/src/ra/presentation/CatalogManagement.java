package ra.presentation;

import ra.bussiness.entity.Catalog;
import ra.bussiness.imp.CatalogImp;
import ra.config.CheckValidate;
import ra.config.Message;
import ra.data.ConstantRegexAndUrl;

import java.util.List;
import java.util.Scanner;

public class CatalogManagement {
    public static CatalogImp catalogImp = new CatalogImp();

    public static void main(String[] args) {

    }
    public static void displayCatalog(Scanner sc){
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
                    showListCata(sc);
                    break;
                case 2:
                    addCata(sc);
                    break;
                case 3:
                    updateCata(sc);
                    break;
                case 4:
                    deleteCata(sc);
                    break;
                case 5:
                    searchByNameCata(sc);
                    break;
                case 6:
                    checkout=false;
                    break;
            }
        }while (checkout);
    }
    public static void showListCata(Scanner sc){
        System.out.println("Danh sach");
        catalogImp.displayData();
        System.out.println("\n");
    }
    public static void addCata(Scanner sc){
        System.out.println("Them moi danh muc");
        System.out.println("Nhap so luong can them");
        int number = Integer.parseInt(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY));
        for (int i = 0; i < number; i++) {
            Catalog catalog = new Catalog();
            System.out.printf("Danh muc thu %d \n",(i+1));
            catalog = catalogImp.inputData(sc);
            boolean check = catalogImp.create(catalog);
            CheckValidate.soutMess(check);
        }
    }
    public static void updateCata(Scanner sc){
        List<Catalog> catalogList = catalogImp.readformFile();
        Catalog catalog = new Catalog();
        System.out.println("Cap nhat danh muc");
        System.out.println("Nhap vao ma danh muc muon cap nhat");
        catalog.setCatalogId(Integer.parseInt(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXQUANTITY)));
        System.out.println(Message.INPUTNAME);
        catalog.setCatalogName(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXFULLNAME));
        System.out.println(Message.INPUTSTATUS);
        catalog.setCatalogStatus(CheckValidate.choiceBooleanStatus(sc));
        boolean check = catalogImp.update(catalog);
        CheckValidate.soutMess(check);
    }
    public static void deleteCata(Scanner sc){
        System.out.println("Xoa/Thay doi danh muc");
        System.out.println("Nhap vao ten muon thay doi:");
        String catalogName = CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXNAME);
        boolean check = catalogImp.delete(catalogName);
        CheckValidate.soutMess(check);
    }
    public static void searchByNameCata(Scanner sc){
        System.out.println("Tim theo ten danh muc");
        System.out.println("Nhap vao ten muon tim");
        String catalogName = CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXFULLNAME);
        List<Catalog> catalogList = catalogImp.findbyName(catalogName);
        if (catalogList== null){
            System.out.printf(" Danh muc %s khong co trong danh sach", catalogName);
        }else {
            String status = Message.STATUS1;
            for (Catalog catalog:catalogList) {
                if (!catalog.isCatalogStatus()){
                    status = Message.STATUS3;
                }
                System.out.printf("1.Ma danh muc: %-10d                2. Ten danh muc: %-30s             3. Trang thai: %-20s            |\n", catalog.getCatalogId(), catalog.getCatalogName(), status);
            }
        }
    }

}
