package org.example.readWriteLock;

public class ReadWriteLockTest {


    public static void test() throws InterruptedException {


        final ReadWriteLock rw1 = new ReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println("Attempting to acquire the write lock in t1 " + System.currentTimeMillis());
                    rw1.acquireWriteLock();
                    System.out.println("Write lock acquired t1: " + System.currentTimeMillis());

                    //simulate the write lock being held indefinitely
                    for (; ; ) {
                        Thread.sleep(500);
                    }

                } catch (InterruptedException e) {

                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("Attempting to acquire write lock in t2 :" + System.currentTimeMillis());
                    rw1.acquireWriteLock();
                } catch (InterruptedException e) {

                }
            }
        });


        Thread tReader1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rw1.acquireReadLock();
                    System.out.println("Read lock acquired :" + System.currentTimeMillis());
                } catch (InterruptedException e) {

                }
            }
        });


        Thread tReader2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Read lock about to release: " + System.currentTimeMillis());
                rw1.releaseReadLock();
                System.out.println("Read lock releases : " + System.currentTimeMillis());
            }
        });


        tReader1.start();
        t1.start();

        Thread.sleep(3000);

        tReader2.start();
        Thread.sleep(1000);
        t2.start();

        tReader1.join();
        tReader2.join();
        t2.join();


    }


}
