#include<iostream>
using namespace std;
//딱 코포 A번 문제 감성 
int main(){
	int n,m;
	string S;
	cin>>n>>m>>S;
	
	int res=0,cnt=0;
	for(int i=1;i<m-1;i++){
		if(S[i-1]=='I'&&S[i]=='O'&&S[i+1]=='I'){
			cnt++; i++;
		}else{
			res+=((cnt-n)+1>0)?(cnt-n)+1:0;
			cnt=0;
		}
	}
	res+=((cnt-n)+1>0)?(cnt-n)+1:0;
	cout<<res;
}
