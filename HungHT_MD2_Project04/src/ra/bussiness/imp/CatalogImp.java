package ra.bussiness.imp;

import ra.bussiness.design.ICatalog;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Catalog;
import ra.data.ReadAndWriteFile;

import java.util.*;

import static ra.config.CheckValidate.*;
import static ra.config.Message.*;
import static ra.data.ConstantRegexAndUrl.URL_CATALOG;

public class CatalogImp implements ICatalog<Catalog, String>, Comparator<Catalog> {

    public static ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
    @Override
    public List<Catalog> sortByName() {
        List<Catalog> catalogList = readformFile();
        if (catalogList == null){
            catalogList = new ArrayList<>();
        }
        Collections.sort(catalogList, new Comparator<Catalog>() {
            @Override
            public int compare(Catalog o1, Catalog o2) {
                return o1.getCatalogName().compareTo(o2.getCatalogName());
            }
        });
        return catalogList;
    }

    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> catalogList = readformFile();
        if (catalogList == null){
            catalogList = new ArrayList<>();
        }
        catalogList.add(catalog);
        boolean result = writeToFile(catalogList);
        return result;
    }

    @Override
    public boolean update(Catalog catalog) {
        Scanner sc = new Scanner(System.in);
        List<Catalog> catalogList = readformFile();
        if (catalogList == null){
            return false;
        }else {
            boolean check = false;
            for (int i = 0; i < catalogList.size(); i++) {
                if (catalogList.get(i).getCatalogId() == catalog.getCatalogId()){
                    boolean checkname = false;
                    for (Catalog cata : catalogList) {
                        if (cata.getCatalogName().equals(catalog.getCatalogName()) && cata.getCatalogId() != catalog.getCatalogId() ){
                            checkname = true;
                            break;
                        }
                    }
                    if (checkname){
                        System.out.println("Ten da ton tai \n"+
                                "1. Su dung ten cu     2. Nhap ten khac");
                        int choice = choiceNumber(sc, 1 ,2);
                        switch (choice){
                            case 1:
                                catalog.setCatalogName(catalogList.get(i).getCatalogName());
                                break;
                            case 2:
                                System.out.println(INPUTNAME);
                                catalog.setCatalogName(checkCatalogName(sc));
                                break;
                        }
                    }
                    catalogList.set(i, catalog);
                    check = true;
                    break;
                }
            }
            BookImp bookImp = new BookImp();
            List<Book> bookList = bookImp.readformFile();
            for (Book book: bookList) {
                if (book.getCatalog().getCatalogId()==catalog.getCatalogId()){
                    book.setCatalog(catalog);
                }
            }
            bookImp.writeToFile(bookList);
            boolean result = writeToFile(catalogList);
            if (check && result){
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public boolean delete(String name) {
        List<Catalog> catalogList = readformFile();
        boolean check = false;
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogName().equals(name)) {
                catalog.setCatalogStatus(!catalog.isCatalogStatus());
                check = true;
                break;
            }
        }
        boolean result = writeToFile(catalogList);
        if (result && check) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Catalog> findAll() {
        return readformFile();
    }

    @Override
    public void displayData() {
        List<Catalog> catalogList = sortByName();
        String status;
        if (catalogList == null) {
            System.out.println(ERRORNULL);
        } else {
            for (Catalog catalog : catalogList) {
                if (catalog.isCatalogStatus()) {
                    status = STATUS1;
                } else {
                    status = STATUS3;
                }
                System.out.printf("                                 |            %-10d      |                %-30s    |         %-20s            |\n", catalog.getCatalogId(), catalog.getCatalogName(), status);
                System.out.println("                                 +-------------------------------------------------------------------------------------------------------------------------+");

            }
        }
    }

    @Override
    public Catalog inputData(Scanner sc) {
        List<Catalog> catalogList = readformFile();
        if (catalogList == null){
            catalogList = new ArrayList<>();
        }
        Catalog catalog = new Catalog();
        catalog.setCatalogId(catalogList.size() + 1);
        System.out.println(INPUTNAME);
        catalog.setCatalogName(checkCatalogName(sc));
        System.out.println(INPUTSTATUS);
        boolean status = choiceBooleanStatus(sc);
        catalog.setCatalogStatus(status);
        return catalog;
    }

    @Override
    public List<Catalog> readformFile() {
        List<Catalog> catalogList = readAndWriteFile.readformFile(URL_CATALOG);
        if (catalogList == null){
            catalogList = new ArrayList<>();
        }
        return catalogList;
    }

    @Override
    public boolean writeToFile(List<Catalog> list) {
        return readAndWriteFile.writeToFile(list, URL_CATALOG);
    }

    @Override
    public int compare(Catalog o1, Catalog o2) {
        return 0;
    }
    public List<Catalog> findbyName(String name) {                  //7. find catalog by name
        List<Catalog> catalogList = readformFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        List<Catalog> catalogListByName = new ArrayList<>();
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogName().contains(name)) {
                catalogListByName.add(catalog);
            }
        }
        return catalogListByName;
    }

}
