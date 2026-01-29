package ytThreads.threadCommunication;

class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Thread : " + Thread.currentThread().getName() + " - Produced : " + value);
        notify();
    }

    public synchronized int consume() {
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        System.out.println("Thread : " + Thread.currentThread().getName() + " - Consumed : " + data);
        return data;
    }
}

public class ThreadCommunication {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Runnable consumerTask = () -> {
            for (int i = 0; i < 10; i++) {
                int value = sharedResource.consume();
            }
        };

        Runnable producerTask = () -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.produce(i);
            }
        };

        Thread t1 = new Thread(producerTask, "producer-thread");
        Thread t2 = new Thread(consumerTask, "consumer-thread");

        t1.start();
        t2.start();
    }
}
