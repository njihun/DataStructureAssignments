package SortCase;

public class RecursiveMergeSort implements SortCaseInterface {

    @Override
    public void sort(Comparable[] array) {
        Comparable[] temp = new Comparable[array.length];
        mergeSort(array, 0, array.length - 1, temp);
    }

    private void mergeSort(Comparable[] array, int start, int end, Comparable[] temp) {
        if (start < end) {
            int q = (start + end) / 2;
            mergeSort(array, start, q, temp); // 왼쪽 부분 정렬
            mergeSort(array, q + 1, end, temp); // 오른쪽 부분 정렬
            merge(array, start, q, end, temp); // 병합
        }
    }

    private void merge(Comparable[] array, int start, int mid, int end, Comparable[] temp) {
        int leftStart = start;
        int rightStart = mid + 1;
        int tempIdx = start;

        while (leftStart <= mid && rightStart <= end) {
            if (!SortHelper.isLess(array[rightStart], array[leftStart])) {
                temp[tempIdx++] = array[leftStart++];
            } else {
                temp[tempIdx++] = array[rightStart++];
            }
        }

        while (leftStart <= mid) {
            temp[tempIdx++] = array[leftStart++];
        }

        while (rightStart <= end) {
            temp[tempIdx++] = array[rightStart++];
        }

        for (int k = start; k <= end; k++) {
            array[k] = temp[k];
        }
    }
}