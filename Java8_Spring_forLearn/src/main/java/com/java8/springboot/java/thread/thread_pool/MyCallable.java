package com.java8.springboot.java.thread.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
	 
    // Tên của Callable, giúp chúng ta phân biệt Runnable nào đang thực thi trong
    // Thread Pool
    private String name;
 
    public MyCallable(String name) {
        // Khởi tạo Callable với biến name truyền vào
        this.name = name;
    }
 
    @Override
    public String call() throws Exception {
        System.out.println(name + " đang thực thi...");
         
        // Giả lập thời gian chạy của Callable mất 2 giây
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        // Trả kết quả về là một kiểu String
        return name;
    }
    
    public static void main(String[] args) throws InterruptedException {        
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> listFuture = new ArrayList<Future<String>>(); // Khởi tạo danh sách các Future
             
        for (int i = 1; i <= 10; i++) {
            // Dùng Callable thay cho Runnable
            MyCallable myCallable = new MyCallable("Callable " + i);
                 
            Future<String> future = executorService.submit(myCallable);
            listFuture.add(future); // Từng Future sẽ quản lý một Callable
        }
             
        for (Future future : listFuture) {
            try {
                // Khi Thread nào kết thúc, get() của Future tương ứng sẽ trả về kết quả mà Callable return
                System.out.println(future.get() + " kết thúc");
            } catch (ExecutionException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
             
        // Phương thức này đã nói ở trên đây rồi
        executorService.shutdown();
    }
}