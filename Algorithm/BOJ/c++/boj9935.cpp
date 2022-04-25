#include<iostream>
#include<cstring>
#include <string>
using namespace std;
int main(){
	int i,j;
	string a,b,res="";
	cin>>a>>b; 
	for(i=0;i<a.length();i++){
		res+=a[i];
		if(a[i]==b[b.length()-1]&&res.length()>=b.length()){
			bool flag=1;
			for(j=0;j<b.length();j++){
				if(res[res.size()-b.length()+j]!=b[j]){flag=0;break;}
			}
			if(flag)res.erase(res.length()-b.length(), b.length());
		}
	}
	if(res.empty())cout<<"FRULA";
	else cout<<res;
}
