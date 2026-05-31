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
import java.util.Scanner;

public class SortCaseApp {
    private final Random random = new Random();

    // 사용자에게 정렬 번호를 입력받고, 번호에 맞는 정렬 실행 메서드를 호출하는 메서드
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Select sort number (0: exit): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }

            int selected = scanner.nextInt();
            if (selected == 0) {
                System.out.println("Sort performance app finished.");
                return;
            }

            switch (selected) {
                case 1 -> runComparisonSort("SelectionSort", new SelectionSort(), true);
                case 2 -> runComparisonSort("BubbleSort", new BubbleSort(), true);
                case 3 -> runComparisonSort("InsertionSort", new InsertionSort(), true);
                case 4 -> runComparisonSort("HeapSort", new HeapSort(), false);
                case 5 -> runComparisonSort("RecursiveQuickSortFirstValuePivot", new RecursiveQuickSortFirstValuePivot(), false);
                case 6 -> runComparisonSort("RecursiveQuickSortMedianOfThreePivot", new RecursiveQuickSortMedianOfThreePivot(), false);
                case 7 -> runComparisonSort("IterativeQuickSortUsingStack", new IterativeQuickSortUsingStack(), false);
                case 8 -> runComparisonSort("RecursiveMergeSort", new RecursiveMergeSort(), false);
                case 9 -> runComparisonSort("IterativeMergeSort", new IterativeMergeSort(), false);
                case 10 -> runComparisonSort("ArraysSort", new ArraysSort(), false);
                case 11 -> runComparisonSort("CollectionSort", new CollectionSort(), false);
                case 12 -> runIntegerSort("CountingSort", new CountingSort());
                case 13 -> runIntegerSort("RadixSort", new RadixSort());
                case 14 -> runIntegerSort("BucketSort", new BucketSort());
                case 15 -> runIntegerSort("ModularWithDecimalSort", new ModularWithDecimalSort());
                case 16 -> runIntegerSort("ModularWithHexSort", new ModularWithHexSort());
                case 17 -> runIntegerSort("MaskingAndShiftSort", new MaskingAndShiftSort());
                default -> System.out.println("Unknown sort number.");
            }
        }
    }

    // run 메서드의 헬퍼 메서드로, 사용자가 선택할 수 있는 정렬 메뉴를 출력함
    private void printMenu() {
        System.out.println();
        System.out.println("===== Sort Case Menu =====");
        System.out.println(" 1. SelectionSort");
        System.out.println(" 2. BubbleSort");
        System.out.println(" 3. InsertionSort");
        System.out.println(" 4. HeapSort");
        System.out.println(" 5. RecursiveQuickSortFirstValuePivot");
        System.out.println(" 6. RecursiveQuickSortMedianOfThreePivot");
        System.out.println(" 7. IterativeQuickSortUsingStack");
        System.out.println(" 8. RecursiveMergeSort");
        System.out.println(" 9. IterativeMergeSort");
        System.out.println("10. ArraysSort");
        System.out.println("11. CollectionSort");
        System.out.println("12. CountingSort");
        System.out.println("13. RadixSort");
        System.out.println("14. BucketSort");
        System.out.println("15. ModularWithDecimalSort");
        System.out.println("16. ModularWithHexSort");
        System.out.println("17. MaskingAndShiftSort");
    }

    // 비교 정렬을 실행하는 메서드로, 정렬 복잡도에 맞는 배열 크기를 선택해 네가지 자료형을 모두 정렬함
    private void runComparisonSort(String name, SortCaseInterface sort, boolean nSquare) {
        //삼항 연산자를 사용하여 n제곱의 복잡도는 전용 크기를 가진 배열에 할당함
        int[] sizes = nSquare
                ? new int[]{10000, 50000, 100000, 250000, 500000, 750000, 1000000}
                : new int[]{1000000, 2500000, 5000000, 7500000, 10000000, 12500000, 15000000, 17500000, 20000000};

        System.out.println();
        System.out.println(name + " running");
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
        System.out.println(name + " running");
        System.out.println("Data creation time and sorted-check time are excluded.");
        System.out.println("Unsigned int range: 0 ~ 65535");

        for (int size : sizes) {
            int[] array = createRandomUnsignedIntegers(size);
            long nano = measureSortTime(sort, array);
            printResult("unsigned int", size, nano, isSortedDescending(array));
        }
    }

    // 비교 정렬 실행 시간을 측정하는 메서드로, Comparable 배열 정렬 시간만 나노초로 반환함
    private long measureSortTime(SortCaseInterface sort, Comparable[] array) {
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
    private boolean isSorted(Comparable[] array) {
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
