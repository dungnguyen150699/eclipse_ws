package com.java8.springboot.java.thread.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyRunnable implements Runnable {
	 
    // Tên của Runnable, giúp chúng ta phân biệt Runnable nào đang thực thi trong Thread Pool
    private String name;
     
    public MyRunnable(String name) {
        // Khởi tạo Runnable với biến name truyền vào
        this.name = name;
    }
     
    @Override
    public void run() {
        System.out.println(name + " đang thực thi...");
 
        // Giả lập thời gian chạy của Runnable mất 2 giây
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        System.out.println(name + " kết thúc.");
    }
    
    
    public static void singleThreadExecutor() {
    	// Khai báo một Thread Pool thông qua newSingleThreadExecutor() của Executors
        ExecutorService executorService = Executors.newSingleThreadExecutor();
             
        // Khai báo 10 Runnable, và "quăng" chúng vào Thread Pool vừa khai báo
        for (int i = 1; i <= 10; i++) {
            MyRunnable myRunnable = new MyRunnable("Runnable " + i);
            executorService.execute(myRunnable);
        }
             
        // Phương thức này sẽ được nói sau ở ExecutorService
        executorService.shutdown();
    }
    
    public static void fixedThreadPool() {
    	// Khai báo một Thread Pool thông qua newFixedThreadPool(5) của Executors.
        // Thread Pool này cho phép thực thi cùng một lúc 5 Thread
        ExecutorService executorService = Executors.newFixedThreadPool(5);
             
        // Khai báo 10 Runnable, và "quăng" chúng vào Thread Pool vừa khai báo
        for (int i = 1; i <= 10; i++) {
            MyRunnable myRunnable = new MyRunnable("Runnable " + i);
            executorService.execute(myRunnable);
        }
             
        // Phương thức này sẽ được nói sau ở ExecutorService
        executorService.shutdown();
    }
    
    public static void cachedThreadPool() {
    	 // Khai báo một Thread Pool thông qua newCachedThreadPool của Executors.
        ExecutorService executorService = Executors.newCachedThreadPool();
             
        // Khai báo 10 Runnable, và "quăng" chúng vào Thread Pool vừa khai báo
        for (int i = 1; i <= 10; i++) {
            MyRunnable myRunnable = new MyRunnable("Runnable " + i);
            executorService.execute(myRunnable);
        }
             
        // Phương thức này sẽ được nói sau ở ExecutorService
        executorService.shutdown();
    }
    
    public static void executors_Summit() {
    	ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future> listFuture = new ArrayList<Future>(); // Khởi tạo danh sách các Future
             
        for (int i = 1; i <= 10; i++) {
            MyRunnable myRunnable = new MyRunnable("Runnable " + i);
            // Bước này chúng ta dùng submit() thay vì execute()
            Future future = executorService.submit(myRunnable);
            listFuture.add(future); // Từng Future sẽ quản lý một Runnable
        }
         
        for (Future future : listFuture) {
            try {
                // Khi Thread nào kết thúc, get() của Future tương ứng sẽ trả về null
                System.out.println(future.get());
            } catch (ExecutionException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
             
        // Phương thức này đã nói ở trên đây rồi
        executorService.shutdown();
    }
    
    public static void main(String[] args) {        
//        singleThreadExecutor();
//        fixedThreadPool();
//        cachedThreadPool();
    	executors_Summit();
    }
 
}