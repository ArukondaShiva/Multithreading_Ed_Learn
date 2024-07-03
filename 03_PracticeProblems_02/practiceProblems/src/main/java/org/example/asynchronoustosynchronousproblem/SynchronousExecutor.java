package org.example.asynchronoustosynchronousproblem;


public class SynchronousExecutor extends Executor{


    public void asynchronousExecution(CallBack callBack) throws Exception{

        Object signal = new Object();
        final boolean[] isDone = new boolean[1];

        CallBack cb = new CallBack(){
            @Override
            public void done(){
                callBack.done();
                synchronized (signal){
                    signal.notify();
                    isDone[0] = true;
                }
            }
        };


        // Call the parent's asynchronousExecution method
        super.asynchronousExecution(cb);


        // wait for the callback to signal completion
        synchronized (signal){
            while (!isDone[0]){
                signal.wait();
            }
        }

    }


}
