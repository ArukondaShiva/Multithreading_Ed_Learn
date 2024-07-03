package org.example.ratelimiting;

import java.util.TreeMap;

public class MultithreadedTokenBucketFilter {

    private long possibleTokens = 0;
    private final int MAX_TOKENS;
    private final int ONE_SECOND = 1000;


    public MultithreadedTokenBucketFilter(int maxTokens){
        MAX_TOKENS = maxTokens;

        // creating a daemon Thread - for inserting the tokens continuously(1 token after every second)
        Thread st = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    daemonThread();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // making it as a demon thread
        st.setDaemon(true);
        st.start();

    }



    private void daemonThread() throws InterruptedException {

        while (true){

            synchronized (this){
                if(possibleTokens<MAX_TOKENS){
                    possibleTokens++;
                }
                this.notify();
            }

            Thread.sleep(1000);
        }
    }

    void getToken() throws InterruptedException {

        synchronized (this){
            while (possibleTokens==0){
                this.wait();
            }
            possibleTokens--;
        }

        System.out.println("Granting "+ Thread.currentThread().getName()+" token at "+System.currentTimeMillis()/1000);

    }


}
