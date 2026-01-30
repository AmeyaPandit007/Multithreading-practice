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

        List<CompletableFuture<Integer>> cfList = new ArrayList<>();

        Long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            int finalI = i;

            CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
                int computedResult = finalI * finalI;
                try {
                    System.out.println("Thread - " + Thread.currentThread().getName() + " : Square of " + finalI + " is : " + computedResult);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                return computedResult;
            }, executorService);

            cfList.add(cf);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]));
        allOf.join(); // Wait for all to complete

        List<Integer> results = cfList.stream()
                .map(CompletableFuture::join)
                .toList();

        System.out.println("Results: " + results);

        System.out.println("All tasks are completed");
        Long end = System.currentTimeMillis();

        System.out.println("Total time taken : " + (end - start) + " ms");
        executorService.shutdown();
    }
}
