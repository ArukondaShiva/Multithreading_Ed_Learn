package org.bonusproblems.orderedprinting.usingcountdownlatch;

public class OrderedPrintingTestCL {

    public static void test(){

        OrderedPrinting obj = new OrderedPrinting();

        OrderedPrintingThread t1 = new OrderedPrintingThread(obj,"first");
        OrderedPrintingThread t2 = new OrderedPrintingThread(obj,"second");
        OrderedPrintingThread t3 = new OrderedPrintingThread(obj,"third");

        t1.start();
        t2.start();
        t3.start();

    }

}
