package ytThreads.example1;

public class Thread1 {
    public static void main(String[] args) throws InterruptedException {

        World world = new World();  // NEW State
        System.out.println("Thread State before start(): " + world.getState());
        world.start();  // RUNNABLE State

        for (int i = 0; i < 10; i++) {
            System.out.println("Hello : " + Thread.currentThread().getName());
            Thread.sleep(5000);
        }
    }
}
