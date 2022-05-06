#include<iostream>
#include<cstring>
using namespace std;
bool en[10000001];
bool ch[10000001];
long long ss(long long n){
	int m=n;
	int ret=0;
	while(m>0){
		ret+=(m%10)*(m%10);
		m/=10;
	}
	return ret;
}
bool f(long long n){
	long long x=ss(n);
	if(x==1||ch[x]==1){
		ch[x]=0;
		return 0;
	}
	ch[n]=1;
	if((x==89||en[x]||f(x))){
		en[n]=1;
		ch[n]=0;
		return 1;
	}else{
		ch[n]=0;
		return 0;
	}
}
int main(){
	long long cnt=0;
	for(int i=1;i<=10000000;i++){
		if(f(i))cnt++;
	}
	cout<<cnt;
}
