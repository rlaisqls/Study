#include<iostream>
#include<queue>
using namespace std;
int main(){
	queue <pair<int,int> > Q;
	int a,b;
	cin>>a>>b;
	Q.push(make_pair(a,1));
	while(!Q.empty()){
		int x=Q.front().first;
		int l=Q.front().second;
		if(x==b){cout<<l;return 0;}
		Q.pop();
		if((long long)x*2<=b) Q.push(make_pair(x*2,l+1));
		if((long long)x*10+1<=b) Q.push(make_pair(x*10+1,l+1));
	}
	cout<<"-1";
	return 0;
}
