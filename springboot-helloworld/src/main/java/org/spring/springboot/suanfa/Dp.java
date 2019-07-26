package org.spring.springboot.suanfa;

public class Dp {
    int[] w = {0, 2, 3, 4, 5};             //商品的体积2、3、4、5
    int[] v = {0, 3, 4, 5, 6};            //商品的价值3、4、5、6;
    int bagV = 8;
    int[][] dp = new int[5][9];
    static int[] item = new int[4];
    public static void main(String args[]){
        Dp dp = new Dp();
        dp.findMax();
        dp.findWhat(4,8);
        for(int i : item) {
            System.out.println(i);
        }
    }
    public void findMax() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= bagV; j++) {
                if (j < w[i])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
            }
        }
    }
    public void findWhat(int i,int j){
        if(i>0){
            if(dp[i][j]==dp[i-1][j]){
                item[i-1]=0;
                findWhat(i-1,j);
            }
            else if(dp[i][j]==dp[i-1][j-w[i]]+v[i]){
                item[i-1]=1;
                findWhat(i-1,j-w[i]);
            }
        }
    }
}
