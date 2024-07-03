package org.example.ratelimiting;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilter {

    private int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens = 0;

    public TokenBucketFilter(int maxTokens){
        MAX_TOKENS = maxTokens;
    }


    synchronized void getToken() throws InterruptedException {

        possibleTokens += (System.currentTimeMillis()-lastRequestTime)/1000;

        System.out.println("Possible Tokens : "+possibleTokens);

        if(possibleTokens > MAX_TOKENS){
            possibleTokens = MAX_TOKENS;
        }

        //System.out.println("Possible Tokens before consume : "+possibleTokens);

        if(possibleTokens == 0){
            Thread.sleep(1000);
        }else{
            System.out.println("----inside--");
            possibleTokens--;
        }

        //System.out.println("Possible Tokens after consume : "+possibleTokens);

        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting "+Thread.currentThread().getName()+" token at "+System.currentTimeMillis()/1000);
    }



    public static void runTestMaxTokenIs1() throws InterruptedException {

        Set<Thread> allThreads = new HashSet<Thread>();
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(5);

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

        Thread.sleep(3000);

        for(Thread t : allThreads){
            t.start();
        }

        for(Thread t : allThreads){
            t.join();
        }
    }




}
