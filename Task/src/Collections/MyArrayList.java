package Collections;

public class MyArrayList<T extends Comparable<T>> implements IMyList<T> {
    private Object[] array;
    private int count;

    public MyArrayList() {
        array = new Object[10];
        count = 0;
    }

    public MyArrayList(int capacity) {
        array = new Object[capacity];
        count = 0;
    }

    @Override
    public T getValue(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public int getIndex(T value) {
        for (int index = 0; index < count; index++) {
            if (array[index].equals(value)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        array = new Object[10];
        count = 0;
    }

    @Override
    public void resize() {
        Object[] newArray = new Object[array.length * 2];
        for (int index = 0; index < count; index++) {
            newArray[index] = array[index];
        }
        array = newArray;
    }

    @Override
    public void append(T value) {
        if (count == array.length) {
            resize();
        }

        array[count] = value;
        count++;
    }

    @Override
    public void insert(int index, T value) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (count == array.length) {
            resize();
        }

        for (int moveIndex = count; moveIndex > index; moveIndex--) {
            array[moveIndex] = array[moveIndex - 1];
        }

        array[index] = value;
        count++;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);

        for (int moveIndex = index; moveIndex < count - 1; moveIndex++) {
            array[moveIndex] = array[moveIndex + 1];
        }

        array[count - 1] = null;
        count--;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isContain(T value) {
        return getIndex(value) != -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
}
