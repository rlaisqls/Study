#include<iostream>
#include<stack>
using namespace std;
int tree[10001];
void f(int s,int e){
	if(s>=e)return;
	int i;
	for(i=s+1;i<e;i++)if(tree[s]<tree[i])break;
	f(s+1,i); f(i,e);
	cout<<tree[s]<<'\n';
}
int main(){
	stack <int> S;
	int n,i=0;
	while(cin>>n)tree[i++]=n;
	f(0,i);
}
