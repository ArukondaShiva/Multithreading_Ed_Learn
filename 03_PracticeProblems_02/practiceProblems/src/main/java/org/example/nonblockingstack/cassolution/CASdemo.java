package org.example.nonblockingstack.cassolution;

import java.util.concurrent.*;

public class CASdemo {

    public static void runTest() throws InterruptedException {

        CASBasedStack<Integer> stack = new CASBasedStack<>();
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
                            stack.push(testValue);
                        }


                        try{
                            barrier.await();
                        }catch (InterruptedException | BrokenBarrierException e){
                            System.out.println("ignoring exception");
                        }

                        for(int i=0;i<10000;i++){
                            stack.pop();
                        }

                    }

                });

            }

        }finally {
            executorService.shutdown();
            executorService.awaitTermination(1,TimeUnit.HOURS);
        }

        System.out.println("10,000 pushes and pops completed by 2 threads. Current top of stack " + stack.pop());
    }

}
