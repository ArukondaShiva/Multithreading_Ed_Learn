package org.example;

public class Deadlock {

    private int counter = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();


    Runnable incrementer = new Runnable() {
        @Override
        public void run() {
            try{
                for(int i=0;i<100;i++){
                    incrementCounter();
                    System.out.println("Incrementing "+i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };


    Runnable decrementer = new Runnable() {
        @Override
        public void run() {
            try{
                for(int i=0;i<100;i++){
                    decrementCounter();
                    System.out.println("Decrementing "+i);
                }
            }catch (InterruptedException e){
                throw  new RuntimeException();
            }
        }
    };


    public void runTest() throws InterruptedException {

        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(decrementer);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }


    void incrementCounter() throws InterruptedException {

        synchronized (lock1){
            System.out.println("Acquired lock1 - increment Method");
            Thread.sleep(100);

            synchronized (lock2){
                System.out.println("Acquired lock2 - increment Method");
                counter++;
            }
        }

    }


    void decrementCounter() throws InterruptedException {

        synchronized (lock2){
            System.out.println("Acquired lock2 - decrementing method");
            Thread.sleep(100);
            synchronized (lock1){
                System.out.println("Acquired lock1 - decrementing method");
                counter--;
            }
        }
    }


}