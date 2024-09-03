package algorithm_.ctdl_gt;

import java.util.Scanner;

public class Combinate_BackTrack {
	int n,k;
	int []x;
	Scanner ca = new Scanner(System.in);
	
	public void funcInit() {
		System.out.println("Type n,k: ");
		n = ca.nextInt();
		k = ca.nextInt();
		x = new int[n];
	}
	
	public void funcBackTrack(int i) {
		for(int j=x[i-1]+1 ; j<=n-k+i ; j++) {
			x[i] = j;
			if(i==k) funcPrintResult();
			else funcBackTrack(i+1);
		}
	}
	
	public void funcPrintResult() {
		for(int i=1 ; i<=k ; i++) {
			System.out.print(x[i]);
		}
		System.out.println();
	}

	
	public static void main(String[] args) {
		Combinate_BackTrack cbt = new Combinate_BackTrack();
		cbt.funcInit();
		cbt.funcBackTrack(1);
	}
}
