package org.example;

import java.util.concurrent.Semaphore;

public class CorrectSemaphoreExample {


    /*
    Whenever using locks or semaphores, remember to unlock or release the semaphore in a finally block.
    The corrected version appears below.
     */


    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        semaphore.acquire();
                        try{
                            throw new RuntimeException("");
                        }catch (Exception e){
                            // handle any program logic exception and exit the function
                            return;
                        }finally {
                            System.out.println("Bad Thread releasing the block");
                            semaphore.release();
                        }
                    }catch (InterruptedException e){

                    }
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
                    System.out.println("Good Thread acquired the lock");
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
