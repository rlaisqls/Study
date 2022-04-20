#include<iostream>
#include<stack>
#define idx first
#define hei second
using namespace std;
typedef long long ll;
int main() {
    int n;
    while(1){
        cin>>n;
        if(n==0)break;
        stack <pair<int,int> > st;
        ll res=0;
        for(int i=0;i<=n;i++){
            int h,left;
            if(i<n)cin>>h;
            else h=-1;
            left=i;
			while(!st.empty()&&st.top().hei>h){
                res=max(res,((ll)i-st.top().idx)*st.top().hei);
                left=st.top().idx;
                st.pop();
            }
            st.push(make_pair(left,h));
        }
        cout<<res<<'\n';
    }
    return 0;
}
