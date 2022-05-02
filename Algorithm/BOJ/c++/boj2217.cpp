#include<iostream>
#include<algorithm>
using namespace std;
int arr[100001];
bool cmp(int a,int b){
	return a>b;
}
int main(){
	int i,n,res=0;
	cin>>n;
	for(i=1;i<=n;i++)cin>>arr[i];
	sort(arr+1,arr+n+1,cmp);
	for(i=1;i<=n;i++){
		if(res<arr[i]*i)res=arr[i]*i;
	}
	cout<<res;
}// 가장 작은거 * k 
