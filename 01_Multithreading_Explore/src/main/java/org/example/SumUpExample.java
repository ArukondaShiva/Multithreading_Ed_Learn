package org.example;

public class SumUpExample {

    long startRange;
    long endRange;
    long counter = 0;
    static long MAX_NUM = Integer.MAX_VALUE;

    public SumUpExample(long startRange,long endRange){
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add(){
        for(long i=startRange;i<=endRange;i++){
            counter += i;
        }
    }


    static public void twoThreads() throws InterruptedException {
        long start = System.currentTimeMillis();
        SumUpExample s1 = new SumUpExample(1,MAX_NUM/2);
        SumUpExample s2 = new SumUpExample((MAX_NUM/2)+1,MAX_NUM);

        Thread thread1 = new Thread(()->{
            s1.add();
        });

        Thread thread2 = new Thread(()->{
            s2.add();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long finalcount = s1.counter+ s2.counter;
        long end = System.currentTimeMillis();
        System.out.println("Two threads final count = "+finalcount+" took "+(end-start));
    }


    static public void oneThread(){

        long start = System.currentTimeMillis();
        SumUpExample s = new SumUpExample(1,MAX_NUM);
        s.add();
        long end = System.currentTimeMillis();
        System.out.println("Single thread final Count = "+s.counter+" took "+(end-start));

    }


    public static void runTest() throws InterruptedException {

        /*
        This code is to check the performance b/w single threaded vs 2 threaded environment
        in adding range of numbers(1 to MAX_NUM).
         */

        oneThread();
        twoThreads();
    }


}
