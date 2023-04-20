package algorithm_;

import java.util.Arrays;
import java.util.LinkedList;

public final class Sort_Swap {
	public int a = 1;

	public static void main(String[] args) {
		Sort_Swap sw = new Sort_Swap();
		Sort_Swap sw1 = new Sort_Swap();
		sw.a = 1;
		sw1.a = 2;
		sw = sw1;
		System.out.println(sw.a); // Result = 2 but why Integer not same

//		Integer x = 1;
//		Integer x1 = 2;
//		sw.Swap(x, x1);
//		System.out.println(x+"-"+x1);
	}

	// Swap dont need variable temp
	public static void Swap(Integer a, Integer b) {
		b = a + b;
		a = new Integer(b - a);
		b = new Integer(b - a);
		System.out.println(a + "-" + b);
	}

}

class Bubble_Sort {

	public static int a[] = { 1, 2, 3, 4, 5 };
	public static int a1[] = { 5, 4, 3, 2, 1 };

	/*
	 * Ý tưởng:
	 * 
	 * Xuất phát từ cuối dãy, đổi chỗ các cặp phần tử kế cận để đưa phần tử nhỏ hơn
	 * trong cặp phần tử đó về vị trí đầu dãy hiện hành, sau đó sẽ không xét đến nó
	 * ở bước tiếp theo
	 * 
	 * Ở lần xử lý thứ i có vị trí đầu dãy là i
	 * 
	 * Lặp lại xử lý trên cho đến khi không còn cặp phần tử nào để xét
	 */
	public void bubbleSort(int[] arr, int n) {
		int i, j;
		boolean haveSwap;
		for (i = 0; i < n - 1; i++) {
			// i phần tử cuối cùng đã được sắp xếp
			haveSwap = false;
			for (j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
//		                swap(arr[j], arr[j+1]); // swap
					arr[j] *= arr[j + 1];
					arr[j + 1] = arr[j] / arr[j + 1];
					arr[j] = arr[j] / arr[j + 1];
					haveSwap = true; // Kiểm tra lần lặp này có swap không
				}
			}
			// Nếu không có swap nào được thực hiện => mảng đã sắp xếp. Không cần lặp thêm
			if (haveSwap == false) {
				break;
			}
		}

		printArray(arr);
	}

	void printArray(int a[]) {
		for (int i : a) {
			System.out.print(i);
		}
	}

	public static void main(String[] args) {
		Bubble_Sort bs = new Bubble_Sort();
		bs.bubbleSort(a1, a1.length);
	}
}

class Insertion_Sort {

	public static int a[] = { 1, 2, 3, 4, 5 };
	public static int a1[] = { 5, 4, 3, 2, 1 };

	/*
	 * Ý tưởng chính: Tìm cách chèn phần tử a[i] vào vị trí thích hợp của đoạn đã
	 * được sắp để có dãy mới a[0] , a[1] ,... , a[i-1] trở nên có thứ tự Vị trí này
	 * chính là pos thỏa : a[pos-1] <= a[i ]< a[pos] (1 <= pos <= i)
	 * 
	 */
	void InsertionSort(int a[], int n) { // Best = n-1 ; Bad = n(n-1)/2
		int pos, x;
		for (int i = 1; i < n; i++) {
			// Tiến hành lưu biến tạm
			x = a[i];
			pos = i;
			// Tiến hành dời chỗ
			while (pos > 0 && x < a[pos - 1]) {
				a[pos] = a[pos - 1]; // dời chỗ
				pos--;
			}
			// Sau khi dời chỗ xong
			a[pos] = x;
		}
	}

	void printArray(int a[]) {
		for (int i : a) {
			System.out.print(i);
		}
	}

	public static void main(String[] args) {
		Insertion_Sort is = new Insertion_Sort();
		is.InsertionSort(a1, a1.length);
		is.printArray(a1);
		;
	}
}

class Select_Sort {

	/*
	 * Ý tưởng: mô phỏng một trong những cách sắp xếp tự nhiên nhất trong thực tế:
	 * Chọn phần tử nhỏ nhất trong n phần tử ban đầu, đưa phần tử này về vị trí đúng
	 * là đầu dãy hiện hành Xem dãy hiện hành chỉ còn n-1 phần tử của dãy ban đầu,
	 * bắt đầu từ vị trí thứ 2; lặp lại quá trình trên cho dãy hiện hành... đến khi
	 * dãy hiện hành chỉ còn 1 phần tử
	 */
	void SelectionSort(int a[], int n) // Best = bad = n(n-1)/2
	{
		int min; // chỉ số phần tử nhỏ nhất trong dãy hiện hành
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (int j = i + 1; j < n; j++)
				if (a[j] < a[min])
					min = j; // ghi nhận vị trí phần tử nhỏ nhất
			if (min != i)
				Sort_Swap.Swap(a[min], a[i]);
		}
	}
}

