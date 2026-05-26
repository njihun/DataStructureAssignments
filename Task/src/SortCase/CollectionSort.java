package SortCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionSort implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        List<Comparable> values = Arrays.asList(array);
        Collections.sort(values);
    }
}
