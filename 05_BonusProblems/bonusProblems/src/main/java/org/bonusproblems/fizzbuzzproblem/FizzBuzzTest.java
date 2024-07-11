package org.bonusproblems.fizzbuzzproblem;

public class FizzBuzzTest {

    public static void test(){

        MultithreadedFizzBuzz obj = new MultithreadedFizzBuzz(15);

        Thread t1 = new FizzBuzzThread(obj,"Fizz");
        Thread t2 = new FizzBuzzThread(obj,"Buzz");
        Thread t3 = new FizzBuzzThread(obj,"FizzBuzz");
        Thread t4 = new FizzBuzzThread(obj,"Number");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
