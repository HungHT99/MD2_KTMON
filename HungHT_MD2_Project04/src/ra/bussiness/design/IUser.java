package ra.bussiness.design;

public interface IUser<T, E> extends ILibrary<T, E> {
    T checkLogin (String name, String pass);
}
