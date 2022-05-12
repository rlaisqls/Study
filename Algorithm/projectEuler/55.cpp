#include<iostream>
using namespace std;
int reverse(int n){
	int m=0;
	while(n!=0){
		m*=10;
		m+=n%10;
	}	
	return m;
}
int check(int n){
	int r=reverse(n);
	int m=n+r;
	
	return m;	
}
int main(){
	for(i=1;i<=10000;i++){
		
	}
}
