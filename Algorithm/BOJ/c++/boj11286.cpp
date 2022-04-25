#include<iostream>
#include<queue>
using namespace std;
int abs(int n){return (n>0)?n:-n;}
class cmp{
public:
	bool operator()(const int& a,const int& b){
		if(abs(a)==abs(b))return a>b;
		else return abs(a)>abs(b);
	}
};
int main(){
	priority_queue <int,vector<int>,cmp> Q;
	int n;
	cin>>n;
	while(n--){
		int x;
		scanf("%d",&x);
		if(x==0){
			if(Q.empty())printf("0\n");
			else printf("%d\n",Q.top()),Q.pop();
		}
		else Q.push(x);
	}
}
