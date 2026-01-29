package ytThreads.executorFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorFramework2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> "Callable(call) Task";
        executorService.submit(() -> System.out.println("Runnable(run) Task"));    // It does not return something
        Future<?> future = executorService.submit(callable);   // It returns something

        System.out.println(future.get()); // get waits for future to get completed
        if (future.isDone()) {
            System.out.println("Task is done.!");
        }
        executorService.shutdown();

        // Example 2
//        long startTime = System.currentTimeMillis();
//        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
//
        List<String> strings = List.of("amema", "banana", "thrrht", "robust", "kk", "SAAS");
//
//        List<Future<Boolean>> futures = new ArrayList<>();
//        for (String s : strings) {
//            futures.add(executorService1.submit(() -> isPalindrome(s)));
//        }
//
//        for (int i = 0; i < strings.size(); i++) {
//            boolean result = futures.get(i).get();
//            System.out.println(result ? strings.get(i) + " is Palindrome." : strings.get(i) + " is not a Palindrome.");
//        }
//
//        executorService1.shutdown();
//        Thread.sleep(100);
//        System.out.println(executorService1.isTerminated());
//        System.out.println("Time taken :- " + (System.currentTimeMillis() - startTime));

        // Example 2 - Different way
        long startTime = System.currentTimeMillis();
        List<Callable<Boolean>> callableList = new ArrayList<>();
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        for(String s : strings) {
            callableList.add(() -> isPalindrome(s));
        }

        List<Future<Boolean>> futures2 = executorService2.invokeAll(callableList);
        for (int i = 0; i < strings.size(); i++) {
            boolean result = futures2.get(i).get();
            System.out.println(result ? strings.get(i) + " is Palindrome." : strings.get(i) + " is not a Palindrome.");
        }

        executorService2.shutdown();
        executorService2.awaitTermination(500, TimeUnit.MILLISECONDS);
        System.out.println(executorService2.isTerminated());
        System.out.println("Time taken :- " + (System.currentTimeMillis() - startTime));

    }

    private static boolean isPalindrome(String str) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is calculating palindrome check for " + str);
        Thread.sleep(1000);
        return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
    }

}
