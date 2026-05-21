package SortCase;

public class IterativeQuickSortUsingStack implements SortCaseInterface {
    @Override
    public void sortExecute(Comparable[] array) {

    }

    private int partition(Comparable[] array, int start, int end) {
        setMedianOfThreePivot(array, start, end);

        Comparable pivotValue = array[end];
        int storeIndex = start - 1;

        for (int currentIndex = start; currentIndex <= end - 1; currentIndex++) {
            if (!SortHelper.isLess(pivotValue, array[currentIndex])) {
                storeIndex++;
                SortHelper.swap(array, storeIndex, currentIndex);
            }
        }

        SortHelper.swap(array, storeIndex + 1, end);
        return storeIndex + 1;
    }

    private void setMedianOfThreePivot(Comparable[] array, int start, int end) {
        int mid = start + (end - start) / 2;

        if (SortHelper.isLess(array[mid], array[start])) {
            SortHelper.swap(array, start, mid);
        }
        if (SortHelper.isLess(array[end], array[mid])) {
            SortHelper.swap(array, mid, end);
        }
        if (SortHelper.isLess(array[mid], array[start])) {
            SortHelper.swap(array, start, mid);
        }

        SortHelper.swap(array, mid, end);
    }
}
