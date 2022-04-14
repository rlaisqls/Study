#include<iostream>
using namespace std;
long long Tree[3000001],Lazy[3000001],arr[3000001];
//10999���̶� �Ȱ��� Lazy Propagation�����ε�, ��ȸ������ �������� �������� ����
//�׽�Ʈ������ �ڹ��ڵ带 �����غôµ� �� ������ �ڹ� �ڵ�ĸ�� ��� �ð��ʰ� ���淡 c++�� ¥�ô�.
//�ڼ��� ������ boj10999.java ������ �����ϱ� �ٶ� 
long long sumTree(int l,int r,int x,int gl,long gr){
    if(Lazy[x]!=0){
        Tree[x]+=Lazy[x]*((r-l)+1);
        if(l!=r){
            Lazy[x*2]+=Lazy[x];
            Lazy[x*2+1]+=Lazy[x];
        }
        Lazy[x]=0;
    }
    if(r<gl||gr<l) return 0;
    else if(gl<=l&&r<=gr)return Tree[x];
    else return sumTree(l,(l+r)/2,x*2,gl,gr)+sumTree((l+r)/2+1,r,x*2+1,gl,gr);
}
void updateTree(int l,int r,int x,int gl,int gr,long val){
    if(Lazy[x]!=0){ 
        Tree[x]+=Lazy[x]*((r-l)+1);
        if(l!=r){
            Lazy[x*2]+=Lazy[x];
            Lazy[x*2+1]+=Lazy[x];
        }
        Lazy[x]=0;
	}
    if(r<gl||gr<l) return;
    else if(gl<=l&&r<=gr){
        Tree[x]+=val*((r-l)+1);
        if(l!=r){
            Lazy[x*2]+=val;
            Lazy[x*2+1]+=val;
        }
    }else{
        updateTree(l,(l+r)/2,x*2,gl,gr,val);
        updateTree((l+r)/2+1,r,x*2+1,gl,gr,val);
        Tree[x]=Tree[x*2]+Tree[x*2+1];
    }
}
long long makeTree(int l,int r,int x){
    if(l==r) return Tree[x]=arr[l];
    else return Tree[x]=makeTree(l,(l+r)/2,x*2)+makeTree((l+r)/2+1,r,x*2+1);
}
int main(){
	int n,m,k;
	cin>>n>>m>>k;
	for(int i=1;i<=n;i++)cin>>arr[i];
	makeTree(1,n,1);
	for(int i=1;i<=m+k;i++){
		int a,b,c;
		long long d;
		cin>>c>>a>>b;
		if(c==2){cin>>d;updateTree(1,n,1,a,b,d);}
		else cout<<sumTree(1,n,1,a,b)<<'\n';
	}
}
