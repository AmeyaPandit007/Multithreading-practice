package ytThreads.completableFuture;

import java.util.concurrent.*;

public class CF {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Worker " + Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            return "OK";
        }, executorService);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Worker " + Thread.currentThread().getName());
                Thread.sleep(4000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            return "OK";
        }, executorService);

        CompletableFuture<Void> result = CompletableFuture.allOf(completableFuture1, completableFuture2);
        result.join();

        System.out.println("Main");
        System.out.println("Total Time Taken :- " + (System.currentTimeMillis() - startTime));

//        String s = null;
////        s = completableFuture1.get();
//        s = completableFuture1.getNow("No");
//        System.out.println(s);
//
//        System.out.println("Main");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Worker " + Thread.currentThread().getName());
                Thread.sleep(4000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            return "OK";
        }, executorService).orTimeout(2, TimeUnit.SECONDS).exceptionally(s -> "Timeout Occurred.!");
        System.out.println(cf1.get());

        executorService.shutdown();
    }
}
