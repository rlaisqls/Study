import java.util.*;
public class boj2229{
    static Scanner sc = new Scanner(System.in);
    static int[] dp;
    static int[] arr;
    static int[][] gap;
    static int max(int a, int b){ return (a>b) ? a : b; }
    public static void main(String[] args){
        int n = sc.nextInt();
        dp = new int[n+1];
        arr = new int[n+1];
        gap = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            arr[i] = sc.nextInt();
            for(int j = 1; j <= i; j++) gap[j][i] = Math.abs(arr[i]-arr[j]);
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++) dp[i] = max(dp[i], dp[j-1] + gap[j][i]);
        }
        System.out.println(dp[n]);
    }
}