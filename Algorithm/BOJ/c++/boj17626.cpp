#include<iostream>
#include<cstring>
using namespace std;
int dp[50001];
int main(){
	int n; dp[0]=0; dp[1]=1;
	for(int i=0;i<50000;i++)
		for(int j=1;i+j*j<=50000;j++)
		dp[i+j*j]=min((dp[i+j*j]==0)?5:dp[i+j*j],dp[i]+1);
	cin>>n;
	cout<<dp[n];
}
