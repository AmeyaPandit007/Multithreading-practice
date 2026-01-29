package ytThreads.example4;

public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(5000);
                System.out.println("Thread is Running...!");
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted : " + e);
            }
            System.out.println("Name : " + Thread.currentThread().getName()
                    + " - Priority : " + Thread.currentThread().getPriority()
                    + " - Count : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        MyThread l = new MyThread("Low Priority Thread");
//        MyThread m = new MyThread("Medium Priority Thread");
//        MyThread h = new MyThread("High Priority Thread");
//
//        l.setPriority(Thread.MIN_PRIORITY);
//        m.setPriority(Thread.NORM_PRIORITY);
//        h.setPriority(Thread.MAX_PRIORITY);
//
//        l.start();
//        m.start();
//        h.start();

        System.out.println(Thread.currentThread().getName()
                + " - Priority : " + Thread.currentThread().getPriority());

        MyThread t1 = new MyThread("t1");
        t1.start();
        t1.interrupt();
    }
}
