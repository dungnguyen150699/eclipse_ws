package com.java8.springboot.java.inheritance;

public class AnonymousClasses {
	
	public String strTest = "Hello Anonymous Class Im Dung";
	  
    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }
    
  
    public void sayHello() {
    	String strTest2 = "Hello Anonymous Class Im Dung2";
        
        class EnglishGreeting implements HelloWorld {
            String name = "world";
            public void greet() {
                greetSomeone("world" + strTest + "---" + strTest2);
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
      
        HelloWorld englishGreeting = new EnglishGreeting();
        
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            @Override
            public void greet() {
                greetSomeone("tout le monde");
            }
            
            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };
        
        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
        spanishGreeting.greet();
    }

    public static void main(String... args) {
    	// new By Interface Annonymous class
    	new HelloWorld() {

			@Override
			public void greet() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void greetSomeone(String someone) {
				// TODO Auto-generated method stub
				
			}
    		
    	};
    	
        AnonymousClasses myApp =
            new AnonymousClasses();
        myApp.sayHello();
    }            
}