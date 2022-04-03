import java.util.*;

public class boj2011 {
    static Scanner sc = new Scanner(System.in);
    static char[] S;
    static int[] dp;
    public static void main(String[] args) {
        String tmp = ' '+sc.nextLine();
        S = tmp.toCharArray();
        dp = new int[tmp.length()];
        dp[0] = 1;
        for(int i = 1; i < tmp.length(); i++){
            dp[i] = 0;
            if (S[i] > '0') dp[i] += dp[i-1] % 1000000;
            if (S[i-1] == '1'||(S[i-1] == '2' && S[i] <= '6')) dp[i] += dp[i-2] % 1000000;
        }
        System.out.println(dp[tmp.length()-1] % 1000000);
        sc.close();
    }    
}
