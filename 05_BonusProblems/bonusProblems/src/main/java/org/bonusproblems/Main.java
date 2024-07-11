package org.bonusproblems;


import org.bonusproblems.buildmolecule.H2OMachineTest;
import org.bonusproblems.fizzbuzzproblem.FizzBuzzTest;
import org.bonusproblems.orderedprinting.usingcountdownlatch.OrderedPrintingTestCL;
import org.bonusproblems.orderedprinting.usingwaitnotifyall.OrderedPrintingTest;
import org.bonusproblems.printingfoobarntimes.FooBarTest;
import org.bonusproblems.printingnumberseries.PrintNumberSeriesTest;

public class Main {

    public static void main(String[] args) {


        // 1 . Ordered Printing
        // using wait and notifyAll
        //OrderedPrintingTest.test();
        // using countDownLatch
        //OrderedPrintingTestCL.test();


        // 2 . Print FooBar n times
        // FooBarTest.test();


        // 3 . Printing Number Series (Zero, Even, Odd)
        // PrintNumberSeriesTest.test();


        // 4 . Build a Molecule
        // Building a H2O Molecule
        // H2OMachineTest.test();


        // 5 . FizzBuzz Problem
        FizzBuzzTest.test();


    }

}