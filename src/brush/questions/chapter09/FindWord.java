package brush.questions.chapter09;

/**
 * @program: my_algorithm
 * @description 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上、下、左、右，能不能找到word？
 * char[][] m = {  { 'a', 'b', 'z' },
 * { 'c', 'd', 'o' },
 * { 'f', 'e', 'o' } }
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 * @author: 34371
 * @create: 2022-05-30 21:42
 **/
public class FindWord {

    public static boolean processByDp(char[][] matrix, String word) {
        boolean[][][] dp = new boolean[matrix.length][matrix[0].length][word.length() + 1];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                dp[row][col][word.length()] = true;
            }
        }


        for (int index = word.length() - 1; index >= 0; index--) {
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] != word.charAt(index)) {
                        dp[row][col][index] = false;
                    } else {
                        boolean flag = false;
                        if (row < matrix.length - 1) {
                            flag = dp[row + 1][col][index + 1];
                        }
                        if (!flag && row > 0) {
                            flag = flag || dp[row - 1][col][index + 1];
                        }
                        if (!flag && col < matrix[0].length - 1) {
                            flag = flag || dp[row][col + 1][index + 1];
                        }
                        if (!flag && col > 0) {
                            flag = flag || dp[row][col - 1][index + 1];
                        }
                        dp[row][col][index] = flag;
                    }
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (dp[row][col][0]){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean canFindByRepeat(char[][] matrix, String word) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (process(matrix, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean process(char[][] matrix, String word, int index, int row, int col) {
        if (index == word.length()) {
            return true;
        }
        if (matrix[row][col] != word.charAt(index)) {
            return false;
        }

        //当前字母相同
        boolean flag = false;
        if (row < matrix.length - 1) {
            flag = flag || process(matrix, word, index + 1, row + 1, col);
        }
        if (!flag && row > 0) {
            flag = flag || process(matrix, word, index + 1, row - 1, col);
        }
        if (!flag && col < matrix[0].length - 1) {
            flag = flag || process(matrix, word, index + 1, row, col + 1);
        }
        if (!flag && col > 0) {
            flag = flag || process(matrix, word, index + 1, row, col - 1);
        }
        return flag;
    }

    public static void main(String[] args) {
        char[][] m = {{'a', 'b', 'z'}, {'c', 'd', 'o'}, {'f', 'e', 'o'},};
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
        System.out.println(processByDp(m, word1));
        System.out.println(canFindByRepeat(m, word2));
    }

}
