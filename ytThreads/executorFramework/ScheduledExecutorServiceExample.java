package ytThreads.executorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

//        scheduledExecutorService.schedule(
//                () -> System.out.println("Task executed after 3 second delay"),
//                3,
//                TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.println("Task executed after every 3 second .!"),
                3,
                3,
                TimeUnit.SECONDS);  // Overlapping It simply starts the next task after 3 sec

        scheduledExecutorService.scheduleWithFixedDelay(
                () -> System.out.println("Task executed after every 3 second .!"),
                3,
                3,
                TimeUnit.SECONDS); // No Overlapping as it waits for 1st task to finish and then starts the next task after delay of 3 sec

        scheduledExecutorService.schedule(() -> {
            System.out.println("Initiating shutdown.!");
            scheduledExecutorService.shutdown();
        }, 20, TimeUnit.SECONDS);

    }
}
