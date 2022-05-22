package brush.questions.chapter01;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 * @author: 34371
 * @create: 2022-04-01 21:39
 **/
public class RopeCoverPoint {


    public static int myProcess(int[] arr, int k) {

        int left = 0;
        int right = 0;
        int max = 0;

        while (right < arr.length && left < arr.length) {
            if (arr[right] - arr[left] <= k) {
                max = Math.max(max, right - left + 1);
                right++;
            } else {
                left++;
            }
        }

        return max;
    }


    //滑动窗口
    public static int maxPoint1(int[] arr, int L) {

        int l = 0;
        int r = 0;
        int max = 0;

        while (l < arr.length) {
            while (r < arr.length && arr[r] - arr[l] <= L) {
                r++;
            }
            max = Math.max(max, r - l);
            l++;
        }

        return max;
    }

    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }


    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main1(String[] args) {
        int[] arr = new int[]{295, 844};
        int L = 0;
        System.out.println(maxPoint1(arr, L));
        System.out.println(myProcess(arr, L));
    }

    public static void main(String[] args) {
        int len = 1000;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = myProcess(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(Arrays.toString(arr));
                System.out.println(L);
                break;
            }
        }
        System.out.println("success");

    }


}
