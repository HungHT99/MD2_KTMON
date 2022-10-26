package ra.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile<T> {
    public List<T> readformFile(String path){
        List<T> tList = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            file = new File(path);
            if (file.exists()){
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                tList = (List<T>) ois.readObject();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if ( fis != null){
                    fis.close();
                }
                if (ois != null){
                    ois.close();
                }
            }catch (Exception ex1){
                ex1.printStackTrace();
            }
        }
        return tList;
    }
    public  boolean writeToFile(List<T> litst, String path) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean check = true;
        try {
            file = new File(path);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(litst);
        } catch (Exception ex) {
            check = false;
            ex.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (fos != null) {
                    oos.close();
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return check;
    }
}
