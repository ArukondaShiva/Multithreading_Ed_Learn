package org.example;

public class NonReentrantLock {

    boolean isLocked;

    public NonReentrantLock(){
        isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }


    public void runTest() throws InterruptedException {
        NonReentrantLock nreLock = new NonReentrantLock();

        // First locking would be successful
        nreLock.lock();
        System.out.println("Acquired the first lock");

        // Second Locking results in self deadlock
        System.out.println("Trying to acquire the second lock");
        nreLock.lock();
        System.out.println("Acquired second lock");
    }


}
