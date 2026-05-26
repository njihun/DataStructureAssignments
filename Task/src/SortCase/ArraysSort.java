package SortCase;

import java.util.Arrays;

public class ArraysSort implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        Arrays.sort(array);
    }
}
