package algorithm_.ctdl_gt;

import java.util.Scanner;

public class N_Queen_BackTrack {
	
	int n;
	boolean []xuoi,nguoc,cot;
	int []x;
	Scanner ca = new Scanner(System.in);
	int dem=1;
	
	public void init() {
		System.out.println("Type n:");
		n = ca.nextInt();
		x = new int[n+1];
		cot = new boolean[n+1];
		xuoi = new boolean[2*n];
		nguoc = new boolean[2*n];
	}
	
	public void backTrack(int i) {
		for(int j=1; j<=n ; j++) {
			if(!cot[j] && !xuoi[i-j+n] && !nguoc[i+j-1]) {
				cot[j]=true;
				xuoi[i-j+n]=true;
				nguoc[j+i-1]=true;
				x[i] = j;
				if(i==n) result();
				else backTrack(i+1);
				cot[j] = false;
				xuoi[i-j+n] = false;
				nguoc[i+j-1] = false;
			}
		}
	}
	
	public void result() {
		System.out.println("way: " + dem);
		for(int i=1 ; i<=n ; i++) {
			System.out.print(x[i] + " ");
		}
		dem++;
		System.out.println("");
	}
	
	public static void main(String[] args) {
		N_Queen_BackTrack nQueen = new N_Queen_BackTrack();
		nQueen.init();
		nQueen.backTrack(1);
	}
}
