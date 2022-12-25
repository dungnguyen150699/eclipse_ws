#include<iostream>
#include<iomanip>
#define MAX 100
using namespace std;

int x[MAX],n, k, dem = 0;
bool ok = true;

void init(){
	cout << "Nhap n,k: "; cin >> n >> k;
	for(int i=1 ; i<= k ; i++)
		x[i] = i;
}

void printPerResult(){
	cout<<"\n" << "So thu " << ++dem << " :";
	for(int i=1 ; i<=k ; i++)
		cout << x[i] << setw(2);
}

void nextPermutation(){// sinh tap con k phan tu tu tap hop cob bat ky
	int i = k;
	while(i>0 && x[i] == n-k+i) // tim i sao cho xi != n-k+i;
		i--;
//	int doi;
//	cout << i << "tu tu nha"; cin >> doi;
	if(i>0){ // Neu chua phai la tap con cuoi cung
		x[i] = x[i] + 1;
		for(int j=i+1 ; j<=k ; j++){// cac vi tri j tu i+1,...k;  // Phai la i+1 vi neu la i thi no dang dung yen tai chô
			x[j] = x[i] + j - i;   
		}
	}
	else // Neu la tap con cuoi cung
		ok = false; // ta ket thuc duyet;
}

int main(){
	init();
	while(ok){
		printPerResult();
		nextPermutation();
	}
}
