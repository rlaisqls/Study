#include<iostream>
using namespace std;
long long dp[21][21];
int main(){
	for(int i=0;i<=20;i++){
		for(int j=0;j<=20;j++){
			if(i==0||j==0)dp[i][j]=1;
			else dp[i][j]=dp[i-1][j]+dp[i][j-1];
		}
	}
	cout<<dp[20][20];
}
