import java.util.*;

public class boj1965 {
    static Scanner sc = new Scanner(System.in);
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) {
        int n = sc.nextInt();
        int res = 0;
        arr = new int[n];
        dp = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]) dp[i] = (dp[i] > dp[j]+1) ? dp[i] : dp[j]+1;
            }
            res = (res > dp[i]) ? res : dp[i];
        }
        System.out.println(res);
    }
}
