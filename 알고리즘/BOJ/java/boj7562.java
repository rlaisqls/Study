import java.util.*;
class pair{
    private int x;
    private int y;
    private int l;
    pair (int x,int y,int l){
        this.x = x;
        this.y = y;
        this.l = l;
    }
    public int X(){return x;}
    public int Y(){return y;}
    public int L(){return l;}
}

public class boj7562{
    static Scanner sc = new Scanner(System.in);
    static int[][] ch;
    static int[] dx = {-2,-2,-1,-1,1,1,2,2};
    static int[] dy = {1,-1,2,-2,2,-2,1,-1};
    static void solve(){
        Queue <pair> Q = new LinkedList<>(); 
        int size = sc.nextInt();
        pair s = new pair(sc.nextInt(),sc.nextInt(),0);
        pair e = new pair(sc.nextInt(),sc.nextInt(),0);
        ch = new int[size][size];
        Q.add(s);
        ch[s.X()][s.Y()]=1;
        while (!Q.isEmpty()){
            pair l = Q.poll();
            if(l.X()==e.X()&&l.Y()==e.Y()){
                System.out.println(l.L());
                return;
            }
            for(int i = 0; i < 8; i++){
                pair n = new pair(l.X() + dx[i], l.Y() + dy[i], l.L()+1);
                if(n.X() < 0 || n.Y() < 0 || n.X() >= size || n.Y() >= size)continue;
                if(ch[n.X()][n.Y()]==0){
                    Q.add(n);
                    ch[n.X()][n.Y()]=1;
                }
            }
        }

    } 
    public static void main(String args[]){
        int T = sc.nextInt();
        while(T-- > 0) solve();
    }
}