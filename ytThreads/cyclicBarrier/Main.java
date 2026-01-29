package ytThreads.cyclicBarrier;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int numberOfSubsystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("All subsystems are up and running. System startup complete.!");
            }
        });

        Thread webServerThread = new Thread(new Subsystem("Web Server", 2000, barrier), "thread-1");
        Thread databaseThread = new Thread(new Subsystem("Database", 4000, barrier), "thread-2");
        Thread cacheThread = new Thread(new Subsystem("Cache", 3000, barrier), "thread-3");
        Thread messagingServiceThread = new Thread(new Subsystem("Messaging Service", 3500, barrier), "thread-4");

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();
    }
}

class Subsystem implements Runnable {
    private String name;
    private int initializationTime;
    private final CyclicBarrier barrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started by " + Thread.currentThread().getName());
            Thread.sleep(initializationTime);
            System.out.println(name + " intialization completed by " + Thread.currentThread().getName());
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
