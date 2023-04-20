package com.java8.springboot.java.Callable_Future_Executors;
import java.util.concurrent.*;

// https://stackjava.com/java8/completablefuture-la-gi-code-vi-du-java-completablefuture-java-8.html
public class Demo1 {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    // Calculator ở đây không nhất thiết phải là 1 thread // hàm  supplyAsync Sẽ chuyển nó về thread
    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> Calculator.add(1,2), executor);
    CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> Calculator.add(1,3), executor);
    CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> Calculator.add(2,3), executor);
    /**
     * Khi gọi: future1.get(); thì nó sẽ chờ Calculator.add(1,2) trả về kết quả xong thì mới chạy tiếp các lệnh sau. Do đó dòng result: 1 + 2 = 3 sẽ luôn hiện ra trước dòng Done
     */
    // future1.get();
    
    System.out.println("Done");
    executor.shutdown();
    
    /**
     * 	thenRun là thực hiện làm gì khi CompletableFuture hoàn thành (không cần quan tâm kết quả là gì).
		thenAccept là xử lý kết quả khi CompletableFuture hoàn thành.
		handle dùng xử lý kết quả hoặc lỗi khi CompletableFuture  hoàn thành.
     */
    future1.thenRun(()->{
        System.out.println("future1 completed!");
      });
      future2.thenAccept(result ->{
        System.out.println("future2 completed, result = " + result);
      });
      future3.handle((data, error) ->{
        if (error != null){
          System.out.println("future3 error, error: " + error);
          return null;
        } else {
          System.out.println("future3 completed, result = " + data);
          return data;
        }
        
      });
  }
}