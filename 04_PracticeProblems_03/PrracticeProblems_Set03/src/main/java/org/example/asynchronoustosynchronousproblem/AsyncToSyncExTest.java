package org.example.asynchronoustosynchronousproblem;

public class AsyncToSyncExTest {


    public static void test() throws Exception {

        SynchronousExecutor executor = new SynchronousExecutor();
        executor.asynchronousExecution(() -> {
            System.out.println("I am done");
        });

        System.out.println("main thread exiting...");

    }

}
