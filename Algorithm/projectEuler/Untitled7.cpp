#include<iostream>
#include<vector>
using namespace std;
char map[15][15];
int num[15][15];
vector <vector<int> > adj;
vector <int> A,B,P;
vector <int> CH;
int chcnt=1,n,m;
int dx[]={-1,-1,0,0,1,1};
int dy[]={-1,1,-1,1,-1,1};
bool DFS(int a){
	if(CH[a]==chcnt)return 0;
	CH[a]=chcnt;
	for(int i=0;i<adj[a].size();i++){
		int b=adj[a][i];
		if(B[b]==-1||DFS(B[b])){
			A[a]=b;
			B[b]=a;
			return 1;
		}
	}
	return 0;
}
int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	int T;
	cin>>T;
	while(T--){
		P.clear();
		cin>>n>>m;
		adj=vector<vector<int> >(n*m+1);
		CH=vector<int>(n*m+1);
		A=vector<int>(n*m+1,-1);
		B=vector<int>(n*m+1,-1);	
		int crash=0,cnt=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				cin>>map[i][j];
				if(map[i][j]=='x')crash++;
				if(j%2==0)P.push_back(cnt);
				num[i][j]=cnt++;
			}
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j+=2){
				if(map[i][j]=='x')continue;
				for(int k=0;k<6;k++){
					int x=i+dx[k];
					int y=j+dy[k];
					if(x<0||y<0||x>=n||y>=m||map[x][y]=='x')continue;
					adj[num[i][j]].push_back(num[x][y]);
				}
			}
		}
		int res=0;
		for(int i=0;i<P.size();i++){
			chcnt++;
			res+=DFS(P[i]);
		}
		cout<<n*m-res-crash<<'\n';
	}
}
