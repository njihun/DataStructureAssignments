package SortCase.DistributionSort;

import SortCase.IntegerSortCaseInterface;

public class ModularWithDecimalSort implements IntegerSortCaseInterface {
    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int max = findMax(array);
        for (int digitPlace = 1; max / digitPlace > 0; digitPlace *= 10) {
            sortByDecimalDigitDescending(array, digitPlace);
        }
    }

    private void sortByDecimalDigitDescending(int[] array, int digitPlace) {
        int[] count = new int[10];
        int[] start = new int[10];
        int[] temp = new int[array.length];

        for (int value : array) {
            int digit = (value / digitPlace) % 10;
            count[digit]++;
        }

        start[9] = 0;
        for (int digit = 8; digit >= 0; digit--) {
            start[digit] = start[digit + 1] + count[digit + 1];
        }

        for (int value : array) {
            int digit = (value / digitPlace) % 10;
            temp[start[digit]] = value;
            start[digit]++;
        }

        for (int index = 0; index < array.length; index++) {
            array[index] = temp[index];
        }
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int index = 1; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }
}
