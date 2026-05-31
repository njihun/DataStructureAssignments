package SortCase;

import Collections.MyArrayList;

public class IterativeQuickSortUsingStack implements SortCaseInterface {

    // MyArrayList를 기반으로 직접 구현한 int 스택
    private static class IntStack {
        private final MyArrayList<Integer> list = new MyArrayList<>();
        private int size = 0;

        public void push(int value) {
            list.append(value);
            size++;
        }

        public int pop() {
            int value = list.getValue(size - 1);
            list.remove(size - 1);
            size--;
            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    @Override
    public void sort(Comparable[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        IntStack stack = new IntStack();

        stack.push(0);
        stack.push(array.length - 1);

        while (!stack.isEmpty()) {
            int end   = stack.pop();
            int start = stack.pop();

            if (start >= end) {
                continue;
            }

            int pivotIndex = partition(array, start, end);

            // 왼쪽 구간 push
            if (start < pivotIndex - 1) {
                stack.push(start);
                stack.push(pivotIndex - 1);
            }

            // 오른쪽 구간 push
            if (pivotIndex + 1 < end) {
                stack.push(pivotIndex + 1);
                stack.push(end);
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
