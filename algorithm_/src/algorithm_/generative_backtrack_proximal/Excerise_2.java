package algorithm_.generative_backtrack_proximal;

import java.util.Scanner;


class BackTrackBasic{
	int n,k,dem,x[];
	Scanner sc = new Scanner(System.in);
	
	void init() {
//		System.out.println("Hãy nhập n,k: ");
//		n = sc.nextInt(); k = sc.nextInt();
		
		System.out.println("Hãy nhập n ");
		n = sc.nextInt();
		x = new int[n+1];
//		for(int i=1 ; i<=n ; i++)
//			x[i] = 0;
	}
	
	void printPerResult() {
		System.out.print("\nSố thứ " + ++dem + " : ");
		for(int i=1 ; i<=n ; i++)
			System.out.print(x[i]);
	}
	
	void backTrack(int i) {
		for(int j=0 ; j<=1 ; j++) {
			x[i] = j;
			if(i == n) printPerResult();
			else  backTrack(i+1);
		}
	}
	
	public static void main(String[] args) {
		BackTrackBasic ex1= new BackTrackBasic();
		ex1.init();
//		ex1.printPerResult();
		ex1.backTrack(1);
	}
}

// BÀI 2. XÂU AB
public class Excerise_2 {
	int n,k,dem,x[],n1;
	char x1[];
	
	Scanner sc = new Scanner(System.in);
	
	void init() {		
		System.out.println("Hãy nhập n,k ");
		n = sc.nextInt(); k = sc.nextInt();
		n1 = n+1-k;
		System.out.println("n1---: " + n1);
		x = new int[n1+1];
		for(int i=1 ; i<=n1 ; i++) {
			x[i] = 0;
		}
	}
	
	void printPerResult() {
		int number1 = 0;
		for(int i=1 ; i<n1 ; i++) {
			if(x[i]!=x[i+1] && x[i]==1 && x[i+1]==2 || x[i]!=x[i+1] && x[i]==2 && x[i+1]==1) return;
			if(x[i] == 1) number1++;
		}
		if(x[n1] == 1) number1++;
		
		if(number1 == 1) {
			System.out.print("\nSố thứ " + ++dem + " : ");
			for(int i=1 ; i<=n1 ; i++) {
//				System.out.print(x[i]);
				if(x[i] == 1) {
					for(int j=1 ; j<=k ; j++) System.out.print('A');
				}
				if(x[i] == 2) System.out.print('A');
				if(x[i] == 3) System.out.print('B');
			}
				
		}
	}
	// Ý tưởng chính là coi AAA=1 A=2 B=3; Sử dụng quay lui để soát hết các phần tử
	void backTrack(int i) {
		for(int j=1 ; j<=n1 ; j++) {
			x[i] = j;

			if(i==n1) printPerResult();
			else backTrack(i+1);
		}
	}

	public static void main(String[] args) {
		Excerise_2 ex2 = new Excerise_2();
		ex2.init();
		ex2.backTrack(1);
//		ex2.printPerResult();
	}
}
