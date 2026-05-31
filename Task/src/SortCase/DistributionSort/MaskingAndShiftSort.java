package SortCase.DistributionSort;

import SortCase.IntegerSortCaseInterface;

public class MaskingAndShiftSort implements IntegerSortCaseInterface {
    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int max = findMax(array);
        for (int shift = 0; (max >> shift) > 0; shift += 4) {
            sortByHexDigitDescending(array, shift);
        }
    }

    private void sortByHexDigitDescending(int[] array, int shift) {
        int[] count = new int[16];
        int[] start = new int[16];
        int[] temp = new int[array.length];

        for (int value : array) {
            int digit = (value >> shift) & 15;
            count[digit]++;
        }

        start[15] = 0;
        for (int digit = 14; digit >= 0; digit--) {
            start[digit] = start[digit + 1] + count[digit + 1];
        }

        for (int value : array) {
            int digit = (value >> shift) & 15;
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
