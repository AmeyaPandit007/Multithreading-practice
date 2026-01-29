package ThreadStart;

public class Example2 {

    public static class NewThread extends Thread {
        @Override
        public void run() {
            // Code that executes on the new Thread
            System.out.println("Hello from : "+Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.start();
    }
}
