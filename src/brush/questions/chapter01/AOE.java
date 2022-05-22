package brush.questions.chapter01;

/**
 * @program: my_algorithm
 * @description 给定两个非负数组x和hp，长度都是N，再给定一个正数range
 * x有序，x[i]表示i号怪兽在x轴上的位置
 * hp[i]表示i号怪兽的血量
 * 再给定一个正数range，表示如果法师释放技能的范围长度：假设法师在x位置，[x-range,x+range]的范围
 * 被打到的每只怪兽损失1点血量。返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
 * @author: 34371
 * @create: 2022-05-06 22:25
 **/
public class AOE {

    public static int myAoe(int[] x, int[] hp, int range) {

        int n = x.length;
        int[] cover = new int[n];
        int r = 0;
        // cover[i] : 如果i位置是技能的最左侧，技能往右的range范围内，最右影响到哪
        for (int i = 0; i < n; i++) {
            while (r < n && x[r] - x[i] <= range) {
                r++;
            }
            cover[i] = r - 1;
        }
        MySegmentTree st = new MySegmentTree(hp);
        st.build();
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int leftHP = st.query(i, i);
            if (leftHP > 0) {
                ans += leftHP;
                st.add(i, cover[i - 1] + 1, -leftHP, 1, n, 1);
            }
        }
        return ans;
    }

    private static int[] dealArr(int[] x, int[] hp) {
        int length = x[x.length - 1] + 1;
        int[] arr = new int[length];
        for (int i = 0; i < x.length; i++) {
            arr[x[i]] = hp[i];
        }
        return arr;
    }

    static class MySegmentTree {
        int[] hp;//血量
        int length;
        int[] sums;
        int[] lazy;

        public MySegmentTree(int[] arr) {
            this.length = arr.length + 1;
            hp = new int[length];
            System.arraycopy(arr, 0, hp, 1, arr.length);
            sums = new int[this.length << 2];
            lazy = new int[this.length << 2];
        }

        public int query(int leftIndex, int rightIndex) {
            return query(leftIndex, rightIndex, 1, length - 1, 1);
        }

        private int query(int leftScale, int rightScale, int curLeftScale, int curRightScale, int ci) {

            if (leftScale <= curLeftScale && rightScale >= curRightScale) {
                return sums[ci];
            }

            int mid = curLeftScale + (curRightScale - curLeftScale) / 2;
            pushDown(ci, curLeftScale, curRightScale, mid);
            int sum = 0;
            if (leftScale <= mid) {
                sum += query(leftScale, rightScale, curLeftScale, mid, ci << 1);
            }
            if (rightScale > mid) {
                sum += query(leftScale, rightScale, mid + 1, curRightScale, ci << 1 | 1);
            }

            return sum;

        }

        public void add(int leftScale, int rightScale, int num) {
            add(leftScale, rightScale, num, 1, this.length - 1, 1);
        }

        private void add(int leftScale, int rightScale, int num, int curLeftScale, int curRightScale, int curIndex) {
            if (leftScale > rightScale) {
                return;
            }
            //能懒住
            if (leftScale <= curLeftScale && rightScale >= curRightScale) {
                sums[curIndex] += (curRightScale - curLeftScale + 1) * num;
                lazy[curIndex] += num;
                return;
            }

            //无法懒住
            //当前任务需要下发
            int mid = curLeftScale + (curRightScale - curLeftScale) / 2;
            pushDown(curIndex, curLeftScale, curRightScale, mid);
            if (leftScale <= mid) {
                add(leftScale, rightScale, num, curLeftScale, mid, curIndex << 1);
            }
            if (rightScale > mid) {
                add(leftScale, rightScale, num, mid + 1, curRightScale, curIndex << 1 | 1);
            }
            pushUp(curIndex);
        }


        private void pushDown(int curIndex, int curLeftScale, int curRightScale, int mid) {
            if (lazy[curIndex]!=0){
                sums[curIndex << 1] += lazy[curIndex] * (mid - curLeftScale + 1);
                sums[curIndex << 1 | 1] += lazy[curIndex] * (curRightScale - mid);
                lazy[curIndex << 1] += lazy[curIndex];
                lazy[curIndex << 1 | 1] += lazy[curIndex];
                lazy[curIndex] = 0;
            }
        }


        public void build() {
            buildTree(1, this.length - 1, 1);
        }

        public void buildTree(int leftScale, int rightScale, int ci) {
            if (leftScale == rightScale) {
                sums[ci] = hp[rightScale];
                return;
            }

            int mid = leftScale + (rightScale - leftScale) / 2;
            buildTree(leftScale, mid, ci << 1);
            buildTree(mid + 1, rightScale, ci << 1 | 1);
            pushUp(ci);
        }

        private void pushUp(int ci) {
            sums[ci] = sums[ci << 1] + sums[ci << 1 | 1];
        }
    }

    public static void main(String[] args) {
        int[] x = new int[]{16, 34, 36};
        int[] hp = new int[]{5, 16, 24};
        int range = 3;
        System.out.println(myAoe(x,hp,range));
    }

}
