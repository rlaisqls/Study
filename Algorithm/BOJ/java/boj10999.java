import java.util.*;
/*2042번 문제에 Lazy Propagation 추가만 했다.
옛날에 이걸 들어본 적이 있었는데 자세히 기억이 나진 않아서 설명보고 코드 짜봤다.
기본적인 구조는 세그먼트 트리인데, 많은 정점을 갱신하는 시간을 줄이기 위해 lazy 배열에 그 수를 더해줘야한다고 예약 걸어둔 다음에 그 정점에 실제로 방문할때 적용시켜주는 방식*/
//이걸 다시 짤 수 있을지는 모르겠다.
public class boj10999{
    static Scanner sc = new Scanner(System.in);
    static long[] Tree;
    static long[] arr;
    static long[] Lazy;
    static int log2(int x){return (int)Math.ceil(Math.log10(x)/Math.log10(2));}
    static long sumTree(int l,int r,int x,int gl,long gr){
        if(Lazy[x]!=0){//lazy 남아있으면
            Tree[x]+=Lazy[x]*((r-l)+1);//정점에 적용
            if(l!=r){Lazy[x*2]+=Lazy[x];Lazy[x*2+1]+=Lazy[x];}//물려주고
            Lazy[x] = 0;//lazy[x]는 0으로 (나중에 그 정점에 가면 적용시킬 예정)
        }
        if(r<gl||gr<l) return 0;
        else if(gl<=l&&r<=gr) return Tree[x];
        else return sumTree(l,(l+r)/2,x*2,gl,gr)+sumTree((l+r)/2+1,r,x*2+1,gl,gr);
    }
    static void updateTree(int l,int r,int x,int gl,int gr,long val){
        if(Lazy[x]!=0){//위와 동일
            Tree[x]+=Lazy[x]*((r-l)+1);
            if(l!=r){Lazy[x*2]+=Lazy[x];Lazy[x*2+1]+=Lazy[x];}
            Lazy[x]=0;
        }
        if(r<gl||gr<l) return;
        else if(gl<=l&&r<=gr){
            Tree[x]+=val*((r-l)+1);
            if(l!=r){Lazy[x*2]+=val;Lazy[x*2+1]+=val;}//lazy배열 값에 더해줌
        }else{
            updateTree(l,(l+r)/2,x*2,gl,gr,val);
            updateTree((l+r)/2+1,r,x*2+1,gl,gr,val);
            Tree[x]=Tree[x*2]+Tree[x*2+1];//자식의 값이 바뀌었을 수 있으니 부모도 갱신
        }
    }
    static long makeTree(int l,int r,int x){
        if(l==r) return Tree[x]=arr[l];
        else return Tree[x]=makeTree(l,(l+r)/2,x*2)+makeTree((l+r)/2+1,r,x*2+1);
    }
    public static void main(String[] args){
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        arr = new long[n+1];
        Tree = new long[(int)Math.pow(2,log2(n)+1)+1];
        Lazy = new long[(int)Math.pow(2,log2(n)+1)+1]; 
        for(int i = 1; i <= n; i++)arr[i] = sc.nextLong();
        makeTree(1,n,1);
        for(int i = 0; i < m + k; i++){
            int c = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(c==1){ //update
                long d = sc.nextLong();
                updateTree(1,n,1,a,b,d);
            }else if(c==2){ //print
                System.out.println(sumTree(1,n,1,a,b));
            }
        }
        sc.close();
    }
}