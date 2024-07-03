package org.example.ratelimiting;

public class TokenBucketFilterFactory {

    private TokenBucketFilterFactory(){
    }

     static public TokenBucketFilterAbs makeTokenBucketFilter(int capacity){
        MultithreadedTokenBucketFilter tbf = new MultithreadedTokenBucketFilter(capacity);
        tbf.initialize();
        return tbf;
    }


    private static class MultithreadedTokenBucketFilter extends TokenBucketFilterAbs{
        private long possibleTokens = 0;
        private final int MAX_TOKENS;
        private final int ONE_SECOND = 1000;


        MultithreadedTokenBucketFilter(int maxTokens){
            MAX_TOKENS = maxTokens;
        }

        void initialize(){
            Thread dt = new Thread(new Runnable() {
                @Override
                public void run() {
                    daemonThread();
                }
            });

            dt.setDaemon(true);
            dt.start();
        }


        private void daemonThread(){

            while (true){

                synchronized (this){
                    if(possibleTokens < MAX_TOKENS){
                        possibleTokens++;
                    }
                    this.notify();
                }

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }



        public void getToken() throws InterruptedException {

            synchronized (this){
                while (possibleTokens==0){
                    this.wait();
                }
                possibleTokens--;
            }

            System.out.println("Granting "+Thread.currentThread().getName()+" token at "+System.currentTimeMillis()/1000);
        }



    }



}
