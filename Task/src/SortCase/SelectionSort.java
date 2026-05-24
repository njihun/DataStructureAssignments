package SortCase;

public class SelectionSort implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        int size = array.length;

        for (int index = 0; index < size; index++){
            int minValueIndex = index;

            for (int innerIdx = index + 1; innerIdx < size; innerIdx++){
                if (!SortHelper.isLess(array[index], array[innerIdx])){
                    minValueIndex = innerIdx;
                }
            }
            SortHelper.swap(array, index, minValueIndex);
        }
    }
}
