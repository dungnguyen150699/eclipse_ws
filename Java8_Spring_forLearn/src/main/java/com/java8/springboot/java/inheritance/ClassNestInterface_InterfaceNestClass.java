package com.java8.springboot.java.inheritance;

import com.java8.springboot.java.inheritance.ClassNestInterface_InterfaceNestClass.interface_B;

public class ClassNestInterface_InterfaceNestClass implements parentInterface, A{
	
	interface interface_B{
		String foo_B();
	}

	@Override
	public void foo() {
		// TODO Auto-generated method stub
        A.B b = new A.B();
        b.foo();
	}
	
	public static void main(String[] strings) {
		ClassNestInterface_InterfaceNestClass c = new ClassNestInterface_InterfaceNestClass();
        c.foo();
        myClassNestInterface my = new myClassNestInterface();  // 1
        parentInterface.myClassNestInterface my2 = new parentInterface.myClassNestInterface();  //  2
        my.sayHello();
    }

}


interface parentInterface{
	public class myClassNestInterface{
		 public static final int KEY_DOWN = 0;
         public static final int KEY_UP = 1;
         public int type;
         public int keyCode;
         public char keyChar;
         
         public void sayHello() {
        	 System.out.println("myClassNestInterface - hello");
         }
	}
	
	public static class myClassNestInterface2{
		public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;
        public int type;
        public int keyCode;
        public char keyChar;
	}
}

// Cái này rất thú vị nhé
interface A {
    public void foo();

    // Được kìa Haha => interface_B
    public static class B implements A, interface_B {
        @Override
        public void foo() {
            System.out.println("B foo");
        }

		@Override
		public String foo_B() {
			// TODO Auto-generated method stub
			return null;
		}
    }
}