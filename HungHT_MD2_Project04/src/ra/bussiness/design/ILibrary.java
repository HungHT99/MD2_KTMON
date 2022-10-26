package ra.bussiness.design;

import java.util.List;
import java.util.Scanner;

public interface ILibrary<T,E> {
    boolean create(T t);
    boolean update(T t);
    boolean delete(E id);
    List<T> findAll();
     void displayData();
    T inputData (Scanner sc);
    List<T> readformFile();
    boolean writeToFile(List<T> list);
}
