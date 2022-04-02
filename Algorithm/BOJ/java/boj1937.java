import java.util.*;

public class boj1937{
    static Scanner sc = new Scanner(System.in);
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int max(int a, int b){
        if(a>b)return a;
        else return b;
    }
    static int dfs(int x,int y, int n){
        if(dp[x][y]!=-1) return dp[x][y];
        int ret = 1;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(map[x][y] < map[nx][ny]) {
                if(dp[nx][ny]!=-1) ret = max(ret,dp[nx][ny]+1);
                else ret = max(ret, dfs(nx,ny,n)+1);
            }
        }
        return dp[x][y]=ret;
    } 
    public static void main(String[] args){
        int n = sc.nextInt();
        int res = 0;
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
                dp[i][j]=-1;
            }
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dp[i][j]!=-1) res = max(res,dp[i][j]);
                else res = max(res, dfs(i,j,n));
            }
        }
        System.out.println(res);
    }
}