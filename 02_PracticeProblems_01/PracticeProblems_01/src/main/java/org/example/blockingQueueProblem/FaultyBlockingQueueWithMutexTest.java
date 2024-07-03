package org.example.blockingQueueProblem;

import java.util.TreeMap;

public class FaultyBlockingQueueWithMutexTest {

    static final FaultyBlockingQueueWithMutex<Integer> q = new FaultyBlockingQueueWithMutex<Integer>(5);

    static void producerThread(int start,int id){
        while (true){
            try{
                q.enqueue(start);
                System.out.println("Producer thread "+id+" enqueued "+start);
                start++;
                Thread.sleep(1);
            }catch (InterruptedException e){

            }
        }
    }


    static void consumerThread(int id){
        while (true){
            try{
                System.out.println("Consumer thread "+id+" dequeued "+q.dequeue());
                Thread.sleep(1);
            }catch (InterruptedException e){

            }
        }
    }



    public static void Test() throws InterruptedException {

        Thread producer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                producerThread(1,1);
            }
        });


        Thread producer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                producerThread(5000,2);
            }
        });


        Thread producer3 = new Thread(new Runnable() {
            @Override
            public void run() {
                producerThread(100000,3);
            }
        });


        Thread consumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                consumerThread(1);
            }
        });


        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                consumerThread(2);
            }
        });


        Thread consumer3 = new Thread(new Runnable() {
            @Override
            public void run() {
                consumerThread(3);
            }
        });


        producer1.setDaemon(true);
        producer2.setDaemon(true);
        producer3.setDaemon(true);

        consumer1.setDaemon(true);
        consumer2.setDaemon(true);
        consumer3.setDaemon(true);

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

        Thread.sleep(20000);

    }




}
