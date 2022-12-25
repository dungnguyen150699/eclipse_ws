#include<iostream>
#include<iomanip>

using namespace std;

int n,x[100];
bool ok = true;

void init(){
	for(int i=0 ; i<n ; i++){
		x[i] = 0;
	}
} 

bool checkLastPermutation(){
	bool result = true;
	for(int i=0 ; i<n ; i++){
		if(x[i] == 0){
			result = false;
			break;
		}
	}
	return result;
}

void next_permutation(){
	int i=n-1;
	while(i>0 && x[i] == 1){
		 x[i] = 0;
		 i--;	
	}
	if(i>=0) x[i] = 1;
	if(checkLastPermutation()){
		ok = false;
	}
}

void printPerResult(){
	for(int i=0 ; i<n ; i++){
		cout<< x[i] <<setw(2);		
	}
	cout <<"\n";
}

int main(){
	cout<< "Nhap n: "; cin>> n;
	init();
	while(ok){
		next_permutation();
		printPerResult();
	}
}

