package algorithm_.ctdl_gt;

import java.util.Scanner;

public class DecimalToBinary_Basic {

	public int[] binaryConverted;
	public int decimalInput, lenghtOfBinary;
	public Scanner ca = new Scanner(System.in);

	public void funcInitDecimal() {
		System.out.println("Type your decimal: ");
		decimalInput = ca.nextInt();
		int i = 1;
		while (decimalInput >= Math.pow(2, i * 4)) {
			i++;
		}
		binaryConverted = new int[i*4];
	}
	
	public void funcDecimalBinary() {
		int i = binaryConverted.length;
		int decimalInputCopy = decimalInput;
		while(i>=0 && decimalInputCopy>0) {
			binaryConverted[i-1]=decimalInputCopy%2;
			decimalInputCopy = (decimalInputCopy-binaryConverted[i-1])/2;
			i--;
		}
		System.out.println("Output binary: ");
		for(int j : binaryConverted) {
			System.out.print(j);
		}
		System.out.println("");
	}


	public static void main(String[] args) {
		DecimalToBinary_Basic decimalToBinary_Basic = new DecimalToBinary_Basic();
		decimalToBinary_Basic.funcInitDecimal();		
		decimalToBinary_Basic.funcDecimalBinary();
	}
}
