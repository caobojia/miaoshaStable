package com.imooc.miaoshaproject.mq;

public class Demo1 {
    public static int uniquePaths(int m, int n) {
        if(m < 1 || n < 1) return 0;
        int[][] resilt = new int[m][n];
        resilt[0][0] = 0;
        for(int i = 1 ;i < resilt.length;i ++){
            resilt[i][0] = 1;
        }
        for(int i = 1;i < resilt[0].length;i ++){
            resilt[0][i] = 1;
        }
        for(int i = 1;i < resilt.length; i++){
            for(int j =1;j < resilt[0].length;j ++){
                resilt[i][j] = resilt[i - 1][j] + resilt[i][j - 1];
            }
        }
        return resilt[m - 1][n - 1];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(m ==0 || n == 0) return 0;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i =0;i <m;i ++){
            for(int j =0;j < n;j ++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else if(i == 0 && j > 0){
                    dp[0][j] = dp[0][j - 1];
                }else if(j == 0 && i > 0){
                    dp[i][0] = dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        uniquePaths(3,3);
    }
}
