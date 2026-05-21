package SortCase;

public class RecursiveQuickSortFirstValuePivot implements SortCaseInterface {

    @Override
    public void sortExecute(Comparable[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Comparable[] array, int start, int end) {
        if (start < end) {
            int q = partition(array, start, end);
            quickSort(array, start, q - 1); // 피벗 기준 왼쪽 구역 정렬
            quickSort(array, q + 1, end); // 피벗 기준 오른쪽 구역 정렬
        }
    }

    private int partition(Comparable[] array, int start, int end) {
        Comparable pivot = array[start];

        int index = start;

        for (int iterIdx = start + 1; iterIdx <= end; iterIdx++) {
            if (!SortHelper.isLess(pivot, array[iterIdx])) {
                index++;
                SortHelper.swap(array, index, iterIdx);
            }
        }

        SortHelper.swap(array, start, index);

        return index;
    }
}