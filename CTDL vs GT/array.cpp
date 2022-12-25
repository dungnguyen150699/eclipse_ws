#include <iostream>
using namespace std;

int arr[5];
int main(){
	for(int i=0 ; i<sizeof(arr)/sizeof(arr[0]) ; i++){
		arr[i] = i ;
		cout << arr[i] ;
	}
}

