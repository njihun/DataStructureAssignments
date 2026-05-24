package SortCase;

public class HeapSort implements SortCaseInterface {
    @Override
    public void sort(Comparable[] array) {
        // 파라미터로 넘어온 배열의 0번 인덱스가 비어있다고 가정
        int heapSize = array.length - 1;
        for (int index = heapSize / 2; index > 0; index--){
            downHeap(array, index, heapSize);
        }

        while (heapSize > 1){
            SortHelper.swap(array, 1, heapSize--);
            downHeap(array, 1, heapSize);
        }
    }

    public void downHeap (Comparable[] array, int parentIdx, int heapSize){
        while (2 * parentIdx <= heapSize){
            int childIdx = 2 * parentIdx;

            if (childIdx < heapSize && SortHelper.isLess(array[childIdx], array[childIdx + 1])){
                childIdx++;
            }

            if (!SortHelper.isLess(array[parentIdx], array[childIdx])){
                break;
            }

            SortHelper.swap(array, parentIdx, childIdx);
            parentIdx = childIdx;
        }
    }
}
