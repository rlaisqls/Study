#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
int cnt[10000001];
int main(){
	ios_base :: sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int i,j,n,m;
	for(i=1;i<=10000000;i++)for(j=1;i*j<=10000000;j++)cnt[i*j]++;
	cin>>n>>m;
	for(i=0;i<n;i++){
		int a;
		cin>>a;
		if(a>m){
			int res=0;
			for(j=1;j<=sqrt(a)&&j<=m;j++){
				if(a%j==0){
					if(j<=m)res++;
					if(a/j<=m)res++;
				}
			}
			cout<<res<<'\n';
		}else{
			cout<<m/a+cnt[a]-1<<'\n';
		}
	}
}
