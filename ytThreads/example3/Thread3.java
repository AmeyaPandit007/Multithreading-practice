package ytThreads.example3;

public class Thread3 extends Thread {

    @Override
    public void run() {
        System.out.println("Ameya Pandit");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread3 t3 = new Thread3();    // NEW STATE
        System.out.println(t3.getState());

        t3.start();        // RUNNABLE STATE
        System.out.println(t3.getState());

        Thread.sleep(100);
        System.out.println(t3.getState());  // TIMED_WAITING STATE

        t3.join();   // Main thread waits for t3 to get completed

        System.out.println(t3.getState());  // TERMINATED STATE
    }
}
