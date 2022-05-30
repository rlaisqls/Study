#include<iostream>
using namespace std;
int main(){
	string myans="DCBDBCBCACBCBCACCACABAABBCBCABBDBACACCCBCBBADAABCBCADDAACCABCDABBDBDBCAACCDCACBADBDCBCBBAADCDDCBACCACBCBDABBBBBCBDBBCDADDACBCDBDBACCBCBBDCBADCCDAACBBCBCDACCACDCCBBADBDDBACDDDCABCDCACBADBCCDABDDCDCCCAC";
	string ans="DCBDBCBCACBCBCACACCABAABBCBCABBDBCACDABBCABADABDCCCADABABCABBDADBCADBCAACCDCCCDBDADADCBBBADCDDCACBDADCCBAABABBBCBDAACDCDDCBBCDADBCCDACBBDABADCCDAACDBABCDACBBCBCDCDCAADDBCDACBBCBCDAADCABBCCADDDACBDBCAC";
	int i;
	int cnt=0;
	int lcCnt=0;
	int rcCnt=0;
	for(i=0;i<100;i++){
		if(i!=0&&i%10==0)cout<<'\n';
		if(ans[i]==myans[i]){
			cnt++;
			lcCnt++;
			cout<<"O";
		}
		else cout<<"X";
		
	}
	cout<<'\n';
	cout<<'\n';
	for(;i<200;i++){
		if(i!=100&&i%10==0)cout<<'\n';
		if(ans[i]==myans[i]){
			cnt++;
			rcCnt++;
			cout<<"O";
		}
		else cout<<"X";
		
	}
	cout<<'\n';cout<<'\n';
	for(i=0;i<100;i++){
		if(i!=0&&i%10==0)cout<<'\n';
		printf("%4d",i+1);
		if(ans[i]==myans[i])cout<<": :   ";
		else cout<<":"<<myans[i]<<":"<<ans[i]<<"  ";
	}
	cout<<'\n';cout<<'\n';
	for(;i<200;i++){
		if(i!=100&&i%10==0)cout<<'\n';
		printf("%4d",i+1);
		if(ans[i]==myans[i])cout<<": :   ";
		else cout<<":"<<myans[i]<<":"<<ans[i]<<"  ";
	}
	cout<<'\n';cout<<'\n';
	cout<<"Æ²¸° °¹¼ö: "<<cnt<<'\n';
	cout<<"LC Æ²¸° °¹¼ö: "<<lcCnt<<'\n';
	cout<<"RC Æ²¸° °¹¼ö: "<<rcCnt<<'\n';
}
