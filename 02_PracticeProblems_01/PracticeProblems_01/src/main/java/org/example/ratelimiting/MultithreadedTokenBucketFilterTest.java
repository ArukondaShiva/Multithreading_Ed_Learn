package org.example.ratelimiting;

import java.util.HashSet;
import java.util.Set;

public class MultithreadedTokenBucketFilterTest {

    public static void test() throws InterruptedException {

        Set<Thread> allThreads = new HashSet<Thread>();

        final MultithreadedTokenBucketFilter tokenBucketFilter = new MultithreadedTokenBucketFilter(1);

        for(int i=0;i<10;i++){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        tokenBucketFilter.getToken();
                    }catch (InterruptedException e){

                    }
                }
            });

            thread.setName("Thread_"+(i+1));
            allThreads.add(thread);
        }


        for(Thread t : allThreads){
            t.start();
        }

        for(Thread t : allThreads){
            t.join();
        }

    }

}
