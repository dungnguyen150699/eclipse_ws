package com.java8.springboot.java.CollectionFrameWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

// CommandLineRunner of Spring boot
import org.springframework.boot.CommandLineRunner;

public class CollectionsClass {

	/*
	 * Collection là một interface cấp cao nhất nhất của Collection Framework. Trong
	 * khi đó, Collections là một lớp tiện ích. Collections bao gồm các phương thức
	 * static được sử dụng để thao tác trên các đối tượng của Collection (List,
	 * ArrayList, LinkedList, Map, Set, …).
	 * 
	 * Các thuộc tính của lớp Collections static List EMPTY_LIST: khởi tạo một danh
	 * sách trống, không thể thay đổi (immutable). final static Map EMPTY_MAP: khởi
	 * tạo một map trống, không thể thay đổi (immutable). final static Set
	 * EMPTY_SET: khởi tạo một tập hợp trống, không thể thay đổi (immutable). final
	 */

// BinnarySearch
	public static List<Integer> listInteger = new ArrayList<>(Arrays.asList(1, 2));

	public static <T> int BinarySearch(List<T> list, T key, Comparator<T> comparator) {
		return Collections.binarySearch(list, key, comparator);
	}

	public static int BinarySearch2(List<Integer> list, Integer key) {
		List<Integer> list2 = new ArrayList<>();
		Collections.copy(list2, list);
		System.out.println(list2.toString());
		return Collections.binarySearch(list2, key);
	}

	public static void BinnarySearch_Pratice(String[] args) {
		// listInteger = Collections.EMPTY_LIST;
		// listInteger.add(1); // => java.lang.UnsupportedOperationException Ko support
		// Phương thức này do nó final r.
		// listInteger = Collections.singletonList(6);
		// listInteger.add(2); => java.lang.UnsupportedOperationException Ko support
		// Phương thức này do nó final r.

		// Collections.addAll
		Collections.addAll(listInteger, 10, 20, 25);
		// Collection.addAll
		listInteger.addAll( new ArrayList<>(Arrays.asList(30,35)));
		
		System.out.println(listInteger.toString());
		// BinarySearch
		// Cách 1 dùng lamda thay thế interface
		int index = BinarySearch(listInteger, 10, (i1, i2) -> {
			if (i1 > i2)
				return 1;
			else {
				if (i1 == i2)
					return 0;
				else
					return -1;
			}
		});

		System.out.println(10 + "   have index  =  " + index + Object.class);
		// BinarySearch
		/*
		 * Cách 2 dùng raw Comparator -3 -1 ( 3 là vị trí 13 nên ở và -1 đại diện cho nó
		 * ko tồn tại nữa)
		 */
		index = BinarySearch(listInteger, 11, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		System.out.println(11 + "   have index  =  " + index);
		System.out.println(listInteger.toString());

		// BinarySearch
		/*
		 * Cách 3
		 */
		index = BinarySearch(listInteger, 12, Comparator.comparingInt(Integer::new));

		System.out.println(12 + "   have index  =  " + index);
		System.out.println(listInteger.toString());
	}

// -------------------------------------------
	public void MinMax_SearchSort_ReverserReverserOrder() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(46);
		list.add(67);
		list.add(24);
		list.add(16);
		list.add(8);
		list.add(12);
		System.out.println("Maximum value: " + Collections.max(list));
		System.out.println("Minimum value: " + Collections.min(list));
		System.out.println("Index of value 24 : " + Collections.binarySearch(list, 24));
		System.out.println("Index of value 10 : " + Collections.binarySearch(list, 10));

		Collections.sort(list);
		System.out.println("Sorted ASC: " + list);

		Collections.reverse(list);
		System.out.println("Sorted DESC: " + list);

		Comparator<Integer> compareDesc = Collections.reverseOrder();
		Collections.sort(list, compareDesc);
		System.out.println("Sorted ASC: " + list);

		Comparator<Integer> compareAsc = Collections.reverseOrder(compareDesc);
		Collections.sort(list, compareAsc);
		System.out.println("Sorted DESC: " + list);
	}

	public static void main(String[] args) {

	}

}


class Student {
    private int id;
    private String name;
    private int age;
 
