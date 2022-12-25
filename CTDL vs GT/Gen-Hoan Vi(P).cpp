#include<iostream>
#include<iomanip>
#define MAX 100

/*
 Nhap n:3
 Ket Qua 1:123
 Ket Qua 2:132
 Ket Qua 3:213
 Ket Qua 4:231
 Ket Qua 5:312
 Ket Qua 6:321
 
 có 2 vòng lâp chính ; 1 lan swqp + 1 vòng swap
*/

using namespace std;
int x[MAX],n,dem=0;
bool ok = true;

void init(){
	cout << "\n Nhap n:"; cin >> n;
	for(int i=1 ; i<=n ;i++) // thiet lap x[] = (1,2,3,..,n)
		x[i] = i;
}

void result(){
	cout << "\n Ket Qua " << ++dem << ":";
	for(int i=1 ; i<=n ; i++)
		cout<<x[i]<<setw(1);
}

void nextPermutation(){
	int j = n-1;// xuat phat tu vi tri j = n-1
	while(j>0 && x[j]>x[j+1]) // tim chi so j sao cho x[j] < x[j+1]
		j--;
	if(j>0){
		int k = n; // xuat phat tu vi tri k = n;
		while(x[j]>x[k]) k--; // tim chi so k sao cho x[j] < x[k];
		int t = x[j]; x[j] = x[k]; x[k] = t; // doi cho x[j] va x[k]
		int r = j+1,s=n;
		while(r<=s){ // lat nguoc lai doan tu j+1,..,n
			t=x[r]; x[r]=x[s]; x[s]=t;
			r++; s--;
		}
	}else // neu la cau hinh cuoi cung
		ok = false; // ket thuc
}

int main(){
//	int x = 13;
//	cout<<setw(10)<<x<<endl;
	init();
	while(ok){
		result();
		nextPermutation();
	}
}
