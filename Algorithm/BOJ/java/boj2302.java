import java.util.*;

public class boj2302 {
    static Scanner sc = new Scanner(System.in);
    static int[] dp;
    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int res = 1;
        int p = 0;
        dp = new int[n+1];
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
        for(int i = 0; i < m; i++) {
            int vip = sc.nextInt();
            res*=dp[vip - p - 1];
            p = vip;
        }
        System.out.println(res * dp[n - p]);
    }
}
