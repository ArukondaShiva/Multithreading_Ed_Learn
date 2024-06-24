package org.example;

import java.util.concurrent.Semaphore;

class IncorrectSemaphoreExample {

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        semaphore.acquire();
                    }catch (InterruptedException e){

                    }

                    //deliberately throwing an Exception
                    throw new RuntimeException("Exception happens at runtime");

                    // The following line to signal the semaphore is never reached
                    //semaphore.release();
                }
            }
        });

        badThread.start();

        Thread.sleep(1000);

        final Thread goodThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Good thread patiently waiting to be signalled");
                try{
                    semaphore.acquire();
                    System.out.println("good Thread acquired the lock");
                }catch (InterruptedException e){

                }
            }
        });

        goodThread.start();
        badThread.join();
        goodThread.join();

        System.out.println("Exiting the program");
    }
}
