package SortCase;

public class HeapSort implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        int heapSize = array.length;

        for (int index = heapSize / 2 - 1; index >= 0; index--) {
            downHeap(array, index, heapSize);
        }

        while (heapSize > 1) {
            SortHelper.swap(array, 0, --heapSize);
            downHeap(array, 0, heapSize);
        }
    }

    public void downHeap(Comparable[] array, int parentIdx, int heapSize) {
        while (2 * parentIdx + 1 < heapSize) {
            int childIdx = 2 * parentIdx + 1;

            if (childIdx + 1 < heapSize && SortHelper.isLess(array[childIdx], array[childIdx + 1])) {
                childIdx++;
            }

            if (!SortHelper.isLess(array[parentIdx], array[childIdx])) {
                break;
            }

            SortHelper.swap(array, parentIdx, childIdx);
            parentIdx = childIdx;
        }
    }
}