    public Student(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
 
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
 
    public int getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public int getAge() {
        return age;
    }
}

class StudentAgeComparator implements Comparator<Student> {
	 
    @Override
    public int compare(Student o1, Student o2) {
        // sort student's age by ASC
        if (o1.getAge() < o2.getAge()) {
            return -1;
        } else if (o1.getAge() == o2.getAge()) {
            return 0;
        } else {
            return 1;
        }
    }
}

// Cung cấp bộ so sánh Comparator
class CollectionsExample3 {
    public static void main(String a[]) {
        // Create list
        List<Student> students = new ArrayList<>();
        Student student1 = new Student(1, "myname1", 15);
        Student student2 = new Student(2, "myname2", 20);
        Student student3 = new Student(3, "myname3", 17);
        Student student4 = new Student(4, "myname4", 10);
        Student student5 = new Student(5, "myname5", 19);
        Student student6 = new Student(6, "myname6", 19);
        students.add(student3);
        students.add(student1);
        students.add(student2);
        students.add(student5);
        students.add(student4);
 
        // Init comparator
        StudentAgeComparator ageComparator = new StudentAgeComparator();
 
        // Using comparator
        System.out.println("Maximum value: " + Collections.max(students, ageComparator));
        System.out.println("Minimum value: " + Collections.min(students, ageComparator));
        System.out.println("Index of student1 : " + Collections.binarySearch(students, student1, ageComparator));
        System.out.println("Index of student6 : " + Collections.binarySearch(students, student6, ageComparator));
        System.out.println("---");
 
        Collections.sort(students, ageComparator);
        System.out.println("Sorted ASC: ");
        print(students);
 
        Collections.reverse(students);
        System.out.println("Sorted DESC: ");
        print(students);
 
        Comparator<Student> compareDesc = Collections.reverseOrder(ageComparator);
        Collections.sort(students, compareDesc);
        System.out.println("Sorted DESC: ");
        print(students);
 
        Comparator<Student> compareAsc = Collections.reverseOrder(compareDesc);
        Collections.sort(students, compareAsc);
        System.out.println("Sorted ASC: ");
        print(students);
    }
 
    private static void print(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("---");
    }
}

/*
 * ==> Result
 * Maximum value: Student [id=2, name=myname2, age=20]
Minimum value: Student [id=4, name=myname4, age=10]
Index of student1 : -1
Index of student6 : -3
---
Sorted ASC: 
Student [id=4, name=myname4, age=10]
Student [id=1, name=myname1, age=15]
Student [id=3, name=myname3, age=17]
Student [id=5, name=myname5, age=19]
Student [id=2, name=myname2, age=20]
---
Sorted DESC: 
Student [id=2, name=myname2, age=20]
Student [id=5, name=myname5, age=19]
Student [id=3, name=myname3, age=17]
Student [id=1, name=myname1, age=15]
Student [id=4, name=myname4, age=10]
---
Sorted DESC: 
Student [id=2, name=myname2, age=20]
Student [id=5, name=myname5, age=19]
Student [id=3, name=myname3, age=17]
Student [id=1, name=myname1, age=15]
Student [id=4, name=myname4, age=10]
---
Sorted ASC: 
Student [id=4, name=myname4, age=10]
Student [id=1, name=myname1, age=15]
Student [id=3, name=myname3, age=17]
Student [id=5, name=myname5, age=19]
Student [id=2, name=myname2, age=20]
 */

class Student2 implements Comparable<Student2> {
    private int id;
    private String name;
    private int age;
 
