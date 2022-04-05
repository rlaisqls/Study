import java.util.*;

public class boj2098{
    static Scanner sc = new Scanner(System.in);
    static int[][] map;
    static int[][] dp;
    static int n;
    static int INF = 9999999;
    static int min(int a, int b){
        return (a<b) ? a : b;
    }
    static int DFS(int x, int loot){
        if(loot == (1<<n) - 1) return ((map[x][0] != 0) ? map[x][0] : INF);
        if(dp[x][loot] != -1) return dp[x][loot];
        else dp[x][loot] = INF;
        for(int i = 0; i < n; i++){
            if((loot & (1<<i)) == 0 && map[x][i] != 0){
                int tmp = DFS(i, loot | (1<<i));
                dp[x][loot] = min(dp[x][loot], map[x][i] + tmp);
            }
        }
        return dp[x][loot];
    }
    public static void main(String[] args){
        n = sc.nextInt();
        map = new int[n][n];
        dp = new int[n][(1<<n)];
        int res = INF;
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(DFS(0, 1));
    }
}