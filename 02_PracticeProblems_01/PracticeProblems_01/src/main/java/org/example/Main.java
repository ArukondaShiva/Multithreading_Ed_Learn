package org.example;

import org.example.implementBarrier.Barrier;
import org.example.ratelimiting.MultithreadedTokenBucketFilterTest;
import org.example.ratelimiting.TokenBucketFilter;
import org.example.ratelimiting.TokenBucketFilterFactoryTest;
import org.example.readWriteLock.ReadWriteLockTest;
import org.example.semaphore.CountingSemaphoreTest;
import org.example.threadsafedeferrcallback.DeferredCallbackExecutor;
import org.example.uberrideproblem.UberSeatingProblem;
import org.example.unisexBathroomProblem.UnisexBathroom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {


        System.out.println("Let's Solve Some Interesting Problems on Multithreading!.....");

        /*
          01 - Blocking Queue / Consumer Producer / Bounded Buffer
         */

        // using Synchronization
        //BlockingQueueMonitorTest.test();

        // using Mutex
        //FaultyBlockingQueueWithMutexTest.Test();


        // using semaphore
        //BlockingQueueWithSemaphoreTest.test();


        //2.Rate Limiting - using TokenBucketFilter
        /*
        we solved this problem in 3 methods
        1- using single thread (this approach is enough for the interviews)
        2- using multiple threads (creating a daemon thread- which is not a good practice. so using factory pattern to resolve this)
        3- using Factory Pattern
         */
        // TokenBucketFilter.runTestMaxTokenIs1();
        // 2 and Approach
        //MultithreadedTokenBucketFilterTest.test();
        //TokenBucketFilterFactoryTest.test();



        //3. Thread Safe Deferred Callback
        // DeferredCallbackExecutor.runTestTenCallbacks();



        // 4. Counting Semaphore
        //CountingSemaphoreTest.test();


        // 5. ReadWriteLock
        // ReadWriteLockTest.test();


        // 6. UnisexBathroomProblem
        // UnisexBathroom.runTest();


        // 7.Barrier Implementation
        // Barrier.runTest();


        // 8. Uber ride problem
        UberSeatingProblem.runTest();



    }
}