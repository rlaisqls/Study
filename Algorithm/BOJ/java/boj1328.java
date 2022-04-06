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
                    dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k]*(i-2);
                    /* 
                    dp[i][j][k] = i개의 빌딩이 있는데 왼쪽에서 j개가 보이고 오른쪽에서 k개가 보일때의 경우의 수
                    빌딩을 큰거부터 작은거 순서로 하나 하나 배치한다고 생각했을때, 
                    맨 왼쪽에 빌딩을 배치하는 경우의 수 = dp[i-1][j-1][k] 의 경우의 수랑 똑같음
                    맨 오른쪽에 빌딩을 배치하는 경우의 수 = dp[i-1][j][k-1] 의 경우의 수랑 똑같음
                    다른 빌딩에 가려지게 배치하는 경우의 수 (큰거에서 작은거 순서로 배치하기 때문에 양 끝에 놓는거 아니면 다 가려짐)
                    (그러면 옆에서 보여지는 빌딩의 갯수는 똑같고, 가려지게 배치할 수 있는 경우의 수를 곱해서 더해주면 됨)
                        예)
                        2 5 4 7 3 6 (i=7)
                        이런식으로 있다고 쳤을때 옆에서 보여지는 빌딩의 갯수가 같도록 높이 1의 빌딩을 놓는 경우의 수
                        2 5 4 7 3 6 
                         ^ ^ ^ ^ ^
                        i-2 = 5가 됨.
                    = dp[i-1][j][k]*(i-2) 
                    점화식 = dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k]*(i-2);
                    */
                    dp[i][j][k] %= 1000000007;
                }
            }
        }
        System.out.print(dp[n][l][r] % 1000000007);
        sc.close();
    }
}
