package SortCase;

public class SortHelper {
    public static void swap(Comparable[] array, int thisIdx, int targetIdx){
        Comparable temp = array[thisIdx];
        array[thisIdx] = array[targetIdx];
        array[targetIdx] = temp;
    }

    public static boolean isLess(Comparable thisValue, Comparable targetValue){
        return thisValue.compareTo(targetValue) < 0;
    }

}
