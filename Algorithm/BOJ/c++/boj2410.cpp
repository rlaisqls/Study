#include<iostream>
#include<cmath>
using namespace std;
long long dp[1000001]={0,1,2};
int main(){
	for(int i=3;i<1000001;i++)dp[i]=(i%2)?dp[i-1]:(dp[i-1]+dp[i/2])%1000000000;
	/*
	n�� Ȧ���϶� ����� ������ ��Ÿ���� ����� ��
    	= n-1�� ����� ������ ��Ÿ���� + 1
	n�� ¦���ϋ� ����� ������ ��Ÿ���� ����� ��
		= N�� ¦���� ���, N-1 ����� �� + N/2 ����� �� 
	    if n==4
	    dp[3] 1+1+1   +1 
	    	  2+1     +1
		dp[2] 1+1     *2
		      2       *2
		�̷��� �� 4�� 
	*/ 
	long long res=0;
	int n;
	cin>>n;
	cout<<dp[n];

}
