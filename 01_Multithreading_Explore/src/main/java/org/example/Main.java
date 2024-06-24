package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

         /*
        1.This code is to check the performance b/w single threaded vs 2 threaded environment
        in adding range of numbers(1 to MAX_NUM).
         */
        //SumUpExample.runTest();



        /*
        2.without synchronization
        one thread incrementing the counter by 100 times while another decrementing by 100 times
         */
        //DemoThreadUnsafe.DemoThreadUnsafeTest();


        /*
        Race Condition - Code
        Race Condition - fix Code
         */
        //RaceCondition.runTest();
        //RaceConditionFix.runTest();


        // Creating a Deadlock
        //Deadlock d = new Deadlock();
        //d.runTest();


        /*
        Re-entrant locks allow for re-locking or re-entering of a synchronization lock
        But NonReentrant does not allow re-locking or re-entering --> leads to self deadlock
         --- NonReentrantLock ---
         */

        //NonReentrantLock nonReentrantLock = new NonReentrantLock();
        //nonReentrantLock.runTest();


        /*
         We synchronize on a Boolean object in the first thread but sleep before we call wait() on the object. While the first thread is asleep,
         the second thread goes on to change the flag's value. When the first thread wakes up and attempts to invoke wait(), it is met with a
         IllegalMonitorState exception! The object the first thread synchronized on before going to sleep has been changed, and now it is attempting
         to call wait() on an entirely different object without having synchronized on it.
         */
        //IncorrectSynchronization.runExample();


        // Interrupting a Thread
        //InterruptExample.example();



        //MissedSignalExample.example();
        //FixedMissedSignalExample.example();


        /*
        The above code when run would time out and show that one of the threads threw an exception.
        The code is never able to release the semaphore causing the other thread to block forever.
         */
        //IncorrectSemaphoreExample.example();
        //CorrectSemaphoreExample.example();



        // Atomic Class Introduction Example
        //AtomicClassDemo.example();


        // Atomic Classes Demonstration
        // AtomicDemonstration.example();


        //Non-blocking counter
        NonblockingCounter counter = new NonblockingCounter();

        counter.increment();
        counter.increment();
        counter.increment();

        System.out.println(counter.get());



    }
}