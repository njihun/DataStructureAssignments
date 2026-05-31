package Collections;

public interface IMyList<T extends Comparable<T>> {
    T getValue(int index);

    int getIndex(T value);

    void clear();

    void resize();

    void append(T value);

    void insert(int index, T value);

    void remove(int index);

    boolean isEmpty();

    boolean isContain(T value);
}
