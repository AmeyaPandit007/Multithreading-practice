package ytThreads.reentrantExample;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        try {
            if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
                System.out.println("Lock acquired by " + Thread.currentThread().getName());
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " processing with withdrawal");
                        Thread.sleep(3000);
                        balance = balance - amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();  // Good Practice
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance.!");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock. Will try again later.!");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

    }

    public int checkBalance() {
        return balance;
    }
}
