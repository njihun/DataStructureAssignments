package SortCase;

public class CountingSort implements SortCaseInterface {

    @Override
    public void sort(Comparable[] array) {

        if (array.length == 0) {
            return;
        }

        int n = array.length;

        int max = (Integer) array[0];

        for (int i = 1; i < n; i++) {
            if ((Integer) array[i] > max) {
                max = (Integer) array[i];
            }
        }

        int[] count = new int[max + 1];

        for (int i = 0; i < n; i++) {
            count[(Integer) array[i]]++;
        }

        int index = 0;

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                array[index++] = i;
                count[i]--;
            }
        }
    }
}
