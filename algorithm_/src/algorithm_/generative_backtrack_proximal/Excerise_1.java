package algorithm_.generative_backtrack_proximal;

import java.util.Scanner;


// SINH KẾ TIẾP – QUAY LUI – NHÁNH CẬN
//BÀI 1. XÂU NHỊ PHÂN CÓ K BIT 1
public class Excerise_1 {
	int n,k,dem,x[];
	boolean ok = true;
	
	Scanner sc = new Scanner(System.in);
	
	void init() {
		System.out.println("Hãy nhập n,k ");
		n = sc.nextInt(); k = sc.nextInt();
		x = new int[n+1];
		
		for(int i=1 ; i<=n ; i++) {
			x[i] = 0;
			if(i > n-k) {
				x[i] = 1;
			}
		}
			
	}
	
	void printPerResult() {
		System.out.print("\nSố thứ " + ++dem + " : ");
		for(int i=1 ; i<=n ; i++)
			System.out.print(x[i]);
	}
	
	void next_Permutation() {
		int i = n-1;
		while(i>0 && x[i] >= x[i+1]) i--;
		if(i>0) {
			// swap
			int n1 = n;
			while(x[i] >= x[n1]) n1--;
			int temp = x[i]; x[i] = x[n1]; x[n1] = temp;
			// reverse
			int r = i+1, s=n;
			while(r<=s) {
				temp = x[r]; x[r] = x[s]; x[s]=temp;
				r++;
				s--;
			}
		}else ok = false;
	}
			
	
	public static void main(String[] args) {
		Excerise_1 ex1= new Excerise_1();
		ex1.init();
//		ex1.printPerResult();
		while(ex1.ok) {
			ex1.printPerResult();
			ex1.next_Permutation();
		}
	}
}
