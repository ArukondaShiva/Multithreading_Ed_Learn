package org.example.semaphore;

public class CountingSemaphoreTest {


    public static void test() throws InterruptedException {


        final CountingSemaphore cs = new CountingSemaphore(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0;i<5;i++){
                        cs.acquire();
                        System.out.println("Ping "+i);
                    }
                }catch (InterruptedException e){
                }
            }
        });



        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try{
                        cs.release();
                        System.out.println("Pong "+i);
                    }catch (InterruptedException e){
                    }
                }
            }
        });


        t2.start();
        t1.start();


        t1.join();
        t2.join();

    }

}
