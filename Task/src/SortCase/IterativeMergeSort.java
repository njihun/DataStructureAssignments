package SortCase;

public class IterativeMergeSort implements SortCaseInterface {
    @Override
    public void sortExecute(Comparable[] array) {
        int totalElements = array.length;
        if (totalElements <= 1) {
            return;
        }

        // 병합 과정에서 사용할 임시 배열
        Comparable[] temporaryArray = new Comparable[totalElements];

        // currentMergeSize: 병합할 부분 배열의 크기 (1, 2, 4, 8 ... 씩 2배로 증가)
        for (int currentMergeSize = 1; currentMergeSize < totalElements; currentMergeSize *= 2) {

            // leftStartIndex: 병합을 시작할 왼쪽 부분 배열의 첫 번째 인덱스
            for (int leftStartIndex = 0; leftStartIndex < totalElements - currentMergeSize; leftStartIndex += currentMergeSize * 2) {

                // middleIndex: 왼쪽 부분 배열의 마지막 인덱스
                int middleIndex = leftStartIndex + currentMergeSize - 1;

                // rightEndIndex: 오른쪽 부분 배열의 마지막 인덱스 (배열 크기를 초과하지 않도록 보정)
                int rightEndIndex = Math.min(leftStartIndex + currentMergeSize * 2 - 1, totalElements - 1);

                // 자연 합병 정렬(Natural Merge) 최적화: 왼쪽 배열 마지막 값이 오른쪽 배열 첫 값보다 작거나 같으면 병합 생략
                if (!SortHelper.isLess(array[middleIndex + 1], array[middleIndex])) {
                    continue;
                }

                mergeSubArrays(array, leftStartIndex, middleIndex, rightEndIndex, temporaryArray);
            }
        }
    }

    private void mergeSubArrays(Comparable[] array, int leftStartIndex, int middleIndex, int rightEndIndex, Comparable[] temporaryArray) {
        int currentLeftIndex = leftStartIndex;
        int currentRightIndex = middleIndex + 1;
        int temporaryArrayIndex = leftStartIndex;

        // 1. 양쪽 부분 배열을 비교하며 임시 배열(temporaryArray)에 복사
        while (currentLeftIndex <= middleIndex && currentRightIndex <= rightEndIndex) {
            if (!SortHelper.isLess(array[currentRightIndex], array[currentLeftIndex])) {
                temporaryArray[temporaryArrayIndex++] = array[currentLeftIndex++];
            } else {
                temporaryArray[temporaryArrayIndex++] = array[currentRightIndex++];
            }
        }

        // 2. 왼쪽 배열에 남은 요소들을 마저 채움
        while (currentLeftIndex <= middleIndex) {
            temporaryArray[temporaryArrayIndex++] = array[currentLeftIndex++];
        }

        // 3. 오른쪽 배열에 남은 요소들을 마저 채움
        while (currentRightIndex <= rightEndIndex) {
            temporaryArray[temporaryArrayIndex++] = array[currentRightIndex++];
        }

        // 4. 정렬된 결과를 원본 배열에 덮어씌움
        for (int copyIndex = leftStartIndex; copyIndex <= rightEndIndex; copyIndex++) {
            array[copyIndex] = temporaryArray[copyIndex];
        }
    }
}
