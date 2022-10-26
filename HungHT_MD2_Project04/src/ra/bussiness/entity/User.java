package ra.bussiness.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int userId;
    private String userName;
    private int phonenumber;
    private String userEmail;
    private String userAdress;
    private Date libraryCardStartDay;
    private Date libraryCardEndDay;
    private boolean userStatus;
    private String userLogin;
    private String userPass;
    private String comfirmUserPass;
    private boolean permission;

    public User() {
    }

    public User(int userId, String userName, int phonenumber, String userEmail, String userAdress, Date libraryCardStartDay, Date libraryCardEndDay, boolean userStatus, String userLogin, String userPass, String comfirmUserPass, boolean permission) {
        this.userId = userId;
        this.userName = userName;
        this.phonenumber = phonenumber;
        this.userEmail = userEmail;
        this.userAdress = userAdress;
        this.libraryCardStartDay = libraryCardStartDay;
        this.libraryCardEndDay = libraryCardEndDay;
        this.userStatus = userStatus;
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.comfirmUserPass = comfirmUserPass;
        this.permission = permission;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public Date getLibraryCardStartDay() {
        return libraryCardStartDay;
    }

    public void setLibraryCardStartDay(Date libraryCardStartDay) {
        this.libraryCardStartDay = libraryCardStartDay;
    }

    public Date getLibraryCardEndDay() {
        return libraryCardEndDay;
    }

    public void setLibraryCardEndDay(Date libraryCardEndDay) {
        this.libraryCardEndDay = libraryCardEndDay;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getComfirmUserPass() {
        return comfirmUserPass;
    }

    public void setComfirmUserPass(String comfirmUserPass) {
        this.comfirmUserPass = comfirmUserPass;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
