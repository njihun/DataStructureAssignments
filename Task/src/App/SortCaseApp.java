package App;

import Domain.Product;
import SortCase.ArraysSort;
import SortCase.BubbleSort;
import SortCase.CollectionSort;
import SortCase.HeapSort;
import SortCase.InsertionSort;
import SortCase.IntegerSortCaseInterface;
import SortCase.IterativeMergeSort;
import SortCase.IterativeQuickSortUsingStack;
import SortCase.RecursiveMergeSort;
import SortCase.RecursiveQuickSortFirstValuePivot;
import SortCase.RecursiveQuickSortMedianOfThreePivot;
import SortCase.SelectionSort;
import SortCase.SortCaseInterface;
import SortCase.SortHelper;
import SortCase.DistributionSort.BucketSort;
import SortCase.DistributionSort.CountingSort;
import SortCase.DistributionSort.MaskingAndShiftSort;
import SortCase.DistributionSort.ModularWithDecimalSort;
import SortCase.DistributionSort.ModularWithHexSort;
import SortCase.DistributionSort.RadixSort;

import java.util.Random;

public class SortCaseApp {
    private final Random random = new Random();

    // 전체 정렬 실험을 n log n 정렬, int 전용 정렬, n^2 정렬 순서로 실행하는 메서드
    public void run() {
        System.out.println("===== n log n comparison sorts =====");
        runComparisonSort("HeapSort", new HeapSort(), false);
        runComparisonSort("RecursiveQuickSortFirstValuePivot", new RecursiveQuickSortFirstValuePivot(), false);
        runComparisonSort("RecursiveQuickSortMedianOfThreePivot", new RecursiveQuickSortMedianOfThreePivot(), false);
        runComparisonSort("IterativeQuickSortUsingStack", new IterativeQuickSortUsingStack(), false);
        runComparisonSort("RecursiveMergeSort", new RecursiveMergeSort(), false);
        runComparisonSort("IterativeMergeSort", new IterativeMergeSort(), false);
        runComparisonSort("ArraysSort", new ArraysSort(), false);
        runComparisonSort("CollectionSort", new CollectionSort(), false);

        System.out.println();
        System.out.println("===== unsigned int distribution sorts =====");
        runIntegerSort("CountingSort", new CountingSort());
        runIntegerSort("RadixSort", new RadixSort());
        runIntegerSort("BucketSort", new BucketSort());
        runIntegerSort("ModularWithDecimalSort", new ModularWithDecimalSort());
        runIntegerSort("ModularWithHexSort", new ModularWithHexSort());
        runIntegerSort("MaskingAndShiftSort", new MaskingAndShiftSort());

        System.out.println();
        System.out.println("===== n^2 comparison sorts =====");
        runComparisonSort("SelectionSort", new SelectionSort(), true);
        runComparisonSort("BubbleSort", new BubbleSort(), true);
        runComparisonSort("InsertionSort", new InsertionSort(), true);
    }

    // 비교 정렬을 실행하는 메서드로, 복잡도에 맞는 배열 크기를 선택해 네 자료형을 모두 정렬함
    private void runComparisonSort(String name, SortCaseInterface sort, boolean nSquare) {
        int[] sizes = nSquare
                ? new int[]{10000, 50000, 100000, 250000, 500000, 750000, 1000000}
                : new int[]{1000000, 2500000, 5000000, 7500000, 10000000, 12500000, 15000000, 17500000, 20000000};

        System.out.println();
        System.out.println(name);
        System.out.println("Data creation time and sorted-check time are excluded.");

        for (int size : sizes) {
            runIntegerArraySort(size, sort);
            runDoubleArraySort(size, sort);
            runStringArraySort(size, sort);
            runProductArraySort(size, sort);
        }
    }

    // runComparisonSort의 헬퍼 메서드로, Integer 배열을 생성하고 정렬 시간을 측정함
    private void runIntegerArraySort(int size, SortCaseInterface sort) {
        Integer[] array = createRandomIntegers(size);
        long nano = measureSortTime(sort, array);
        printResult("int", size, nano, isSorted(array));
    }

    // runComparisonSort의 헬퍼 메서드로, Double 배열을 생성하고 정렬 시간을 측정함
    private void runDoubleArraySort(int size, SortCaseInterface sort) {
        Double[] array = createRandomDoubles(size);
        long nano = measureSortTime(sort, array);
        printResult("double", size, nano, isSorted(array));
    }

    // runComparisonSort의 헬퍼 메서드로, String 배열을 생성하고 정렬 시간을 측정함
    private void runStringArraySort(int size, SortCaseInterface sort) {
        String[] array = createRandomStrings(size);
        long nano = measureSortTime(sort, array);
        printResult("String", size, nano, isSorted(array));
    }

