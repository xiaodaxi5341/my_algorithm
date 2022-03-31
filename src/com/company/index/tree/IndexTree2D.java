package com.company.index.tree;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-03-30 20:30
 **/
public class IndexTree2D {

    int[][] help;
    int[][] nums;

    IndexTree2D(int[][] origin) {
        int rowSize = origin.length;
        int colSize = origin[0].length;
        help = new int[rowSize + 1][colSize + 1];
        nums = new int[rowSize][colSize];
        for (int i = 0;i<rowSize;i++){
            for (int j = 0 ; j<colSize;j++){
                update(i,j,origin[i][j]);
            }
        }
    }

    public void update(int rowIndex, int colIndex, int newValue) {

        int add = newValue - nums[rowIndex][colIndex];
        for (int row = rowIndex+1;row < help.length;row+=(row&(-row))) {
            for (int col = colIndex + 1;col<help[0].length;col+=(col&(-col))){
                help[rowIndex][colIndex]+=add;
            }
        }
    }

    public int sum(int row,int col){
        int res = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                res+=help[i][j];
            }
        }
        return res;
    }

    public int sumRegion(int l1,int r1,int l2,int r2){
        int largeL = Math.max(l1,l2);
        int smallL = Math.min(l1,l2)-1;
        int largeR = Math.max(r1,r2);
        int smallR  =Math.min(r1,r2)-1;
        return sum(largeL,largeR)+sum(smallL,smallR)-sum(smallL,largeR)-sum(largeL,smallR);
    }

}
