package ytThreads.volatileKeyword;

class SharedResource {
    private volatile boolean flag = false;

    public void setFlagTrue() {
        System.out.println("Writer thread made the flag true.!");
        flag = true;
    }

    public void printIfFlagTrue() {
        while (!flag) {
            // Do Nothing.
            try {
                Thread.sleep(500);
                System.out.println("Stuck in a loop as flag is still false.!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Flag is true now.!");
    }
}

public class VolatileExample {
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Runnable writerTask = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sharedResource.setFlagTrue();
        };

        Runnable readerTask = () -> {
            sharedResource.printIfFlagTrue();
        };

        Thread writerThread = new Thread(writerTask);
        Thread readerThread = new Thread(readerTask);

        writerThread.start();
        readerThread.start();
    }
}
