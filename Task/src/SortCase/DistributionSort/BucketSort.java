package SortCase.DistributionSort;

import Collections.MyArrayList;
import SortCase.IntegerSortCaseInterface;

public class BucketSort implements IntegerSortCaseInterface {
    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int max = findMax(array);
        int bucketCount = max + 1;
        int range = max + 1;
        MyArrayList<Integer>[] buckets = createBuckets(bucketCount);
        int[] bucketSizes = new int[bucketCount];

        for (int value : array) {
            int bucketIndex = (int) (((double) value / range) * bucketCount);
            buckets[bucketIndex].append(value);
            bucketSizes[bucketIndex]++;
        }

        int arrayIndex = 0;
        for (int bucketIndex = buckets.length - 1; bucketIndex >= 0; bucketIndex--) {
            int[] bucketValues = copyBucketValues(buckets[bucketIndex], bucketSizes[bucketIndex]);
            sortBucketDescending(bucketValues);

            for (int valueIndex = 0; valueIndex < bucketValues.length; valueIndex++) {
                array[arrayIndex] = bucketValues[valueIndex];
                arrayIndex++;
            }
        }
    }

    private MyArrayList<Integer>[] createBuckets(int bucketCount) {
        MyArrayList<Integer>[] buckets = new MyArrayList[bucketCount];
        for (int bucketIndex = 0; bucketIndex < bucketCount; bucketIndex++) {
            buckets[bucketIndex] = new MyArrayList<>();
        }
        return buckets;
    }

    private int[] copyBucketValues(MyArrayList<Integer> bucket, int bucketSize) {
        int[] bucketValues = new int[bucketSize];
        for (int index = 0; index < bucketSize; index++) {
            bucketValues[index] = bucket.getValue(index);
        }
        return bucketValues;
    }

    private void sortBucketDescending(int[] bucketValues) {
        for (int index = 1; index < bucketValues.length; index++) {
            int value = bucketValues[index];
            int beforeIndex = index - 1;

            while (beforeIndex >= 0 && value > bucketValues[beforeIndex]) {
                bucketValues[beforeIndex + 1] = bucketValues[beforeIndex];
                beforeIndex--;
            }

            bucketValues[beforeIndex + 1] = value;
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
