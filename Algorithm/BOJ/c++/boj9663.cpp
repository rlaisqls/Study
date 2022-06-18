#include<iostream>
using namespace std;
bool sch[15], dch[30], bdch[30];
int n, cnt=0;
void dfs(int l){
	if(l == n){ cnt++; return; }
	for(int i=0; i<n; i++){
		if(dch[l+i] == 0 && bdch[l+n-i-1] == 0 && sch[i] == 0) {
			dch[l+i]=1;sch[i]=1; bdch[l+n-i-1] = 1;
			dfs(l+1);
			sch[i]=0; dch[l+i]=0; bdch[l+n-i-1]=0;
		}
	}
}
int main(){
	cin>>n;
	dfs(0);
	cout<<cnt;
	return 0;
}
