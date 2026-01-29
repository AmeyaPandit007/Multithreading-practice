package ytThreads.synchronizedExample;

public class SynchronizedExample extends Thread {

    private Counter counter;

    public SynchronizedExample(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();  // Shared Resource
        SynchronizedExample t1 = new SynchronizedExample(counter);
        SynchronizedExample t2 = new SynchronizedExample(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount());
    }
}
