package ThreadStart;

public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Code that will run in a new thread
                System.out.println("We are in a new Thread : "+Thread.currentThread().getName());
                System.out.println("Current Thread priority is : "+ Thread.currentThread().getPriority());
            }
        });

        thread.setName("New Worker Thread");

        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: "+Thread.currentThread().getName()+" before starting a new Thread");
        System.out.println("Current State of the thread: "+ thread.getState());
        thread.start();
        System.out.println("We are in thread: "+Thread.currentThread().getName()+" after starting a new Thread");
        System.out.println("Current State of the thread: "+ thread.getState());

        Thread.sleep(10000);
    }
}
