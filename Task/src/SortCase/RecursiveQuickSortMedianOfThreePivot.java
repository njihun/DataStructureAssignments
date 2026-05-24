package SortCase;

public class RecursiveQuickSortMedianOfThreePivot implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Comparable[] array, int start, int end) {
        if (start < end) {
            int mid = partition(array, start, end);
            quickSort(array, start, mid - 1);
            quickSort(array, mid + 1, end);
        }
    }

    private int partition(Comparable[] array, int start, int end) {
        setMedianOfThreePivot(array, start, end);

        Comparable pivot = array[end];
        int index = start - 1;

        for (int iterIdx = start; iterIdx <= end - 1; iterIdx++) {
            if (!SortHelper.isLess(pivot, array[iterIdx])) {
                index++;
                SortHelper.swap(array, index, iterIdx);
            }
        }

        SortHelper.swap(array, index + 1, end);

        return index + 1;
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
