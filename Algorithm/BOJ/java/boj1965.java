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
                /*dp[i] = i번째 칸까지의 상자중에 가장 증가하는 부분 수열
                j < i인동안 쭉 탐색해서 i번째 칸 숫자보다 작은 칸(j) 중에 dp[j]가 가장 큰거 +1을 dp[i]에 넣어줌*/
            }
            res = (res > dp[i]) ? res : dp[i];
        }
        System.out.println(res);
    }
}
