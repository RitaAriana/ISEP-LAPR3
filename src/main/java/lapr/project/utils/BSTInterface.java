package lapr.project.utils;

/**
 *
 * @author 1201239 Francisco Redol
 * @param <E> Generic Class E
 */

public interface BSTInterface<E> {

    boolean isEmpty();
    void insert(E element);

    int size();
    int height();

    E smallestElement();
    Iterable<E> inOrder();

}