    public Student2(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
 
    @Override
    public int compareTo(Student2 student) {
        // sort student's age by ASC
        if (this.getAge() < student.getAge()) {
            return -1;
        } else if (this.getAge() == student.getAge()) {
            return 0;
        } else {
            return 1;
        }
    }
 
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
 
    public int getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public int getAge() {
        return age;
    }
}

//Cung cấp bộ so sánh Comparable
class CollectionsExample4 {
    public static void main(String a[]) {
        // Create list
        List<Student2> students = new ArrayList<>();
        Student2 student1 = new Student2(1, "myname1", 15);
        Student2 student2 = new Student2(2, "myname2", 20);
        Student2 student3 = new Student2(3, "myname3", 17);
        Student2 student4 = new Student2(4, "myname4", 10);
        Student2 student5 = new Student2(5, "myname5", 19);
        Student2 student6 = new Student2(6, "myname6", 19);
        students.add(student3);
        students.add(student1);
        students.add(student2);
        students.add(student5);
        students.add(student4);
 
        // Không cần cung cấp bộ so sánh, bởi vì nó đã được cài đặt trong lớp Student2
        System.out.println("Maximum value: " + Collections.max(students));
        System.out.println("Minimum value: " + Collections.min(students));
        System.out.println("Index of student1 : " + Collections.binarySearch(students, student1));
        System.out.println("Index of student6 : " + Collections.binarySearch(students, student6));
        System.out.println("---");
 
        Collections.sort(students);
        System.out.println("Sorted ASC: ");
        print(students);
 
        Collections.reverse(students);
        System.out.println("Sorted DESC: ");
        print(students);
 
        Comparator<Student2> compareDesc = Collections.reverseOrder();
        Collections.sort(students, compareDesc);
        System.out.println("Sorted DESC: ");
        print(students);
 
        Comparator<Student2> compareAsc = Collections.reverseOrder(compareDesc);
        Collections.sort(students, compareAsc);
        System.out.println("Sorted ASC: ");
        print(students);
    }
 
