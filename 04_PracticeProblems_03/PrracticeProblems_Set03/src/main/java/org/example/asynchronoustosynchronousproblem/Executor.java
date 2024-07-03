package org.example.asynchronoustosynchronousproblem;

public class Executor {


    public void asynchronousExecution(CallBack callBack) throws Exception{

       Thread t = new Thread(()->{
           // simulate work
           try{
               Thread.sleep(5000);
           }catch (InterruptedException e){

           }

           // execute the callback
           callBack.done();
       });

       t.start(); // start the thread

    }



}
