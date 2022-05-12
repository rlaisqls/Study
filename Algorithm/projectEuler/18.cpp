#include<iostream>
using namespace std;
int arr[101][101],dp[101][101];
int main(){
	int i,j,n,m=0;
	cin>>n;
	for(i=1;i<=n;i++){
		for(j=1;j<=i;j++){
			cin>>arr[i][j];
		}
	}
	dp[1][1]=arr[1][1];
	for(i=2;i<=n;i++){
		dp[i][1]=dp[i-1][1]+arr[i][1];
		for(j=2;j<i;j++){
			dp[i][j]=max(dp[i-1][j-1],dp[i-1][j])+arr[i][j];
		}
		dp[i][i]=dp[i-1][i-1]+arr[i][i];
	}
	for(i=1;i<=n;i++){
		for(j=1;j<=i;j++){
			cout<<dp[i][j]<<" ";
			m=max(m,dp[i][j]);
		}
		cout<<endl;
	}
	cout<<m;
}
