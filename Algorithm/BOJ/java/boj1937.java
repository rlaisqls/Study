import java.util.*;

public class boj1937{ //욕심쟁이 판다
    static Scanner sc = new Scanner(System.in);
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int max(int a, int b){
        if(a>b)return a;
        else return b;
    }
    /*일정 칸에서 최대 몇칸까지 더 이동할 수 있는지 정보를 dp배열에 저장하고,
    dfs에서 그 칸을 재호출하면 중복탐색하지 않고 dp를 불러와서 그 값을 사용*/
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