class QuickSort2 {

	// Hàm nhận phần tử cuối cùng làm chốt,
	// đặt các phần tử nhỏ hơn chốt ở trước
	// và lớn hơn ở sau nó
	int partition(int arr[], int low, int high) {
		int pivot = arr[high]; // Chọn chốt là cuối arr
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// Nếu phần tử hiện tại nhỏ hơn chốt
			if (arr[j] < pivot) {
				i++;
				// swap arr[i] và arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] và arr[high] (hoặc pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	// arr[] --> Mảng cần được sắp xếp,
	// low --> chỉ mục bắt đầu,
	// high --> chỉ mục kết thúc
	void sort(int arr[], int low, int high) {
		if (low < high) {

			// pi là chỉ mục của chốt, arr[pi] vị trí của chốt
			int pi = partition(arr, low, high);

			// Sắp xếp đệ quy các phần tử
			// trướcphân vùng và sau phân vùng
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}

	// In các phần tử của mảng
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		int arr[] = { 10, 80, 30, 90, 40, 50, 70 };
		int n = arr.length;

		System.out.println("Mảng ban đầu:");
		printArray(arr);

		QuickSort2 ob = new QuickSort2();
		ob.sort(arr, 0, n - 1);

		System.out.println("Mảng sau khi sắp xếp:");
		printArray(arr);
	}
}

//Radix sort Java implementation
// https://www.geeksforgeeks.org/radix-sort/
class Radix {

	// A utility function to get maximum value in arr[]
	static int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	static void countSort(int arr[], int n, int exp, int step) {
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10]; // count point for each item arr it also index sorted
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++; // hàng đơn vị, hàng chục ...
		
		System.out.print("Count origin Step " + step + ": exp = " + exp + ":  ");
		print(count,10);

		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++) // Quy hoạch động
			count[i] += count[i - 1];
		
		System.out.print("Count reindex Step " + step + ": exp = " + exp + ":  ");
		print(count,10);

		// Build the output array 
		for (i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to current
		// digit
		for (i = 0; i < n; i++)
			arr[i] = output[i];
		
		System.out.print("Count final Step " + step + ":  "); print(count,10);
		System.out.print("Result Step " + step + ":  ");print(arr,n);
		step++;
	}

	// The main function to that sorts arr[] of
	// size n using Radix Sort
	static void radixsort(int arr[], int n) {
		// Find the maximum number to know number of digits
		int m = getMax(arr, n);

		// Do counting sort for every digit. Note that
		// instead of passing digit number, exp is passed.
		// exp is 10^i where i is current digit number
		int step = 1;
		for (int exp = 1; m / exp > 0; exp *= 10) {
			countSort(arr, n, exp, step);
			step++;
		}
	}

	// A utility function to print an array
	static void print(int arr[], int n) {
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println("");
	}

	// Main driver method
	public static void main(String[] args) {
		int arr[] = { 170,170, 45, 75, 90, 802, 24, 2, 66 };
		System.out.println("Arr Origin : ");  print(arr, arr.length);
		int n = arr.length;

		// Function Call
		radixsort(arr, n);
		System.out.print("Result: "); print(arr, n);
	}
}



class RadixSort {
 
    // utility function to get the number of digits in the
    // max_element
    static int digits(int n)
    {
        int i = 1;
        if (n < 10)
            return 1;
 
        while (n > Math.pow(10, i))
            i++;
 
        return i;
    }
 
    public static void radixSort(int[] arr)
    {
        // size of the array to be sorted
        int sz = arr.length;
 
        // getting the maximum element in the array
        int max_val = Integer.MIN_VALUE;
        for (int i = 0; i < sz; i++)
            max_val = Math.max(max_val, arr[i]);
 
        // getting digits in the maximum element
        int d = digits(max_val);
 
        // creating buckets to store the pointers
        LinkedList<Integer>[] bins = new LinkedList[10];
        for (int i = 0; i < 10; i++)
            bins[i] = new LinkedList<Integer>();
 
        // first loop working for a constant time only and
        // inner loop is iterating through the array to
        // store elements of array in the linked list by
        // their digits value
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < sz; j++) {
                int digit
                    = (arr[j] / (int)Math.pow(10, i)) % 10;
                bins[digit].add(arr[j]);
            }
 
            int x = 0, y = 0;
            // write back to the array after each pass
            while (x < 10) {
                while (!bins[x].isEmpty()) {
                    arr[y] = bins[x].remove();
                    y++;
                }
                x++;
            }
        }
    }
 
    // a utility function to print the sorted array
    static void printArr(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
 
    public static void main(String[] args)
    {
        int[] arr = { 573, 25, 415, 12, 161, 6 };
 
        // function call
        radixSort(arr);
        printArr(arr);
    }
}