    private static void print(List<Student2> students) {
        for (Student2 student : students) {
            System.out.println(student);
        }
        System.out.println("---");
    }
}
/* ==> Result
 * Maximum value: Student [id=2, name=myname2, age=20]
Minimum value: Student [id=4, name=myname4, age=10]
Index of student1 : -1
Index of student6 : -3
---
Sorted ASC: 
Student [id=4, name=myname4, age=10]
Student [id=1, name=myname1, age=15]
Student [id=3, name=myname3, age=17]
Student [id=5, name=myname5, age=19]
Student [id=2, name=myname2, age=20]
---
Sorted DESC: 
Student [id=2, name=myname2, age=20]
Student [id=5, name=myname5, age=19]
Student [id=3, name=myname3, age=17]
Student [id=1, name=myname1, age=15]
Student [id=4, name=myname4, age=10]
---
Sorted DESC: 
Student [id=2, name=myname2, age=20]
Student [id=5, name=myname5, age=19]
Student [id=3, name=myname3, age=17]
Student [id=1, name=myname1, age=15]
Student [id=4, name=myname4, age=10]
---
Sorted ASC: 
Student [id=4, name=myname4, age=10]
Student [id=1, name=myname1, age=15]
Student [id=3, name=myname3, age=17]
Student [id=5, name=myname5, age=19]
Student [id=2, name=myname2, age=20]
 */

// Sử dụng phương thức frequency() để đếm số lần xuất hiện của phần tử trong Collection
class CollectionsExample5 {
    public static void main(String args[]) {
        List<Integer> myList = new ArrayList<>();
        myList.add(10);
        myList.add(20);
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(10);
        System.out.println("Elements of ArrayList: " + myList);
        System.out.println("No. of times 10 exists: " + Collections.frequency(myList, 10));
        System.out.println("No. of times 20 exists: " + Collections.frequency(myList, 20));
        System.out.println("No. of times 30 exists: " + Collections.frequency(myList, 30));
    }
}

/*
 * Elements of ArrayList: [10, 20, 10, 20, 30, 10]
No. of times 10 exists: 3
No. of times 20 exists: 2
No. of times 30 exists: 1
 */

// Sử dụng phương thức copy() để sao chép một list này sang một list khác
class CollectionsExample6 {
    public static void main(String args[]) {
        List<String> firstList = new ArrayList<>();
        firstList.add("10");
        firstList.add("20");
        firstList.add("30");
        System.out.println("Elements firstList: " + firstList);
 
        List<String> secondList = new ArrayList<>();
        secondList.add("one");
        secondList.add("two");
        secondList.add("three");
        System.out.println("Elements secondList: " + secondList);
 
        Collections.copy(secondList, firstList);
        System.out.println("Elements of secondList after copying firstList: " + secondList);
 
        List<String> thirdList = new ArrayList<>();
        thirdList.add("one");
        thirdList.add("two");
        thirdList.add("three");
        thirdList.add("four");
        thirdList.add("five");
        System.out.println("\nElements thirdList: " + thirdList);
 
        Collections.copy(thirdList, firstList);
        System.out.println("Elements of thirdList after copying firstList: " + thirdList);
    }
}
/*
 * Elements firstList: [10, 20, 30]
Elements secondList: [one, two, three]
Elements of secondList after copying firstList: [10, 20, 30]
 
Elements thirdList: [one, two, three, four, five]
Elements of thirdList after copying firstList: [10, 20, 30, four, five]
 */


//Sử dụng phương thức swap() để hoán đổi vị trí của 2 phần tử
class CollectionsExample7 {
    public static void main(String args[]) {
        List<Integer> myList = new ArrayList<>();
        myList.add(50);
        myList.add(10);
        myList.add(20);
        myList.add(40);
        System.out.println("Elements before swap: " + myList);
 
        Collections.swap(myList, 0, 1);
        System.out.println("Elements after 0,1 swap: " + myList);
 
        Collections.swap(myList, 2, 3);
        System.out.println("Elements after 2,3 swap: " + myList);
    }
}

/* Result
Elements before swap: [50, 10, 20, 40]
Elements after 0,1 swap: [10, 50, 20, 40]
Elements after 2,3 swap: [10, 50, 40, 20]
*/


// Sử dụng phương thức shuffle() để truy cập ngẫu nhiên các phần tử trong Collection
class CollectionsExample8 {
    public static void main(String args[]) {
        List<Integer> myList = new ArrayList<>();
        myList.add(30);
        myList.add(10);
        myList.add(20);
        myList.add(40);
        System.out.println("Elements before shuffle: " + myList);
 
        Collections.shuffle(myList);
        System.out.println("Elements after first shuffle: " + myList);
 
        Collections.shuffle(myList);
        System.out.println("Elements after second shuffle: " + myList);
 
        Collections.shuffle(myList);
        System.out.println("Elements after third shuffle: " + myList);
    }
}
/* ==> Result
 * Elements before shuffle: [30, 10, 20, 40]
Elements after first shuffle: [10, 40, 20, 30]
Elements after second shuffle: [10, 20, 40, 30]
Elements after third shuffle: [40, 30, 10, 20]
 */


// Sử dụng phương thức rotate() để xoay các phần tử trong danh sách
class CollectionsExample9 {
    public static void main(String args[]) {
        List<Integer> myList = new ArrayList<>();
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        myList.add(50);
        myList.add(60);
        System.out.println("Elements myList before rotate: " + myList);
 
        Collections.rotate(myList, 3);
        System.out.println("Elements myList after rotate: " + myList);
    }
}
/* ==> Result
 * Elements myList before rotate: [10, 20, 30, 40, 50, 60]
Elements myList after rotate: [40, 50, 60, 10, 20, 30]
 */

// Sử dụng phương thức replaceAll() để tìm kiếm và thay thế các phần tử bằng một phần tử khác
class CollectionsExample10_2 {
    public static void main(String args[]) {
        List<Integer> myList = new ArrayList<>();
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        System.out.println("Elements myList before replacing: " + myList);
 
        boolean success = Collections.replaceAll(myList, 10, 100);
        System.out.println("Replace operation successful: " + success);
        System.out.println("Elements after replacing: " + myList);
 
        success = Collections.replaceAll(myList, 50, 200);
        System.out.println("Replace operation successful: " + success);
        System.out.println("Elements after replacing: " + myList);
    }
}

/* ==> Result
 * Elements myList before replacing: [10, 20, 30, 40]
Replace operation successful: true
Elements after replacing: [100, 20, 30, 40]
Replace operation successful: false
Elements after replacing: [100, 20, 30, 40]
 */


// Sử dụng phương thức fill() để thay thế tất cả các phần tử trong danh sách bằng một phần tử bất kỳ
class CollectionsExample10 {
    public static void main(String args[]) {
        List<Integer> myList = new ArrayList<>();
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        System.out.println("Elements before fill: " + myList);
 
        Collections.fill(myList, 0);
        System.out.println("Elements after fill: " + myList);
 
        List<String> namesList = new ArrayList<>();
        namesList.add("wellcome");
        namesList.add("to");
        namesList.add("gpcoder");
        System.out.println("\nElements before fill: " + namesList);
 
        Collections.fill(namesList, null);
        System.out.println("Elements after fill: " + namesList);
    }
}
/*
 * Elements before fill: [10, 20, 30, 40]
Elements after fill: [0, 0, 0, 0]
 
Elements before fill: [wellcome, to, gpcoder]
Elements after fill: [null, null, null]
 */

//Sử dụng phương thức disjoint() để kiểm tra hai danh sách có chứa bất kỳ phần tử nào giống nhau không
class CollectionsExample11 {
    public static void main(String args[]) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
 
