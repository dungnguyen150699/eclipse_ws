package com.java8.springboot.spring;

import java.util.ArrayList;

public class Annotation_Available_InJAVA {
	public static void main(String[] args) {
		
	}
}


/*
 *  Annotation @SuppressWarnings: được sử dụng để ngăn chặn các cảnh báo phát hành bởi trình biên dịch.
 *	Nếu bạn loại bỏ @SuppressWarnings({ "unchecked", "rawtypes" }) 
 *  thì trình biên dịch sẽ hiện thị cảnh báo lúc biên dịch vì chúng ta đang sử dụng non-generic collection.
 */

class TestAnnotation2 {
//    @SuppressWarnings({ "unchecked", "rawtypes" }) 
    @SuppressWarnings({ "unchecked" })// Cái cảnh báo màu vàng ở góc ấy @@
    public static void main(String args[]) {
        ArrayList list = new ArrayList();
        list.add("C++");
        list.add("Java");
        list.add("PHP");
 
        for (Object obj : list)
            System.out.println(obj);
    }
}

/*
 * @Deprecated
	Annoation @Deprecated đánh đấu rằng phương thức này không được công nhận và không nên sử dụng nữa. 
	Nó thông báo cho người dùng rằng nó có thể được gỡ bỏ trong các phiên bản trong tương lai. 
	Vì vậy, tốt hơn là không sử dụng các phương thức như vậy.
*/

class A {
    void m() {
        System.out.println("hello m");
    }
 
    @Deprecated
    void n() {
        System.out.println("hello n");
    }
}
 
class TestAnnotation3 {
    public static void main(String args[]) {
        A a = new A();
        a.n();
    }
}

