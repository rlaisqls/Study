import java.util.*;

public class boj2213{
    /* 진짜 해도해도 어렵다 dp
    아직 이해를 못한 부분이 좀 있어서 내일 코포끝나고 수정할 예정*/
    static Scanner sc = new Scanner(System.in);
    static Vector <ArrayList <Integer> > V;
    static Vector <Integer> P;
    static int[][] dp;
    static int[] w;
    static boolean[] ch;
    static int max(int a, int b){ return (a > b) ? a : b; }
    static void f(int x, int p){
        dp[x][0] = 0; 
        dp[x][1] = w[x];
        for(int i = 0; i < V.get(x).size(); i++) {
            int nx = V.get(x).get(i);
            if(nx == p) continue;
            f(nx, x);
            dp[x][0] += max(dp[nx][0], dp[nx][1]);
            dp[x][1] += dp[nx][0];
        }
    }
    static void tracing(int x, int p){
        if(dp[x][1] > dp[x][0] && !ch[p]) {
            P.add(x);
            ch[x] = true;
        }
        for(int i = 0; i < V.get(x).size() ; i++){
            int nx = V.get(x).get(i);
            if(nx != p) tracing(nx, x);
        }
    }
    public static void main(String[] args){
        int n = sc.nextInt();
        V = new Vector <ArrayList <Integer> >();
        P = new Vector <Integer> ();
        dp = new int [n+1][2];
        ch = new boolean [n+1];
        w = new int[n+1];
        for(int i = 0; i <= n; i++) V.add(new ArrayList <Integer>()); 
        for(int i = 1; i <= n; i++) w[i] = sc.nextInt();
        for(int i = 1; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            V.get(a).add(b);
            V.get(b).add(a);
        }
        f(1, 0);
        tracing(1, 0);
        System.out.println(max(dp[1][0], dp[1][1]));
        Collections.sort(P);
        for(int i = 0; i < P.size(); i++) System.out.print(P.get(i)+" ");
    }
}