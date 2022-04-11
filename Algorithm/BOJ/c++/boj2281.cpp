#include<iostream>
using namespace std;
int dp[1001][1001];
int a[1001];
int main(){
	int i,j,k,n,m,p,res=999999999;
	for(i=0;i<1001;i++)for(j=0;j<1001;j++)dp[i][j]=999999999; 
	cin>>n>>m;
	for(i=1;i<=n;i++){
		cin>>a[i];
	}
	dp[0][0]=0;
	/*dp[i][j]
	j줄에 i번쨰 이름까지 채웠을떄 남은칸 제곱 최솟값 
	j+1줄에 i+1번째 이름부터 가능한 이름까지 채워서 최솟값으로 갱신
	그걸 반복 (3중 for문)*/
	for(i=1;i<=n;i++){
		for(j=1;j<=n;j++){
			if(dp[i-1][j-1]==999999999)continue;
			p=0;
			for(k=j;k<=n;k++){
				if(p+a[k]<=m){
					if(k==n){dp[i][k]=min(dp[i][j],dp[i-1][j-1]);break;}
					p+=a[k];
					dp[i][k]=min(dp[i][k],dp[i-1][j-1]+(m-p)*(m-p));
					p++;
				}else break;
			}
		}res=min(res,dp[i][n]);
	}
	cout<<res;
}
