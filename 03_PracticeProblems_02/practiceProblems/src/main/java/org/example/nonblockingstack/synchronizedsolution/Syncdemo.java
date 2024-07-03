package org.example.nonblockingstack.synchronizedsolution;

import java.util.concurrent.*;

public class Syncdemo {

    public static void test() throws InterruptedException {

        SynchronizedStack<Integer> stackOfInts = new SynchronizedStack<>();

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int numThreads = 2;
        CyclicBarrier barrier = new CyclicBarrier(numThreads);

        Integer testValue = 51;

        try{
            for(int i=0;i<numThreads;i++){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {

                        for(int i=0;i<10000;i++){
                            stackOfInts.push(testValue);
                        }

                        try{
                            barrier.await();
                        }catch (InterruptedException | BrokenBarrierException e){
                            System.out.println("ignoring exception");
                        }


                        for(int i=0;i<10000;i++){
                            stackOfInts.pop();
                        }

                    }
                });
            }
        }finally{
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }

    }

}
