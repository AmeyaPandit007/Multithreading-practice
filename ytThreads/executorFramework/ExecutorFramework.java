package ytThreads.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println("Thread : " + Thread.currentThread().getName() + " - Factorial of : "+ finalI + " is : " + result);
            });
        }

        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
                System.out.println("Waiting...");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total time : " + (System.currentTimeMillis() - startTime));
    }

    private static int factorial(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int fact = 1;
        for (int j = i; j >= 1; j--) {
            fact = fact * j;
        }
        return fact;
    }
}
