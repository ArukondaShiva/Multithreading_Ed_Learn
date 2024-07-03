package org.example.semaphore;

public class CountingSemaphore {

    int usedPermits = 0;
    int maxCount;


    public CountingSemaphore(int maxCount){
        this.maxCount = maxCount;
    }


    public synchronized void acquire() throws InterruptedException {

        if(usedPermits==maxCount){
            wait();
        }

        usedPermits++;
        notify();
    }


    public synchronized void release() throws InterruptedException {

        if(usedPermits==0){
            wait();
        }

        usedPermits--;
        notify();
    }


}
