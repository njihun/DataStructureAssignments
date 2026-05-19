package SortCase;

public class BubbleSortInterface implements SortCaseInterface {
    @Override
    public void sortExecute(Comparable[] array) {
        int size = array.length;

        for (int index = 0; index < size - 1; index++){
            //플래그를 세워서 스왑이 발생했는지 판단.
            boolean isSwapOccur = false;

            for (int innerIdx = 0; innerIdx < array.length - (1 + index); innerIdx++){
                if (SortHelper.isLess(array[innerIdx + 1], array[innerIdx])){
                    SortHelper.swap(array, innerIdx, innerIdx + 1);
                    isSwapOccur = true;
                }
            }

            //루프가 끝났는데도 스왑이 발생하지 않았으면 이미 정렬된 것임.
            if (!isSwapOccur){
                break;
            }
        }
    }
}
