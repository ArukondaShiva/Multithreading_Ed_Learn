package org.bonusproblems.printingnumberseries;

public class PrintNumberSeriesTest {


    public static void test(){

        PrintNumberSeries zeo = new PrintNumberSeries(5);

        /*

        Suppose we are given a number _n_based on which a program creates the series 010203…0n.
        There are three threads t1, t2 and t3 which print a specific type of number from the series.
        t1 only prints zeros, t2 prints odd numbers and t3 prints even numbers from the series.

         */

        Thread t1 = new PrintNumberSeriesThread(zeo,"zero");
        Thread t2 = new PrintNumberSeriesThread(zeo,"even");
        Thread t3 = new PrintNumberSeriesThread(zeo,"odd");

        t2.start();
        t1.start();
        t3.start();

    }


}
