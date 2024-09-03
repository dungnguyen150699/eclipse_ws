package algorithm_.ctdl_gt;

import java.util.Scanner;

public class GennerateNBinary {
	int n;
	int binary[];
	Scanner ca = new Scanner(System.in);
	
	public void funcInit(){
		System.out.println("Type number Gennerate Binary: ");
		n = ca.nextInt();
		int blength = n/4+1;
		binary = new int[blength];
	}
	
	public void funcGenerateNBinary() {
		int i = binary.length;
		while(i>0 && binary[i-1]==1) {
			binary[i-1]=0;
			i--;
		}
		binary[i-1]=1;
		printOutput();
	}
	
	public void printOutput() {
		for(int j : binary) {
			System.out.print(j);
		}
		System.out.println();
	}

	
	public static void main(String[] args) {
		GennerateNBinary gen = new GennerateNBinary();
		gen.funcInit();
		while(gen.n>0) {
			gen.funcGenerateNBinary();	
			gen.n -= 1;
		}
	}

}
