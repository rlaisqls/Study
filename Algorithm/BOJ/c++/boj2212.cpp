#include<iostream>
#include<algorithm>
using namespace std;
int arr[10001];
int dist[1001];
/*그리디 진짜 너무 좋다
미칠 것 같다...*/
int main(){
	int i,j,n,k,res=0;
	cin>>n>>k;
	for(i=0;i<n;i++)cin>>arr[i];
	sort(arr,arr+n);
	for(i=0;i<n-1;i++)dist[i]=arr[i+1]-arr[i];
	sort(dist,dist+n-1);
	for(i=0;i<n-k;i++)res+=dist[i];
	cout<<res;
}
