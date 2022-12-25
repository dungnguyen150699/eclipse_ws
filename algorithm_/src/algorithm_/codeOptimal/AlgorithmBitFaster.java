package algorithm_.codeOptimal;

public class AlgorithmBitFaster {
	public static boolean isOdd(int i) {
	    return (i & 1) != 0;
	}
	public static void main(String[] args) {
		AlgorithmBitFaster algorithmBitFaster = new AlgorithmBitFaster();
		System.out.println(algorithmBitFaster.isOdd(2));
		System.out.println(5&1); // Cái này dùng check chẵn lẻ này và nó sẽ nhanh hơn khi viết i%2!=0
	}
}
