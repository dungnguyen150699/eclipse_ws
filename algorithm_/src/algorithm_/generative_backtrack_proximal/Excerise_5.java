package algorithm_.generative_backtrack_proximal;

import java.util.Scanner;


public class Excerise_5 {
	/*
	 * // Sự khác biệt [][]x và x[][]
	 * [][] khai báo cho cả các phần tử phía sau nó, trong khi đó x[][] chỉ cho mình nó
	 * nên sử dụng trong th nào biết r đó @@
	 */
	private int x[][],n,k,sum,r[],dem; 
	private Scanner sc = new Scanner(System.in);
	
	public void init() {
		System.out.println("Nhập n,k: ");
		n = sc.nextInt(); k = sc.nextInt();
		x = new int[n+1][n+1];
		r = new int[n+1];
		
		for(int i=1 ; i<=n ; i++) {
			for(int j=1 ; j<=n ; j++) {
				x[i][j] = sc.nextInt();
			}
		}
	}
	
	public void printResult() {
		if(sum != k) return;
		System.out.print("number result " + ++dem + ": ");
		for(int i=1 ; i<=n ; i++) {
			System.out.print(r[i]);
		}
		System.out.println();
	}
	
	void backTrack(int i) {
		for(int j=1 ; j<=n ; j++) {
			sum += x[i][j];
			r[i] = j;
			if(i == n) {
				printResult();
			}else backTrack(i+1);
			sum -= x[i][j];
		}
	}
	
	public static void main(String[] args) {
		Excerise_5 ex5 = new Excerise_5();
		ex5.init();
		ex5.backTrack(1);
		
	}

}
