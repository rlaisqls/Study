#include <iostream>
#include <algorithm>
using namespace std;
pair<int, int> N[100];
int dp[100][100001];
int main() {
	int n,k;
	cin>>n>>k;
	for(int i=0;i<n;i++)cin>>N[i].first>>N[i].second;
	for (int i=0;i<=k;i++){
		if(N[0].first<=i)dp[0][i]=N[0].second;
		else dp[0][i]=0;
	}
	for(int i=1;i<n;i++){
		for(int j=0;j<=k;j++){
			if(N[i].first<=j)dp[i][j]=max(dp[i-1][j],N[i].second+dp[i-1][j-N[i].first]);
			else dp[i][j]=dp[i-1][j];
		}
	}
	cout<<dp[n-1][k]<<endl;
}
