package org.example.BarberShopProblem;

import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShopProblem {

    final int CHAIRS = 3;

    // controls the flow of customers entering the shop
    Semaphore waitForCustomerToEnter = new Semaphore(0);

    // Synchronizes the barber getting ready for the next customer.
    Semaphore waitForBarberToGetReady = new Semaphore(0);

    //Synchronizes the customer's departure after the haircut.
    Semaphore waitForCustomerToLeave = new Semaphore(0);

    //  Ensures the barber can start cutting hair.
    Semaphore waitForBarberToCutHair = new Semaphore(0);


    int waitingCustomers = 0;
    ReentrantLock lock = new ReentrantLock();
    int hairCutsGiven = 0;


    void customerWalksIn() throws InterruptedException{

        lock.lock();

        if(waitingCustomers==CHAIRS){
            System.out.println("Customer walks out, all chairs occupied");

            // unlock before leaving
            lock.unlock();
            return;
        }

        waitingCustomers++;
        lock.unlock();

        /*
          Signals that a customer has entered :
          This signals that a customer has entered the shop. It increments the semaphore count, allowing the barber to acquire it.
         */
        waitForCustomerToEnter.release();


        /*
        Waits for the barber to get ready :
        The customer waits until the barber is ready. This decrements the semaphore count once the barber signals that he is ready.
         */
        waitForBarberToGetReady.acquire();


        // Waits for the barber to start cutting hair.
        // The customer waits until the barber starts cutting hair.
        waitForBarberToCutHair.acquire();

        // The customer signals that they are leaving after the haircut.
        waitForCustomerToLeave.release();

        lock.lock();
        waitingCustomers--;
        lock.unlock();
    }


    void barber() throws InterruptedException{

        // barber runs an infinite loop
        while(true){

            // Wait for a customer to enter
            waitForCustomerToEnter.acquire();

            //signals that the barber is ready
            waitForBarberToGetReady.release();
            hairCutsGiven++;
            System.out.println("Barber cutting hair..."+hairCutsGiven);

            // simulating the hair cut
            Thread.sleep(50);

            // Signal that the barber is cutting hair
            waitForBarberToCutHair.release();

            // waits for the customer to leave.
            waitForCustomerToLeave.acquire();
        }

    }


    public static void runTest() throws InterruptedException {

        HashSet<Thread> set = new HashSet<>();
        final BarberShopProblem barberShopProblem = new BarberShopProblem();

        Thread barberThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    barberShopProblem.barber();
                }catch (InterruptedException e){

                }
            }
        });


        barberThread.start();


        for(int i=0;i<10;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    try{
                        barberShopProblem.customerWalksIn();
                    }catch (InterruptedException e){

                    }

                }
            });

            set.add(t);
        }


        for(Thread t : set){
            t.start();
        }

        for(Thread t : set){
            t.join();
        }



        set.clear();
        Thread.sleep(800);


        for(int i=0;i<5;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        barberShopProblem.customerWalksIn();
                    }catch (InterruptedException e){

                    }
                }
            });

            set.add(t);
        }

        for(Thread t : set){
            t.start();
        }

        barberThread.join();

    }




}
