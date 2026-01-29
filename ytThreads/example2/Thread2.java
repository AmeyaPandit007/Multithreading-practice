package ytThreads.example2;


public class Thread2 {
    public static void main(String[] args) {
        World world = new World();
        Thread t1 = new Thread(world);
        t1.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("Ameya : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
