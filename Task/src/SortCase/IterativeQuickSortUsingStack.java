package SortCase;

public class IterativeQuickSortUsingStack implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int[] startStack = new int[array.length];
        int[] endStack = new int[array.length];
        int top = -1;

        startStack[++top] = 0;
        endStack[top] = array.length - 1;

        while (top >= 0) {
            int start = startStack[top];
            int end = endStack[top--];

            if (start >= end) {
                continue;
            }

            int pivotIndex = partition(array, start, end);

            if (pivotIndex - 1 > start) {
                startStack[++top] = start;
                endStack[top] = pivotIndex - 1;
            }

            if (pivotIndex + 1 < end) {
                startStack[++top] = pivotIndex + 1;
                endStack[top] = end;
            }
        }

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
