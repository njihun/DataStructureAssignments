package Collection;

public interface IMyStack<T extends Comparable<T>> {
    T pop();

    void push(T data);

    T peek();

    boolean isEmpty();

    int count();

    void clear();

}