        List<Integer> list2 = new ArrayList<>();
        list2.add(60);
        list2.add(40);
        list2.add(20);
 
        List<Integer> list3 = new ArrayList<>();
        list3.add(60);
        list3.add(40);
        list3.add(50);
 
        System.out.println("Elements of list1: " + list1);
        System.out.println("Elements of list2: " + list2);
        System.out.println("Elements of list3: " + list3);
 
        boolean exists = Collections.disjoint(list1, list2);
        System.out.println("\nlist1 and list2 contains same elements: " + exists); // false
 
        exists = Collections.disjoint(list1, list3);
        System.out.println("list1 and list3 contains same elements: " + exists); // true
    }
}
/* ==>  Result
 * Elements of firstList: [10, 20, 30]
Elements of secondList: [60, 40, 20]
Elements of thirdList: [60, 40, 50]
 
firstList and secondList contains same elements: false
firstList and thirdList contains same elements: true
 */

// Sử dụng phương thức indexOfSubList() và lastIndexOfSubList() để tìm vị trí xuất hiện đầu tiên và cuối cùng của một danh sách này trong một danh sách khác

class CollectionsExample12 {
    public static void main(String args[]) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
        list1.add(100);
        list1.add(20);
        list1.add(30);
        list1.add(400);
 
        List<Integer> list2 = new ArrayList<>();
        list2.add(20);
        list2.add(30);
 
        List<Integer> list3 = new ArrayList<>();
        list3.add(20);
        list3.add(40);
 
        System.out.println("list1 elements: " + list1);
        System.out.println("list2 elements: " + list2);
        System.out.println("list3 elements: " + list3);
         
        int num1 = Collections.indexOfSubList(list1, list2);
        System.out.println("\nFirst index list2 in list1: " + num1);
 
        int num2 = Collections.lastIndexOfSubList(list1, list2);
        System.out.println("Last index list2 in list1: " + num2);
 
        int num3 = Collections.lastIndexOfSubList(list1, list3);
        System.out.println("\nFirst index num3 in list1: " + num3);
 
        int num4 = Collections.lastIndexOfSubList(list1, list3);
        System.out.println("Last index num3 in list1: " + num4);
    }
}
/*Result
 * list1 elements: [10, 20, 30, 40, 100, 20, 30, 400]
list2 elements: [20, 30]
list3 elements: [20, 40]
 
First index list2 in list1: 1
Last index list2 in list1: 5
 
First index num3 in list1: -1
Last index num3 in list1: -1
 * 
 */


// Sử dụng các phương thức unmodifiableCollection()
class CollectionsExample13 {
    public static void main(String args[]) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
 
        List<Integer> list2 = Collections.unmodifiableList(list1);
        System.out.println("list2 elements: " + list2);
 
        // list2.add(50); // throws UnsupportedOpearationException
 
        System.out.println("list1 elements before adding 50: " + list1);
        list1.add(50);
        System.out.println("list1 elements after adding 50: " + list1);
 
        Set<String> set1 = new HashSet<>();
        set1.add("welcome");
        set1.add("to");
        set1.add("gpconder");
        Set<String> set2 = Collections.unmodifiableSet(set1);
        System.out.println("set2 elements: " + set2);
 
        // set2.add("sir"); // throws UnsupportedOpearationException
 
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        Map<Integer, String> map2 = Collections.unmodifiableMap(map1);
        System.out.println("map2 elements: " + map2);
 
        // map2.put(4, "four"); // throws UnsupportedOpearationException
    }
}
/*Result 
list2 elements: [10, 20, 30, 40]
list1 elements before adding 50: [10, 20, 30, 40]
list1 elements after adding 50: [10, 20, 30, 40, 50]
set2 elements: [gpconder, to, welcome]
map2 elements: {1=one, 2=two, 3=three}
 * 
 */

