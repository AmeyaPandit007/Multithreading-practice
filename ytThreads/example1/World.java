package ytThreads.example1;

public class World extends Thread {
    @Override
    public void run() {
        System.out.println("Thread State inside run(): " + Thread.currentThread().getState());
        // RUNNING State
        for (int i = 0; i < 10; i++) {
            System.out.println("World : " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
