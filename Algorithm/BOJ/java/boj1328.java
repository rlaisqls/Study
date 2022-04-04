import java.util.*;
public class boj1328 {
    static Scanner sc = new Scanner(System.in);
    static long[][][] dp = new long[101][101][101];
    public static void main(String[] args){
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        dp[1][1][1]=1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= l; j++){
                for(int k = 1; k <= r; k++){
                    dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j][k-1] +dp[i-1][j][k]*(i-2);
                    dp[i][j][k] %= 1000000007;
                }
            }
        }
        System.out.print(dp[n][l][r] % 1000000007);
        sc.close();
    }
}
