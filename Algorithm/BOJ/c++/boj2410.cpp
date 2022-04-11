#include<iostream>
#include<cmath>
using namespace std;
long long dp[1000001]={0,1,2};
int main(){
	for(int i=3;i<1000001;i++)dp[i]=(i%2)?dp[i-1]:(dp[i-1]+dp[i/2])%1000000000;
	/*
	n이 홀수일때 멱수의 합으로 나타내는 경우의 수
    	= n-1를 멱수의 합으로 나타낸거 + 1
	n이 짝수일떄 멱수의 합으로 나타내는 경우의 수
		= N이 짝수인 경우, N-1 경우의 수 + N/2 경우의 수 
	    if n==4
	    dp[3] 1+1+1   +1 
	    	  2+1     +1
		dp[2] 1+1     *2
		      2       *2
		이렇게 총 4개 
	*/ 
	long long res=0;
	int n;
	cin>>n;
	cout<<dp[n];

}
