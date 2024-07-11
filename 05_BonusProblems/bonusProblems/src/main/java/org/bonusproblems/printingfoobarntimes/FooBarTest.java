package org.bonusproblems.printingfoobarntimes;

public class FooBarTest {

    public static void test(){

        FooBar fooBar = new FooBar(3);

        Thread t1 = new FooBarThread(fooBar,"foo");
        Thread t2 = new FooBarThread(fooBar,"bar");

        t2.start();
        t1.start();

    }

}
