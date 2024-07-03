package org.example;

import org.example.BarberShopProblem.BarberShopProblem;
import org.example.asynchronoustosynchronousproblem.AsyncToSyncExTest;
import org.example.blockingQueueProblem.BlockingQueueMonitorTest;
import org.example.blockingQueueProblem.BlockingQueueWithSemaphoreTest;
import org.example.blockingQueueProblem.FaultyBlockingQueueWithMutexTest;
import org.example.diningphilosophersproblem.DiningPhilosophers;
import org.example.implementBarrier.Barrier;
import org.example.mergesortusingmultitreading.MergeSortMultiThread;
import org.example.mergesortusingmultitreading.MergeSortNaive;
import org.example.nonblockingstack.atomicrefsolution.AtomicrefDemo;
import org.example.nonblockingstack.cassolution.CASdemo;
import org.example.nonblockingstack.synchronizedsolution.Syncdemo;
import org.example.ratelimiting.MultithreadedTokenBucketFilterTest;
import org.example.ratelimiting.TokenBucketFilter;
import org.example.ratelimiting.TokenBucketFilterFactoryTest;
import org.example.readWriteLock.ReadWriteLockTest;
import org.example.semaphore.CountingSemaphoreTest;
import org.example.supermanproblem.SupermanNaiveButCorrect;
import org.example.threadsafedeferrcallback.DeferredCallbackExecutor;
import org.example.uberrideproblem.UberSeatingProblem;
import org.example.unisexBathroomProblem.UnisexBathroom;


public class Main {
    public static void main(String[] args) throws Exception {


        // 9. Dining Philosophers
        //DiningPhilosophers.runTest();


        // 10. BarberShop Problem
        // BarberShopProblem.runTest();


        // 11. superman Problem
        // Naive
        //SupermanNaiveButCorrect superManobj =  SupermanNaiveButCorrect.getInstance();
        //superManobj.fly();

        // 12. Merge Sort - Naive, Multi threaded
        //MergeSortNaive.runTest();
        //MergeSortMultiThread.runTest();


        // 13. Asynchronous to Synchronous (Need to revise this problem properly)
        // AsyncToSyncExTest.test();


        // 14. Non Blocking Stack
        //Syncdemo.test();
        //CASdemo.runTest();
        AtomicrefDemo.test();

    }
}