package ytThreads.countDownLatch;

import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//        Future<String> future1 = executorService.submit(new DependentService());
//        Future<String> future2 = executorService.submit(new DependentService());
//        Future<String> future3 = executorService.submit(new DependentService());
//
//        future1.get();
//        future2.get();
//        future3.get();
//
//        System.out.println("All dependent services finished. Starting main service");
//
//        executorService.shutdown();

        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);

        CountDownLatch latch = new CountDownLatch(numberOfServices);

        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));

        latch.await();

        System.out.println("Main");
        executorService.shutdown();

    }
}


class DependentService implements Callable<String> {

    private final CountDownLatch countDownLatch;

    public DependentService(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " service started.!");
        } catch(Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            countDownLatch.countDown();
        }
        return "OK";
    }
}