#include<iostream>
using namespace std;
int arr[2][10001];
int main(){
	int n,l,p=0,cnt=0;
	cin>>n>>l;
	for(int i=0;i<n;i++){
		cin>>arr[0][i]>>arr[1][i];
	}
	for(int i=0;i<n;i++){
		if(p<arr[0][i])p=arr[0][i];
		cout<<"---------";
		while(arr[1][i]>p){
			p+=l;
			cout<<p<<'\n';
			cnt++;
		}
	}
	cout<<cnt;
}
