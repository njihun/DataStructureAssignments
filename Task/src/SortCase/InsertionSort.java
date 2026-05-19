package SortCase;

public class InsertionSort implements SortCaseInterface {
    @Override
    public void sortExecute(Comparable[] array) {
        int size = array.length;
        for (int index = 1; index < size; index++){
            for (int innerIdx = index; innerIdx > 0; innerIdx--){
                if (SortHelper.isLess(array[innerIdx], array[innerIdx - 1])){
                    SortHelper.swap(array, innerIdx, innerIdx - 1);
                } else {
                    break;
                }
            }
        }
    }
}
