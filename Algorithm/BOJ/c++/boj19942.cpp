#include<iostream>
#include<vector>
using namespace std;
struct S{
	int mp;
	int mf;
	int ms;
	int mv;
	int mc;
}arr[16];
int n, m=2147000000;
int gp,gf,gs,gv;
vector <int> V;
void func(int l,int path,int p,int f,int s,int v,int c){
	if(l==n){
		if(p>=gp&&f>=gf&&s>=gs&&v>=gv){
			if(m>c){
				m=c;
				V.clear();
				for(int i=0;i<l;i++){
					if(path&(1<<i))V.push_back(i+1);
				}
			}
		}
	}else{
		if(!(arr[l].mp==0&&arr[l].mf==0&&arr[l].ms==0&&arr[l].mv==0))
			func(l+1,path|(1<<l),p+arr[l].mp,f+arr[l].mf,s+arr[l].ms,v+arr[l].mv,c+arr[l].mc);	
		func(l+1,path,p,f,s,v,c);	
	}
}
int main(){
	cin>>n;
	cin>>gp>>gf>>gs>>gv;
	for(int i=0;i<n;i++){
		cin>>arr[i].mp>>arr[i].mf>>arr[i].ms>>arr[i].mv>>arr[i].mc;
	}
	func(0,0,0,0,0,0,0);
	if(m==2147000000) cout<<"-1";
	else{
		cout<<m<<"\n";
		for(int i=0;i<V.size();i++) cout<<V[i]<<" ";
	}
}
