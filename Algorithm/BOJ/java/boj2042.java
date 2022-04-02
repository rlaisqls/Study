import java.util.*;

public class boj2042{
    static Scanner sc = new Scanner(System.in);
    static long[] Tree;
    static long[] arr;
    static int log2(int x,int base){
        return (int) Math.ceil(Math.log10(x)/Math.log10(2));
    }
    static long sumTree(int l, int r, int x, int gl, long gr){
        if(gl<=l&&r<=gr)return Tree[x];
        else if(gr<=(l+r)/2) return sumTree(l,(l+r)/2,x*2,gl,gr);
        else if((l+r)/2+1<=gl) return sumTree((l+r)/2+1,r,x*2+1,gl,gr);
        else if(gl<=(l+r)/2&&(l+r)/2<=gr)return sumTree(l,(l+r)/2,x*2,gl,gr)+sumTree((l+r)/2+1,r,x*2+1,gl,gr);
        return 0;
    }
    static void updateTree(int l, int r, int x, int goal, long val){
        Tree[x]+=val;
        if(l==r) return;
        if(l<=goal&&goal<=(l+r)/2) updateTree(l,(l+r)/2,x*2,goal,val);
        else if((l+r)/2+1<=goal&&goal<=r) updateTree((l+r)/2+1,r,x*2+1,goal,val);
    }
    static long makeTree(int l, int r, int x){
        if(l==r) Tree[x] = arr[l];
        else Tree[x] = makeTree(l,(l+r)/2,x*2)+makeTree((l+r)/2+1,r,x*2+1);
        return Tree[x];
    }
    public static void main(String[] args){
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        arr = new long[n];
        Tree = new long[(int)Math.pow(2,log2(n,2)+1)+1]; 
        for(int i = 0; i < n; i++)arr[i] = sc.nextLong();
        makeTree(0,n-1,1);
        for(int i = 0; i < m + k; i++){
            int c = sc.nextInt();
            int a = sc.nextInt();
            long b = sc.nextLong();
            if(c==1){
                long tmp = arr[a-1];
                arr[a-1] = b; 
                updateTree(0,n-1,1,a-1,b-tmp);
            }else if(c==2){
                System.out.println(sumTree(0,n-1,1,a-1,b-1));
            }
        }
        sc.close();
    }
}