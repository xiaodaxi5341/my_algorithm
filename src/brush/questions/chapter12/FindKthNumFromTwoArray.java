package brush.questions.chapter12;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数
 * 进阶，在两个都有序的数组中找整体第K小的数，可以做到O(log(Min(M,N)))
 * @author: 34371
 * @create: 2022-06-05 17:51
 **/
public class FindKthNumFromTwoArray {

    //问题2:进阶
    public static int getKthNumFromArray(int[] nums1, int[] nums2, int K) {
        if (nums1 == null || nums1.length == 0){
            return nums2[K-1];
        }
        if (nums2 == null || nums2.length == 0){
            return nums1[K-1];
        }
        int[] longArr = nums1.length > nums2.length ? nums1 : nums2;
        int[] shortArr = nums1.length > nums2.length ? nums2 : nums1;
        if (K <= shortArr.length) {
            return getUpMedian(longArr, 0, K - 1, shortArr, 0, K - 1);
        }
        if (K > longArr.length) {
            int fromLongIndex = K - shortArr.length - 1;
            int fromShortIndex = K - longArr.length - 1;

            if (longArr[fromLongIndex] >= shortArr[shortArr.length - 1]) {
                return longArr[fromLongIndex];
            }
            if (shortArr[fromShortIndex] >= longArr[longArr.length - 1]) {
                return shortArr[fromShortIndex];
            }
            return getUpMedian(longArr, fromLongIndex + 1, longArr.length - 1, shortArr, fromShortIndex + 1, shortArr.length - 1);
        }

        int fromLongIndex = K - shortArr.length - 1;
        if (longArr[fromLongIndex] >= shortArr[shortArr.length - 1]) {
            return longArr[fromLongIndex];
        }
        return getUpMedian(longArr, fromLongIndex + 1, K - 1, shortArr, 0, shortArr.length - 1);
    }

    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            // mid1 = s1 + (e1 - s1) >> 1
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            // 两个中点一定不等！
            if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else { // A[mid1] < B[mid2]
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            } else { // 偶数长度
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }

    //问题1：找中位数，偶数找上中位数
    public static int getUpMedian(int[] nums1, int[] nums2) {
        if (nums1.length == 1) {
            return nums1[0] > nums2[0] ? nums2[0] : nums1[0];
        }

        if ((nums1.length & 1) == 1) {//奇数长度
            int mid = nums1.length / 2;
            if (nums1[mid] == nums2[mid]) {
                return nums1[mid];
            } else {
                if (nums1[mid] > nums2[mid]) {
                    return processOdd(nums1, nums2, mid);
                } else {
                    return processOdd(nums2, nums1, mid);
                }
            }
        } else {
            int mid = nums1.length / 2 - 1;
            if (nums1[mid] == nums2[mid]) {
                return nums1[mid];
            } else {
                if (nums1[mid] > nums2[mid]) {
                    return processEven(nums1, nums2, mid);
                } else {
                    return processEven(nums2, nums1, mid);
                }
            }
        }
    }

    private static int processOdd(int[] nums1, int[] nums2, int mid) {
        if (nums2[mid] >= nums1[mid - 1]) {
            return nums2[mid];
        } else {
            int[] newNums1 = new int[mid];
            System.arraycopy(nums1, 0, newNums1, 0, mid);
            int[] newNums2 = new int[mid];
            System.arraycopy(nums2, mid + 1, newNums2, 0, mid);

            return getUpMedian(newNums1, newNums2);
        }
    }

    private static int processEven(int[] nums1, int[] nums2, int mid) {

        int[] newNums1 = new int[mid + 1];
        System.arraycopy(nums1, 0, newNums1, 0, mid + 1);
        int[] newNums2 = new int[mid + 1];
        System.arraycopy(nums2, mid + 1, newNums2, 0, mid + 1);

        return getUpMedian(newNums1, newNums2);
    }

    public static void main(String[] args) {

        int maxLength = 5;
        int maxVal = 30;

        for (int times = 0; times < 50000; times++) {
            System.out.println("当前测试第【"+(times+1)+"】次");
            int length1 = (int) (Math.random() * maxLength);
            while (length1 == 0) {
                length1 = (int) (Math.random() * maxLength);
            }
            int length2 = (int) (Math.random() * maxLength);
            while (length2 == 0 && length2 == length1) {
                length2 = (int) (Math.random() * maxLength);
            }
            int[] nums1 = new int[length1];
            int[] nums2 = new int[length2];
            int[] all = new int[length1 + length2];
            for (int i = 0; i < length1; i++) {
                nums1[i] = (int) (Math.random() * maxVal);
                all[i] = nums1[i];
            }

            for (int i = 0; i < length2; i++) {
                nums2[i] = (int) (Math.random() * maxVal);
                all[i + length1] = nums2[i];
            }
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            Arrays.sort(all);
            System.out.println("nums 1 : " + Arrays.toString(nums1));
            System.out.println("nums 2 : " + Arrays.toString(nums2));
            System.out.println("nums all : " + Arrays.toString(all));

            int K = (int) (Math.random() * (length1 + length2))+1;
            while (K == 0) {
                K = (int) (Math.random() * (length1 + length2));
            }
            System.out.println(" k " + K);
            try {
                if (getKthNumFromArray(nums1, nums2, K) != all[K - 1]) {
                    System.err.println("nums 1 : " + Arrays.toString(nums1));
                    System.err.println("nums 2 : " + Arrays.toString(nums2));
                    System.err.println("nums all : " + Arrays.toString(all));
                    break;
                }
            }catch (Exception e){
                System.err.println("nums 1 : " + Arrays.toString(nums1));
                System.err.println("nums 2 : " + Arrays.toString(nums2));
                System.err.println("nums all : " + Arrays.toString(all));
                throw e;
            }

        }
        System.out.println();
    }
}



