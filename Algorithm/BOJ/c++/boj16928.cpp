#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;
/*각 칸에서 주사위를 1~6까지 굴리는 경우의 수 전부 탐색
 +뱀이나 사다리 있는 경우 순간이동시켜줌  
 칸이 100칸밖에 없어서 그냥 다 찾아주면 됨*/
int ch[101];
int mov[101];
int main(){
	int n,m;
	cin>>n>>m;
	for(int i=1;i<=100;i++){
		ch[i]=-1;
		mov[i]=i;
	}
	for(int i=0;i<n+m;i++){
		int a,b;
		cin>>a>>b;
		mov[a]=b;
	}
	queue <int> Q;
	Q.push(1);
	ch[1]=0;
	while(!Q.empty()){
		int x=Q.front(); Q.pop();
		for(int i=1;i<=6;i++){
			if(x+i>100)break;
			if(ch[mov[x+i]]==-1){
				ch[mov[x+i]]=ch[x]+1;
				Q.push(mov[x+i]);
			}
		}
	} 
	cout<<ch[100];
	return 0;
}
