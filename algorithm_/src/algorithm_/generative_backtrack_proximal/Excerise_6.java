package algorithm_.generative_backtrack_proximal;

import java.util.Scanner;

//Sắp xếp quân hậu 1
public class Excerise_6 {
	
	private int n,sum,dem,r[];
	private boolean xuoi[],nguoc[],cot[];
	
	private Scanner sc = new Scanner(System.in);
	
	public void init() {
		System.out.println("Type n:");
		n = sc.nextInt();
		// Step + 1 => size real is n
		r = new int[n+1];
		xuoi = new boolean[2*n - 1 + 1];	nguoc = new boolean[2*n - 1 + 1];	cot = new boolean[n+1];
		
	}
	
	public void printResult() {
		sum++; 
		System.out.println("number result " + ++dem + ": ");
		for(int i=1 ; i<=n ; i++) {
			System.out.print(r[i]);
		}
		System.out.println();
	}
	
	public void backTrack(int i) { // i <=> row
		for(int j=1 ; j<=n ; j++) {
			if(!cot[j] && !xuoi[i-j+n] && !nguoc[i+j-1]) {
				r[i] = j;
				cot[j] = true;
				xuoi[i-j+n] = true;
				nguoc[i+j-1] = true;
				if(i==n) printResult();
				else backTrack(i+1);
				cot[j] = false;	xuoi[i-j+n] = false;	nguoc[i+j-1] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Excerise_6 ex_6 = new Excerise_6();
		ex_6.init();
		ex_6.backTrack(1);
		
		System.out.println("Total Result: " + ex_6.sum);
	}
	
	
	
	
}
