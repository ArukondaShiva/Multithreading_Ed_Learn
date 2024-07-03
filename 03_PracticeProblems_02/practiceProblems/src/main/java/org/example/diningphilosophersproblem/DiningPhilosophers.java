package org.example.diningphilosophersproblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {


    // random variable for testing purpose only
    private static Random random = new Random(System.currentTimeMillis());

    private Semaphore[] forks = new Semaphore[5];
    private Semaphore maxDiners = new Semaphore(4);


    // initializing the semaphores with a permit of 1.
    public DiningPhilosophers(){
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
    }



    // Represents how a philosopher lives his life
    public void lifecycleOfPhilosophers(int id) throws InterruptedException {
        while (true){
            contemplate();
            eat(id);
        }
    }


    // We can sleep the thread when the philosopher is thinking
    void contemplate() throws InterruptedException {
        Thread.sleep(random.nextInt(500));
    }


    // This method is the meat of the solution where the
    // philosopher is trying to eat.
    void eat(int id) throws InterruptedException{

        // maxDiners allows only 4 philosophers to
        // attempt picking up forks. - with 4 philosophers
        // and 5 forks we can avoid deadlock.

        maxDiners.acquire();


        // acquire the left fork first
        forks[id].acquire();

        // acquire the right fork second
        forks[(id+4)%5].acquire();

        // eat to your heart's content
        System.out.println("Philosopher "+id+" is eating");

        // release forks for others to use
        forks[id].release();
        forks[(id+4)%5].release();

        maxDiners.release();
    }


    static void startPhilosopher(DiningPhilosophers dp,int id){
        try{
            dp.lifecycleOfPhilosophers(id);
        }catch (InterruptedException e){

        }
    }


    public static void runTest() throws InterruptedException {

        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp,0);
            }
        });


        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp,1);
            }
        });


        Thread p3 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp,2);
            }
        });


        Thread p4 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp,3);
            }
        });


        Thread p5 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp,4);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        p5.join();


    }



}
