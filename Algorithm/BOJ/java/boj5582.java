import java.util.*;
public class boj5582{
    static Scanner sc = new Scanner(System.in);
    static int[][] dp;
    static int max(int a,int b){
        return (a > b) ? a : b;
    }
    public static void main(String[] args){
        String str1 = " "+sc.nextLine();
        String str2 = " "+sc.nextLine();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int res = 0;
        dp = new int[str1.length()][str2.length()];
        for (int i = 1; i < str1.length(); i++){
            for (int j = 1; j < str2.length(); j++){
                if(arr1[i] == arr2[j]) dp[i][j] = dp[i-1][j-1]+1;
                res = max(res, dp[i][j]);
                //else dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
            }
        }
        System.out.println(res);
    }
}