    // runComparisonSort의 헬퍼 메서드로, Product 배열을 생성하고 정렬 시간을 측정함
    private void runProductArraySort(int size, SortCaseInterface sort) {
        Product[] array = createRandomProducts(size);
        long nano = measureSortTime(sort, array);
        printResult("Product", size, nano, isSorted(array));
    }

    // int 전용 정렬을 실행하는 메서드로, unsigned int 배열을 생성하고 내림차순 정렬 시간을 측정함
    private void runIntegerSort(String name, IntegerSortCaseInterface sort) {
        int[] sizes = {1000000, 2500000, 5000000, 7500000, 10000000, 12500000, 15000000, 17500000, 20000000};

        System.out.println();
        System.out.println(name);
        System.out.println("Data creation time and sorted-check time are excluded.");
        System.out.println("Unsigned int range: 0 ~ 65535");

        for (int size : sizes) {
            int[] array = createRandomUnsignedIntegers(size);
            long nano = measureSortTime(sort, array);
            printResult("unsigned int", size, nano, isSortedDescending(array));
        }
    }

    // 비교 정렬 실행 시간을 측정하는 메서드로, Comparable 배열 정렬 시간만 나노초로 반환함
    private <T extends Comparable<? super T>> long measureSortTime(SortCaseInterface sort, T[] array) {
        long start = System.nanoTime();
        sort.sort(array);
        long end = System.nanoTime();
        return end - start;
    }

    // int 전용 정렬 실행 시간을 측정하는 메서드로, int 배열 정렬 시간만 나노초로 반환함
    private long measureSortTime(IntegerSortCaseInterface sort, int[] array) {
        long start = System.nanoTime();
        sort.sort(array);
        long end = System.nanoTime();
        return end - start;
    }

    // 정렬 결과를 출력하는 메서드로, 나노초를 마이크로초, 밀리초, 초 단위로 변환해 출력함
    private void printResult(String type, int size, long nano, boolean sorted) {
        double micro = nano / 1000.0;
        double milli = nano / 1000000.0;
        double second = nano / 1000000000.0;

        System.out.printf(
                "%s | n=%d | micro=%.6f | milli=%.6f | sec=%.6f | sorted=%s%n",
                type,
                size,
                micro,
                milli,
                second,
                sorted ? "YES" : "NO"
        );
    }

    // 지정된 크기만큼 랜덤 Integer 배열을 생성하는 메서드
    private Integer[] createRandomIntegers(int size) {
        Integer[] array = new Integer[size];
        for (int index = 0; index < size; index++) {
            array[index] = random.nextInt();
        }
        return array;
    }

    // 지정된 크기만큼 0부터 65535 사이의 랜덤 int 배열을 생성하는 메서드
    private int[] createRandomUnsignedIntegers(int size) {
        int[] array = new int[size];
        for (int index = 0; index < size; index++) {
            array[index] = random.nextInt(65536);
        }
        return array;
    }

    // 지정된 크기만큼 랜덤 Double 배열을 생성하는 메서드
    private Double[] createRandomDoubles(int size) {
        Double[] array = new Double[size];
        for (int index = 0; index < size; index++) {
            array[index] = random.nextDouble();
        }
        return array;
    }

    // 지정된 크기만큼 길이가 4 이상인 랜덤 String 배열을 생성하는 메서드
    private String[] createRandomStrings(int size) {
        String[] array = new String[size];
        for (int index = 0; index < size; index++) {
            int length = 4 + random.nextInt(13);
            StringBuilder builder = new StringBuilder(length);
            for (int charIndex = 0; charIndex < length; charIndex++) {
                builder.append((char) ('a' + random.nextInt(26)));
            }
            array[index] = builder.toString();
        }
        return array;
    }

    // 지정된 크기만큼 랜덤 Product 배열을 생성하는 메서드
    private Product[] createRandomProducts(int size) {
        Product[] array = new Product[size];
        for (int index = 0; index < size; index++) {
            array[index] = new Product(
                    random.nextInt(1000000),
                    random.nextInt(10000000),
                    random.nextInt(100000),
                    1.0 + random.nextDouble() * 4.0
            );
        }
        return array;
    }

    // 비교 정렬 결과가 오름차순으로 정렬되었는지 확인하는 메서드
    private <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        for (int index = 1; index < array.length; index++) {
            if (SortHelper.isLess(array[index], array[index - 1])) {
                return false;
            }
        }
        return true;
    }

    // int 전용 정렬 결과가 내림차순으로 정렬되었는지 확인하는 메서드
    private boolean isSortedDescending(int[] array) {
        for (int index = 1; index < array.length; index++) {
            if (array[index - 1] < array[index]) {
                return false;
            }
        }
        return true;
    }
}
