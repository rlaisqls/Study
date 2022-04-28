#include<iostream>
using namespace std;
int ch[500001];
int un[500001];
int find(int x){
	if(x==un[x])return x;
	else return un[x]=find(un[x]);
}
bool Union(int a,int b){
	a=find(a),b=find(b);
	if(a!=b){
		un[a]=b;
		return 0;
	}else return 1;
}
int main(){
	int n,m;
	cin>>n>>m;
	for(int i=0;i<n;i++)un[i]=i;
	for(int i=1;i<=m;i++){
		int a,b;
		cin>>a>>b;
		if(Union(a,b)){
			cout<<i;
			return 0;
		}
	}
	
	cout<<0;
}
