package ra.bussiness.imp;

import ra.bussiness.design.IUser;
import ra.bussiness.entity.User;
import ra.config.CheckValidate;
import ra.config.Message;
import ra.data.ConstantRegexAndUrl;
import ra.data.ReadAndWriteFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserImp implements IUser<User, String> {
    public static ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();

    @Override
    public boolean create(User user) {
        /*\
        1. Doc du lieu tu file chua thong tin cac user --> listUser
        2. Neu listUser = null --> khoi tao listUser
        3. add newUSer vao listUser
        4. Ghi de ra file chua cac thong tin user
        */
        //1.
        List<User> userList = readformFile();
        //2.
        if (userList == null) {
            userList = new ArrayList<>();
        }
        //3.
        userList.add(user);
        //4.
        boolean result = writeToFile(userList);
        return result;
    }

    @Override
    public boolean update(User user) {
        /*
        1. Doc du lieu tu file chua user --> listUser
        2. Neu listUser = null --> Thong bao khong co user
            Neu listUser !=null
                updateUser khong ton tai trong listUser --> Thong bao khong ton tai user
                updateUser ton tai trong listUser --> de updateUser len user trong listUser
         3. Ghi de vao file chua thong tin user

        */
        //1.
        List<User> listUser = readformFile();
        //2.
        if (listUser != null) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getUserId()==user.getUserId()){
                    //tien hanh cap nhat
                    listUser.set(i,user);
                    //3.
                    return writeToFile(listUser);
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        /*
        1. Doc du lieu tu file --> listUser
        2. Duyet tung phan tu tim ra user can xoa theo id
        3. Tien hanh xoa
        4. Ghi ra file va tra ra ket qua
        */
        List<User> listUser = readformFile();
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserId()==Integer.parseInt(id)){
                listUser.remove(i);
                return writeToFile(listUser);
            }
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        /*
        1. Doc du lieu tu file
        */
        return readformFile();
    }

    @Override
    public void displayData() {
    LibraryBookCardImp libraryBookCardImp = new LibraryBookCardImp();
    List<User> userList = libraryBookCardImp.userStatus();
    if (userList == null){
        System.out.println(Message.ERRORNULL);
    }else {
        String status = Message.STATUS1;
        for (User user:userList) {
            if (!user.isUserStatus()){
                status=Message.STATUS3;
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String stDay = dateFormat.format(user.getLibraryCardStartDay());
            String endDay = dateFormat.format(user.getLibraryCardEndDay());

            System.out.printf("%d - %s - %d - %s - %s - %s - %s - %s", user.getUserId(), user.getUserName(), user.getPhonenumber(), user.getUserEmail(),
                    user.getUserAdress(), stDay, endDay, status);
        }
    }
    }

    @Override
    public User inputData(Scanner sc) {
        //1.Doc du lieu tu file
        List<User> userList = readformFile();
        if (userList == null){
            userList = new ArrayList<>();
        }
        User user = new User();
        user.setUserId(userList.size()+1);
        System.out.println("Nhap ho va ten:  ");
        user.setUserName(CheckValidate.strValidate(sc,ConstantRegexAndUrl.REGEXFULLNAME));
        do {
            System.out.println(Message.USERNAMELOGIN);
            user.setUserLogin(CheckValidate.strValidate(sc,ConstantRegexAndUrl.REGEXNAME));
            boolean check = false;
            for (User user1 : userList){
                if (user1.getUserLogin().equals(user.getUserLogin())){
                  check = true;
                  break;
                }
            }
            if (check){
                System.out.println(Message.NAMEERROR1);
            }else break;
        }while (true);
        System.out.println("Mat khau: ");
        do {
            user.setUserPass(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXPASS));
            System.out.print("Nhap lai mat khau: ");
            user.setComfirmUserPass(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXPASS));
            if (user.getUserPass().matches(user.getComfirmUserPass())) {
                break;
            } else {
                System.out.println("Khong trung khop, nhap lai: ");
            }
        } while (true);

        System.out.print(Message.PHONENUMBER);
        String phonenumber = CheckValidate.strValidate(sc,ConstantRegexAndUrl.REGEXPHONE);
        user.setPhonenumber(Integer.parseInt(phonenumber));
        System.out.print(Message.EMAIL);
        user.setUserEmail(CheckValidate.strValidate(sc, ConstantRegexAndUrl.REGEXEMAIL));
        ///thieu dia chi nua :(((((
        System.out.print(Message.ADDRESS);
        user.setUserAdress(sc.nextLine());
        System.out.print(Message.LBCARDSTART);
        user.setLibraryCardStartDay(CheckValidate.dateValidate(sc));
        Date date = user.getLibraryCardStartDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(Message.LBCARDEND);
        int choice1 = CheckValidate.choiceNumber(sc, 1, 3);
        switch (choice1) {
            case 1:
                calendar.roll(Calendar.MONTH, 2);
                user.setLibraryCardEndDay(calendar.getTime());
                break;
            case 2:
                calendar.roll(Calendar.MONTH, 4);
                user.setLibraryCardEndDay(calendar.getTime());
                break;
            case 3:
                calendar.roll(Calendar.MONTH, 6);
                user.setLibraryCardEndDay(calendar.getTime());
                break;
        }
        if (userList.size() == 0) {
            user.setPermission(true);
        } else {
            user.setPermission(false);
        }
        System.out.println(Message.INPUTSTATUS);
        System.out.println("1." + Message.STATUS1 + "       2." + Message.STATUS3);
        int choice = CheckValidate.choiceNumber(sc, 1, 2);
        switch (choice) {
            case 1:
                user.setUserStatus(true);
                break;
            case 2:
                user.setUserStatus(false);
                break;
        }
        return user;
    }

    @Override
    public List<User> readformFile() {
        List<User> listUser = readAndWriteFile.readformFile(ConstantRegexAndUrl.URL_USER);
        return listUser;
    }

    @Override
    public boolean writeToFile(List<User> list) {
        return readAndWriteFile.writeToFile(list, ConstantRegexAndUrl.URL_USER);
    }

    @Override
    public User checkLogin(String name, String pass) {
        List<User> listUser = readformFile();
        for (User user : listUser) {
            if (user.getUserLogin().equals(name) && user.getUserPass().equals(pass)) {
                return user;
            }
        }
        return null;
    }
    public List<User> findbyName(String name) {
        List<User> userList = readformFile();
        List<User> userList1 = new ArrayList<>();
        for (User user : userList) {
            if (user.getUserName().contains(name)) {
                userList1.add(user);
            }
        }
        return userList1;
    }
    public boolean checkUserId(Integer id) {
        List<User> userList = readformFile();
        boolean check = false;
        for (User us : userList) {
            if (us.getUserId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }
}