// Ví dụ sử dụng synchronizedCollection() để sử dụng các phương thức của Collection trong môi trường đa luồng (multi-thread)
class CollectionsExample14 {
    public static void main(String args[]) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
 
        List<Integer> list2 = Collections.synchronizedList(list1);
        System.out.println("list2 elements: " + list2);
 
        HashSet<String> set1 = new HashSet<String>();
        set1.add("welcome");
        set1.add("to");
        set1.add("gpconder");
        Set<String> set2 = Collections.synchronizedSet(set1);
        System.out.println("set2 elements: " + set2);
 
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        Map<Integer, String> map2 = Collections.synchronizedMap(map1);
        System.out.println("map2 elements: " + map2);
    }
}
/* Result
list2 elements: [10, 20, 30, 40]
set2 elements: [gpconder, to, welcome]
map2 elements: {1=one, 2=two, 3=three}
 */

// Sử dụng phương thức checkedCollection() để sử dụng Type-safe Collection (tương tự Generic)
class CollectionsExample15 {
    public static void main(String args[]) {
        List list1 = new ArrayList();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
 
        List list2 = Collections.checkedList(list1, Integer.class);
        System.out.println("list2 elements: " + list2);
 
        list1.add("hello");
        // list2.add("hello"); // throws ClassCastException
 
        HashSet set1 = new HashSet();
        set1.add("welcome");
        set1.add("to");
        set1.add("gpconder");
        Set set2 = Collections.checkedSet(set1, String.class);
        System.out.println("set2 elements: " + set2);
 
        set1.add(10);
        // secondSet.add(10); // throws ClassCastException
 
        Map map1 = new HashMap();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        Map map2 = Collections.checkedMap(map1, Integer.class, String.class);
        System.out.println("map2 elements: " + map2);
 
        // map2.put("4", "four"); // throws ClassCastException
    }
}
/* Result
list2 elements: [10, 20, 30, 40]
set2 elements: [gpconder, to, welcome]
map2  elements: {1=one, 2=two, 3=three}
 */


// Sử dụng phương thức singletonList() để đảm bảo một đối tượng chỉ có một phần tử
class CollectionsExample16 {
    public static void main(String args[]) {
        List<Integer> list = Collections.singletonList(new Integer(10));
        System.out.println("list elements: " + list);
 
        // list.add(20); // throws UnsupportedOperationException
 
        Set<String> set = Collections.singleton("Welcome to gpcoder.com");
        System.out.println("set elements: " + set);
 
        // set.add("world"); // throws UnsupportedOperationException
 
        Map<Integer, String> map = Collections.singletonMap(1, "one");
        System.out.println("map elements: " + map);
 
        // map1.put(2, "two"); // throws UnsupportedOperationException
    }
}
/*Result
list elements: [10]
set elements: [Welcome to gpcoder.com]
map elements: {1=one}
 */

// Sử dụng phương thức list(Enumeration) để chuyển Enumeration sang ArrayList
class CollectionsExample17 {
    public static void main(String args[]) {
        Vector<Integer> vect = new Vector<>();
        vect.addElement(10);
        vect.addElement(30);
        vect.add(50);
        vect.add(20);
        System.out.println("Elements of Vector: " + vect);
 
        Enumeration<Integer> e = vect.elements();
        ArrayList<Integer> myList = Collections.list(e);
        System.out.println("Elements of ArrayList: " + myList);
    }
}
/*Result
 * Elements of Vector: [10, 30, 50, 20]
Elements of ArrayList: [10, 30, 50, 20]
 */


// Sử dụng phương thức enumeration() để có thể duyệt các phần tử của Collection thông qua đối tượng Enumeration
class CollectionsExample18 {
    public static void main(String args[]) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
        System.out.println("list1 elements: " + list1);
 
        Enumeration<Integer> e = Collections.enumeration(list1);
 
        System.out.print("list1 elements using Enumeration: ");
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            System.out.print(obj + " ");
        }
    }
}
/*
list1 elements: [10, 20, 30, 40]
list1 elements using Enumeration: 10 20 30 40
 */





