package org.example;

import java.util.Random;

public class RaceCondition {

    int randInt;
    Random random = new Random(System.currentTimeMillis());

    void printer(){
        int i = 1000000;
        while(i!=0){
            if(randInt%5==0){

                /*
                Actually we are in if condition after checking whether randInt divisible by 5 or not
                But, as it is a critical section we can see the randInt which is not divisible by 5
                because of multiple threads accessing and modifying (Race Condition)
                 */
                if(randInt%5!=0){
                    System.out.println(randInt);
                }
            }
            i--;
        }
    }


    void modifier(){
        int i = 1000000;
        while (i!=0){
            randInt = random.nextInt(1000);
            i--;
        }
    }


    public static void runTest() throws InterruptedException {

        final RaceCondition rc = new RaceCondition();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                rc.printer();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                rc.modifier();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }


}
