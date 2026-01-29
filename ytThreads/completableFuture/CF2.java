package ytThreads.completableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CF2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<CompletableFuture<Void>> cfList = new ArrayList<>();

        Long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            int finalI = i;

            CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Thread - " + Thread.currentThread().getName() + " : Square of " + finalI + " is : " + finalI * finalI);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                return null;
            }, executorService);

            cfList.add(cf);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]));
        allOf.join(); // Wait for all to complete

        System.out.println("All tasks are completed");
        Long end = System.currentTimeMillis();

        System.out.println("Total time taken : " + (end - start) + " ms");
        executorService.shutdown();
    }
}
