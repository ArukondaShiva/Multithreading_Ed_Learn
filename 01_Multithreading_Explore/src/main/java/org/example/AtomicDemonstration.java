package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemonstration {

    static AtomicInteger counter = new AtomicInteger(0);
    static int simpleCounter = 0;

    synchronized static void incrementSimpleCounter(){
        simpleCounter++;
    }

    static void test(boolean isAtomic) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();

        try{
            for(int i=0;i<10;i++){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<1000000;i++){

                            if(isAtomic){
                                counter.incrementAndGet();
                            }else{
                                incrementSimpleCounter();
                            }
                        }
                    }
                });
            }
        }finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }

        long timeTaken = System.currentTimeMillis() - start;

        System.out.println("Time taken by "+(isAtomic ? "atomic integer counter " : "integer counter")+timeTaken+" milliseconds");

    }


    public static void example() throws InterruptedException {
        test(true);
        test(false);
    }

}
