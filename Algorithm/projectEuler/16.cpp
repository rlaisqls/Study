#include<iostream>
using namespace std;
int main(){
	int sum=0;
	char n[1001]="1\0";
	for(int i=0;i<1000;i++){
		cout<<n<<endl;
		int j,c=0;
		for(j=0;n[j]!='\0';j++){
			int m=(n[j]-'0');
			n[j]=((m*2)+c)%10+'0';
			c=((m*2)+c)/10;
		}
		if(c!=0){
			n[j]=(char)(c+'0');
			n[j+1]='\0';
		}
	}
	for(int i=0;n[i]!='\0';i++)sum+=n[i]-'0';
	cout<<sum;
}
