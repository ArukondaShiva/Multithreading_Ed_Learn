package org.example.threadsafedeferrcallback;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {


    PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {
        @Override
        public int compare(CallBack o1, CallBack o2) {
            return (int) (o1.executeAt-o2.executeAt);
        }
    });


    // Lock to guard critical section.
    ReentrantLock lock = new ReentrantLock();


    //Condition to make execution thread wait on
    /*
    this is a condition variable on which consumer thread will signal on it
    while executor thread waits on it. whenever a new callback is
    registered  the consumer thread will wake up executor thread by signalling.
     */
    Condition newCallbackArrived = lock.newCondition();



    // Run by the Executor Thread
    public void start() throws InterruptedException {

        long sleepFor = 0;

        while(true){

            //lock the critical section
            lock.lock();

            // if no item in the queue, wait indefinitely for one to arrive.
            while (q.size()==0){
                newCallbackArrived.await();
            }

            // loop till all callbacks have been executed
            while(q.size()!=0){

                // find the minimum time execution thread should
                // sleep for before the next callback becomes due
                sleepFor = findSleepDuration();


                // if the callback is due break from loop and start
                // executing the callback
                if(sleepFor<=0){
                    break;
                }

                // sleep until the earliest due callback can be executed
                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
            }


            // Because we have a min-Heap, the first element of the queue
            // is necessarily the one which is due
            CallBack cb = q.poll();

            System.out.println("Executed at "+System.currentTimeMillis()/1000 + " required at "+cb.executeAt/1000+" : message : "+cb.message);

            //unlocking the critical section
            lock.unlock();
        }


    }


    private long findSleepDuration(){
        long currentTime = System.currentTimeMillis();
        return q.peek().executeAt-currentTime;
    }



    // Called by the consumer Threads to register callback
    public void registerCallback(CallBack callBack){
        lock.lock();
        q.add(callBack);

        newCallbackArrived.signal();
        /*
        As a reminder, note the execution thread, if waiting on the condition variable, will not be able to
         make progress until the consumer thread gives up the lock, even though the condition has been signaled.
         */

        lock.unlock();
    }



    public static void runTestTenCallbacks() throws InterruptedException {

        Set<Thread> allThreads = new HashSet<Thread>();
        final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

        Thread service = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    deferredCallbackExecutor.start();
                }catch (InterruptedException e){

                }
            }
        });

        service.start();


        /*

        for(int i=0;i<10;i++){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    CallBack cb = new CallBack(1,"Hello this is "+Thread.currentThread().getName());
                    deferredCallbackExecutor.registerCallback(cb);
                }
            });


            thread.setName("Thread_"+(i+1));
            thread.start();
            allThreads.add(thread);
            Thread.sleep(1000);
        }


        for(Thread t : allThreads){
            t.join();
        }

         */



        Thread lateThread = new Thread(new Runnable() {
            public void run() {
                CallBack cb = new CallBack(8, "Hello this is the callback submitted first");
                deferredCallbackExecutor.registerCallback(cb);
            }
        });
        lateThread.start();

        Thread.sleep(3000);

        Thread earlyThread = new Thread(new Runnable() {
            public void run() {
                CallBack cb = new CallBack(1, "Hello this is callback sumbitted second");
                deferredCallbackExecutor.registerCallback(cb);
            }
        });
        earlyThread.start();

        lateThread.join();
        earlyThread.join();

    }



}
