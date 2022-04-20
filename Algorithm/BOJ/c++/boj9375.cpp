#include <string>
#include <iostream>
#include <map>
using namespace std;
void solve(){
    map <string,int> m;
    map<string, int>::iterator iter;
    int n,res=1;
    cin>>n;
    while(n--){
    	string a,b;
        cin>>a>>b;
        m[b]++;
    }
    for(iter=m.begin();iter!=m.end();iter++){
        res*=iter->second+1;
    }
    cout<<res-1<<'\n';
}
int main(){
    int T;
    cin>>T;
    while(T--)solve();
    return 0;
}

