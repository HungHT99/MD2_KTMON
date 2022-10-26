package ra.bussiness.design;

import java.util.List;

public interface ICatalog<T,E> extends ILibrary<T, E>  {
    List<T> sortByName() ;

}
