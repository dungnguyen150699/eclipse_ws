#include<iostream>
#include<iomanip>
/*
Back Tack là thuat tan duyet phan tu thu j1 n?u thoa man => j1,1 
Neu các phan tu j1 ko thoa thi quay lai j0, j2 ( chu nhi ) tim phan tu tiem theo
*/
#define MAX 100
using namespace std;

int x[MAX],n, dem = 0;

void init(){
	cout << "Nhap n: "; cin >> n;
}

void printPerResult(){
	cout<<"\n" << "So thu " << ++dem << " :";
	for(int i=0 ; i<n ; i++)
		cout << x[i] << setw(2);
}

void Try(int i){ // thuat toan quay lui
	for(int j=0 ; j<=1 ; j++){  // Duyet cac kha nang j danh cho X
		x[i] = j;
		if(i == n-1) // Neu i la thanh phan cuoi cung
			printPerResult(); // in KQ			
		else Try(i+1); // ta xac dinh tiep thanh phan xi+1;
	}
}

int main(){
	init();
//	printPerResult();
	Try(0);
}
