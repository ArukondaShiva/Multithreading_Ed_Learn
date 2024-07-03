package org.example.ratelimiting;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilterFactoryTest {

    public static void test() throws InterruptedException {

        Set<Thread> allThreads = new HashSet<>();
        TokenBucketFilterAbs tokenBucketFilter = TokenBucketFilterFactory.makeTokenBucketFilter(1);

        for(int i=0;i<10;i++){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        tokenBucketFilter.getToken();
                    }catch (InterruptedException e){
                        e.printStackTrace();
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
