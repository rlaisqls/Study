import java.util.*;
class trip {
    private int s;
    private int e;
    private int v;
    trip(int s, int e, int v){
        this.s = s;
        this.e = e;
        this.v = v;
    }
    public int S(){return s;}
    public int E(){return e;}
    public int V(){return v;}
}
public class boj2157{
    static Scanner sc = new Scanner(System.in);
    static ArrayList <trip> arr;
    static int[][] dp;
    public static int max(int a, int b){ return (a > b) ? a : b; }
    public static void main(String[] args){
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int res = -1;
        arr = new ArrayList<trip>();
        dp = new int[301][301];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        for(int i = 0; i < k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if(a > b) continue;
            arr.add(new trip(a, b, c));
        }
        Collections.sort(arr, new Comparator<trip>(){
            public int compare(trip t1, trip t2) {
                if(t1.S()==t2.S()) return ((t1.E() < t2.E()) ? -1 : 1);
                else return ((t1.S() < t2.S()) ? -1 : 1);
            }
        });
        dp[1][0] = 0;
        k = arr.size();
        for(int i = 0; i < k; i++){
            trip x = arr.get(i);
            for(int j = 1; j < m; j++){
                if(dp[x.S()][j-1]==-1) continue;
                dp[x.E()][j] = max(dp[x.E()][j], dp[x.S()][j-1] + x.V());
            }
        }
        for(int i = 1; i < m; i++){
            res = max(res,dp[n][i]);
        }
        System.out.println(res);
    }
}